"""
Test REST endpoint utility.
Usage example:
template file extension must be .yml

 Ex template:

 template: test_user.yml
    - message: Add user
      method: POST
      endpoint: /add/user
      payload:
        username: Admin
        password: admin
      response:
        $.username: Admin

That template will result in POST request at URL/add/user
with a payload '{username: Admin, password: admin}'
And then it will check response as defined in check_response()
Response will be checked not just for HTTP codes, but for
values in responses too.

For this example response will be searched for username key under
the document root. If value under key is the same as mapped value
no exception will be raised. Otherwise, AssertionException will
be raised.

All template files are 'executed' asynchronous, but tests inside templates
are 'executed' one after another, from top to bottom.
"""
import os
from unittest import TestCase

import yaml
from jsonpath_rw import jsonpath, parse
from requests import Session, Request, Response
from rx import Observable

"""
Path to directory with templates
"""
template_dir = "templates"
"""
REST service URL
"""
url = "http://localhost:8080"


def is_template(string: str):
    return string.endswith(".yml")


def get_templates():
    templates = []
    Observable.from_(os.listdir(template_dir)) \
        .filter(is_template) \
        .subscribe(templates.append)
    return templates


def template_as_yaml(template: str):
    with open(template_dir + "/" + template) as stream:
        return yaml.load(stream)


def prepare_request_from(template: dict):
    method = template.get("method", "")
    endpoint = url + template.get("endpoint", "")
    payload = template.get("payload", {})
    return {"request": Request(method, endpoint, json=payload), "template": template}


def prepare_requests(templates: list):
    return [prepare_request_from(template) for template in templates]


def execute_request(request: dict, session: Session):
    req = request.get("request")
    response = session.send(session.prepare_request(req))
    return {"template": request.get("template"),
            "method": req.method, "url": req.url,
            "payload": req.json, "response": response}


def execute_requests(request_list: list):
    session = Session()
    return [execute_request(request, session) for request in request_list]


def check_response(response: dict):
    code_msg = "response code not correct {} on {}.\n Should be in {} but was {}"
    template: dict = response.get("template", {})
    req_resp: dict = template.get("response", None)
    assert response is not None, "processing error"
    assert response["response"] is not None, "response is empty"
    resp: Response = response["response"]
    correct_codes = ()
    if response["method"] == "POST":
        correct_codes = (201, 200, 422)
    if response["method"] == "GET":
        correct_codes = (200,)
    if response["method"] == "DELETE":
        correct_codes = (200,)
    if response["method"] == "PUT":
        correct_codes = (200, 201)

    assert resp.status_code in correct_codes, code_msg.format(response["method"], response["url"],
                                                              correct_codes, resp.status_code)

    if req_resp is not None:
        for key, value in req_resp.items():
            expr: jsonpath.Child = parse(key)
            match = expr.find(resp.json())[0].value
            assert match == value

    print("###\n{} with response code {}, {} on {}".format(template["message"],
                                                           resp.status_code, response["method"], resp.url))
    print("# response: \n{}".format(resp.text))


class RestTests(TestCase):
    templates = []
    request_list = []
    session = Session()

    @classmethod
    def setup_class(cls):
        Observable \
            .from_(get_templates()) \
            .map(template_as_yaml) \
            .map(prepare_requests) \
            .subscribe(cls.request_list.append)

    def test_requests(self):
        Observable \
            .from_(self.request_list) \
            .flat_map(execute_requests) \
            .subscribe(check_response)

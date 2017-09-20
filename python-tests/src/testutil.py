import os

import yaml
from requests import Request, PreparedRequest, Session, Response
from rx import Observable

template_dir = "../templates"
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
        try:
            return yaml.load(stream)
        except yaml.YAMLError:
            pass


def prepare_request_from(template: dict):
    message = template["message"]
    method = template["method"]
    endpoint = url + template["endpoint"]
    payload = template["payload"]
    return Request(method, endpoint, json=payload)


def prepare_requests(templates: list):
    return [prepare_request_from(template) for template in templates]


def execute_request(request: PreparedRequest, session: Session):
    response = session.send(request)
    return {"method": request.method, "url": request.url, "payload": request.body, "response": response}


def execute_requests(request_list: list):
    session = Session()
    return [execute_request(request, session) for request in request_list]


def check_response(response: dict):
    try:
        code_msg = "response code not correct. Should be in {} but was {}"
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

        assert resp.status_code in correct_codes, code_msg.format(correct_codes, resp.status_code)

        print("Response code {}, {} on {}".format(resp.status_code, response["method"], resp.url))
    except AssertionError:
        msg = "### Failing request: {} on {} with payload {}".format(
            response["method"], response["url"], response["payload"]
        )
        raise AssertionError(msg)

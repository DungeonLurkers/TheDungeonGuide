import os
import yaml
from requests import Request, Session
from rx import Observable
from unittest import TestCase

template_dir = "../templates"
url = "http://localhost:8080"


def is_template(string):
    return string.endswith(".yml")


def get_templates():
    templates = []
    Observable.from_(os.listdir(template_dir)) \
        .filter(is_template) \
        .subscribe(templates.append)
    return templates


def template_as_yaml(template):
    with open(template_dir + "/" + template) as stream:
        try:
            return yaml.load(stream)
        except yaml.YAMLError:
            pass


def prepare_request_from(template, wtf):
    message = template["message"]
    method = template["method"]
    endpoint = url + template["endpoint"]
    payload = template["payload"]
    return Request(method, endpoint, data=payload)


def execute_request(request):
    assert isinstance(request, Request)


def check_response(response):
    pass


class RestTests(TestCase):
    templates = []
    request_list = []
    session = Session()

    @classmethod
    def setup_class(cls):
        Observable.from_(get_templates()) \
            .map(template_as_yaml) \
            .map(prepare_request_from) \
            .subscribe(cls.request_list.append)

    def test_requests(self):
        print(self.request_list)

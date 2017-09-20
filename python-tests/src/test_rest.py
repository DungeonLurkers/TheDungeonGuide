from unittest import TestCase

from requests import Session
from rx import Observable
from testutil import *


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
            .map(lambda req_list: [cls.session.prepare_request(req) for req in req_list]) \
            .subscribe(cls.request_list.append)

    def test_requests(self):
        Observable \
            .from_(self.request_list) \
            .flat_map(execute_requests) \
            .subscribe(check_response)

from logging import getLogger

import yaml
from requests import post, get, put
from rx import Observable

URL = "http://localhost:8080"
logger = getLogger(__name__)


def execute_request(request):
    required_in_response = Observable.from_(request["response"])
    message = request["message"]
    method = request["method"]
    endpoint = request["endpoint"]
    payload = request["payload"]
    response = None

    print("Executing {} method on {}".format(method, endpoint))
    logger.debug("Message {} with method {} on {} with data {}".format(message, method, endpoint, payload))

    try:
        if method == "GET":
            response = get(URL + endpoint, json=payload)
        if method == "POST":
            response = post(URL + endpoint, json=payload)
        if method == "PUT":
            response = put(URL + endpoint, json=payload)
        if method == "DELETE":
            response = put(URL + endpoint, json=payload)
    except Exception:
        print("Connection on {} refused".format(URL + endpoint))
        pass

    print("Response {}".format(response.status_code))
    logger.debug("Response headers: {}\n Response data: {}".format(response.headers, response.json()))

    required_in_response.subscribe(
        lambda key: print("{} in response: {}".format(key, request["response"][key] in response.text))
    )


class RequestParser:
    def __init__(self):
        self.template = None
        self.actions = None

    def set_template(self, template_file):
        self.template = template_file
        self.actions = Observable.from_(yaml.load(open(template_file, "r")))

    def execute(self):
        self.actions.subscribe(execute_request)


def main():
    p = RequestParser()
    print("Template: ../templates/test_requests.yml")
    p.set_template("../templates/test_requests.yml")
    p.execute()


if __name__ == '__main__':
    main()

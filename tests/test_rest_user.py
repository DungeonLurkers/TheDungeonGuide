import yaml
from rx import Observable


def execute_request(request):
    responseRequires = Observable.from_(request["response"])
    print("------------")
    print("Message: ", request["message"])
    print("Method:", request["method"])
    print("Payload: ", request["payload"])
    print("Response: ", request["response"])

    responseRequires.subscribe(lambda v: print(v))


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
    p.set_template("templates/rest_user.yml")
    p.execute()


if __name__ == '__main__':
    main()

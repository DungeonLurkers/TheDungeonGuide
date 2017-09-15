import requests
import rx


class RequestParser:
    def __init__(self):
        self.template = None
        self.actions = None

    def set_template(self, template):
        self.template = template

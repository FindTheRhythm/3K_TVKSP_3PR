import os
from http.server import BaseHTTPRequestHandler, HTTPServer


class Handler(BaseHTTPRequestHandler):
    def do_GET(self):
        message = os.getenv("APP_MESSAGE", "Docker работает")
        response = f"{message}\n"

        self.send_response(200)
        self.send_header("Content-Type", "text/plain; charset=utf-8")
        self.end_headers()
        self.wfile.write(response.encode("utf-8"))


if __name__ == "__main__":
    print("HTTP-сервер запущен на порту 8080", flush=True)
    HTTPServer(("0.0.0.0", 8080), Handler).serve_forever()
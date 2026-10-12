FROM python:3.12-slim

LABEL project="TVKSP Practical Work 3"
LABEL description="Dockerfile instructions demonstration"

ENV APP_MESSAGE="Практическая работа №3: Docker работает"
ENV PYTHONUNBUFFERED=1

WORKDIR /app

RUN mkdir -p /app/data \
    && groupadd --system appgroup \
    && useradd --system --gid appgroup --home-dir /app appuser \
    && chown -R appuser:appgroup /app

COPY app.py /app/app.py

ADD message.txt /app/message.txt

VOLUME ["/app/data"]

EXPOSE 8080

USER appuser

ENTRYPOINT ["python", "/app/app.py"]

CMD []

ONBUILD RUN echo "ONBUILD: дочерний образ успешно собирается"



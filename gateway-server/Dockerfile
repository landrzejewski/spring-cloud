FROM eclipse-temurin:21-jre-alpine

ARG USERNAME=user
RUN adduser --gecos "$USERNAME" --disabled-password --shell /bin/sh --uid 1014 ${USERNAME} \
    && addgroup ${USERNAME} ${USERNAME}

USER ${USERNAME}

COPY target/gateway-server.jar /home/$USERNAME

WORKDIR /home/$USERNAME

ENTRYPOINT ["java", "-jar", "gateway-server.jar"]

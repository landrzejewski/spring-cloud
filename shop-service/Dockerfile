FROM eclipse-temurin:25-jre-alpine

ARG USERNAME=user
RUN adduser --gecos "$USERNAME" --disabled-password --shell /bin/sh --uid 1014 ${USERNAME} \
    && addgroup ${USERNAME} ${USERNAME}

USER ${USERNAME}

COPY target/shop-service.jar /home/$USERNAME

WORKDIR /home/$USERNAME

ENTRYPOINT ["java", "-jar", "shop-service.jar"]

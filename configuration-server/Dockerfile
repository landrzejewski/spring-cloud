FROM openjdk:17-slim

ARG USERNAME=USER
ARG UID=1000
ARG GUID=$UID

RUN groupadd --gid $GUID $USERNAME \
    && useradd --uid $UID --gid $GUID -m $USERNAME \
    && chown -R $UID:$GUID /home/$USERNAME

USER $USERNAME

COPY target/configuration-server.jar /home/$USERNAME
WORKDIR /home/$USERNAME
CMD java -jar \
    -Dserver.port=$PORT \
    -Dspring.cloud.config.server.git.uri=$GIT_URI \
    -Dspring.cloud.stream.kafka.binder.brokers=$KAFKA_BROKERS \
    configuration-server.jar

FROM openjdk:17-slim

ARG USERNAME=USER
ARG UID=1000
ARG GUID=$UID

RUN groupadd --gid $GUID $USERNAME \
    && useradd --uid $UID --gid $GUID -m $USERNAME \
    && chown -R $UID:$GUID /home/$USERNAME

USER $USERNAME

COPY target/discovery-server.jar /home/$USERNAME
WORKDIR /home/$USERNAME
CMD java -jar \
    -Dspring.profiles.active=$PROFILES \
    -Dspring.config.import=$CONFIG_SERVER_URI \
    discovery-server.jar

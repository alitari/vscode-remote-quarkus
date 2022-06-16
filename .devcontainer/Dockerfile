FROM registry.redhat.io/quarkus/mandrel-21-jdk17-rhel8:21.3-18

USER root

RUN yum -y update && \
    yum -y install git wget sudo
    # #
    # # Allow for a consistant java home location for settings - image is changing over time
    # && if [ ! -d "/docker-java-home" ]; then ln -s "${JAVA_HOME}" /docker-java-home; fi


# # install native-image component
# RUN gu install native-image

# ENV GRAALVM_HOME=/opt/graalvm-ce-19.2.1

# Install tools

# # mvn
ARG MAVEN_VERSION=3.8.6
# ARG USER_HOME_DIR="/root"
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN mkdir -p /usr/share/maven /usr/share/maven/ref && \
  curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz && \
  tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 && \
  rm -f /tmp/apache-maven.tar.gz && \
  ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "/workspaces/vscode-remote-quarkus/.m2"

ADD settings.xml /usr/share/maven/conf/

# # docker
# RUN yum -y install docker-cli.x86_64

ARG USERNAME=alitari
ARG USER_UID=1000
ARG USER_GID=$USER_UID

# Create the user
RUN groupadd --gid $USER_GID $USERNAME && \
    useradd --uid $USER_UID --gid $USER_GID -m $USERNAME && \
    echo $USERNAME ALL=\(root\) NOPASSWD:ALL > /etc/sudoers.d/$USERNAME && \
    chmod 0440 /etc/sudoers.d/$USERNAME


# bash config
ADD .bashrc /home/alitari

USER $USERNAME


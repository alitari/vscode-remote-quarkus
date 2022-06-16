#-----------------------------------------------------------------------------------------
# Copyright (c) Microsoft Corporation. All rights reserved.
# Licensed under the MIT License. See LICENSE in the project root for license information.
#-----------------------------------------------------------------------------------------

FROM registry.redhat.io/quarkus/mandrel-21-jdk17-rhel8:21.3-18

USER root

RUN yum -y update && \
    yum -y install git wget
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

# # bash config
# ADD .bashrc /root


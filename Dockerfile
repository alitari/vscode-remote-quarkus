# fill the local repo with the libraries
FROM maven:3.6.1-ibmjava-8 as repoloader
COPY pom.xml .
# no output and never exit with failure code
RUN mvn clean package -Pnative > /dev/null 2>&1 || :
# RUN ls -l /root/.m2/repository

FROM oracle/graalvm-ce:19.2.1 as mavenbuilder
# Graal vm
RUN gu install native-image
ENV GRAALVM_HOME=/opt/graalvm-ce-19.2.1

# mvn
ARG MAVEN_VERSION=3.6.1
ARG USER_HOME_DIR="/root"
ARG SHA=b4880fb7a3d81edd190a029440cdf17f308621af68475a4fe976296e71ff4a4b546dd6d8a58aaafba334d309cc11e638c52808a4b0e818fc0fd544226d952544
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries
RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && echo "${SHA}  /tmp/apache-maven.tar.gz" | sha512sum -c - \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
ENV MAVEN_HOME /usr/share/maven
# ENV MAVEN_CONFIG "/workspaces/vscode-remote-quarkus/.m2"
# ADD settings.xml /usr/share/maven/conf/
# include the repo
COPY --from=repoloader /root/.m2 /root/.m2
# include source code
COPY --from=repoloader pom.xml .
COPY src src
# build native runnable
RUN mvn clean package -Pnative
# copy ssl stuff
RUN mkdir -p /tmp/ssl-libs/lib \
  && cp ${JAVA_HOME}jre/lib/security/cacerts /tmp/ssl-libs \
  && cp ${JAVA_HOME}jre/lib/amd64/libsunec.so /tmp/ssl-libs/lib/

# app container for the native executable
FROM registry.access.redhat.com/ubi8/ubi-minimal
ENV APP_TIMEZONE=UTC
ENV TZ=UTC
ENV JAVA_OPTS=${JAVA_OPTS:-""}
WORKDIR /work/
COPY --from=mavenbuilder target/*-runner /work/application
COPY --from=mavenbuilder /tmp/ssl-libs/ /work/
RUN chmod 775 /work
EXPOSE 8080
CMD ./application -Dquarkus.http.host=0.0.0.0 -Djava.library.path=/work/lib -Djavax.net.ssl.trustStore=/work/cacerts ${JAVA_OPTS}
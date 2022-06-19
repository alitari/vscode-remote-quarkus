# Vscode Remote Development Containers: [Quarkus](https://quarkus.io)

## create project

```bash
# Create a project with groupId=com.foo, artifactId=bar, and version=1.0
quarkus create app --no-wrapper --maven --java=17 com.foo:bar:1.0 && \
mv bar/src . && \
mv bar/pom.xml . && \
rm -rf bar
```

## quarkus dev mode

```bash
mvn quarkus:dev
```

or choose `Debug Quarkus` in vscode command palette in order to debug with vscode.

call endpoint with `curl localhost:8080/hello` or navigate to `http://localhost:8080` on your host browser

## test

```bash
mvn clean test
```

## build native executable

```bash
# show
native-image --version
mvn clean package -Pnative
# Find executable in `target` directory.
ls -l target/*-runner
```

## run integration tests against native executable

```bash
mvn verify -Pnative
```

## build docker image with native executable

```bash
docker build -f src/main/docker/Dockerfile.native -t quarkus-quickstart/getting-started .
```

## run docker image with native executable

```bash
docker run -i --rm -p 8080:8080 quarkus-quickstart/getting-started
# from your browser: http://localhost:8080/hello
curl -v http://localhost:8080/hello

```

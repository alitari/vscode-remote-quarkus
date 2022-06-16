# Vscode Remote Development Containers: [Quarkus](https://quarkus.io)

## dev mode

```bash
mvn clean compile quarkus:dev
```

or press `F5` in order to debug with vscode.

call endpoint with `curl localhost:8080/hello`

## test

```bash
mvn clean test
```

## build native executable

```bash
mvn clean package -Pnative
# Find executable in `target` directory.
ls -l target
```

## run integration tests against native executable

```bash
mvn verify -Pnative
```

## build docker image with native executable

```bash
sudo docker build -f src/main/docker/Dockerfile.native -t quarkus-quickstart/getting-started .
```

## run docker image with native executable

```bash
sudo docker run -i --rm -p 8080:8080 quarkus-quickstart/getting-started
# from your browser: http://localhost:8080/hello
curl -v http://localhost:8080/hello

```

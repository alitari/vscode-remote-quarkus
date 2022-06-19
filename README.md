# Vscode Remote Development Containers: [Quarkus](https://quarkus.io)

This repo contains several [vscode devcontainers](https://code.visualstudio.com/docs/remote/containers) for Quarkus in order to start right away without any additional installations. On your workstation you only need:

- a running [docker daemon](https://www.docker.com/)
- [vscode](https://code.visualstudio.com/) with [Remote Container extension](https://code.visualstudio.com/docs/remote/containers-tutorial)

Clone the branch of your choice and choose from vscode command palette `Remote container: Open Folder in container...`

## branches

| branch | Details |
| ------ | ------- |
| [`2.9.2.Final-java17-plain](https://github.com/alitari/vscode-remote-quarkus/tree/2.9.2.Final-java17-plain) | Quarkus 2.9.2, java17, native-image 21.3.2.0-1b5 Mandrel Distribution.|

## devcontainer user

In order to avoid permission issues you must use existing `UID` and `GID` from your host machine. The dockerfile assumes `1000` for the user and `1001` for docker group. ([We assume that you are using docker without sudo.](https://docs.docker.com/engine/install/linux-postinstall/#manage-docker-as-a-non-root-user)) However, this can be different on your machine, please adjust accordingly.

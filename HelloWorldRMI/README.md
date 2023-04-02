# Execução

1. Iniciar o RMI Registry
2. Iniciar o programa servidor
3. Iniciar o programa cliente

## Iniciar o RMI Registry

rmiregistry &

## Iniciar o programa servidor

java -Djava.rmi.server.hostname=127.0.0.1 Server

## Iniciar o programa cliente

java -Djava.rmi.server.hostname=127.0.0.1 Client

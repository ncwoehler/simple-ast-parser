# SQL Parser

How to run the SQL parser:

```
./gradlew installDist 
 ./build/install/nrpe-demo/bin/nrpe-demo -f src/test/resources/operations.sql
```

# IT Infrastructure

# How to build Docker file

Run

```
docker build . -t nrpe-demo
```

from project directory.

# How to run docker-compose

Run 

```
docker-compose build
docker-compose up
```


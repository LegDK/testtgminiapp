#!/usr/bin/env bash

export JAVA_HOME=/opt/render/project/.java
./mvnw clean package -DskipTests

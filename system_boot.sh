#!/bin/sh

cd configserver; ./gradlew clean build; cd ..
cd gatewayapi; ./gradlew clean build; cd ..
FROM gradle:8.1.0-jdk17 AS build
COPY --chown=gradle:gradle . /home/app/src
WORKDIR /home/app/src
RUN gradle build

FROM openjdk:17-oracle
COPY --from=build /home/app/src/build/libs/demo_alineacion-0.0.1-SNAPSHOT.jar demo_alineacion-0.0.1-SNAPSHOT.jar
#COPY target/demo_alineacion-0.0.1-SNAPSHOT.jar demo_alineacion-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","demo_alineacion-0.0.1-SNAPSHOT.jar"]
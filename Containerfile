FROM registry.access.redhat.com/ubi9/openjdk-21-runtime:1.23

ENV LANGUAGE="en_US:en"
WORKDIR /work/

COPY target/quarkus-app/lib/ /work/lib/
COPY target/quarkus-app/*.jar /work/
COPY target/quarkus-app/app/ /work/app/
COPY target/quarkus-app/quarkus/ /work/quarkus/

EXPOSE 8080
USER 185

ENTRYPOINT ["java", "-jar", "/work/quarkus-run.jar"]

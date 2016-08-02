FROM anapsix/alpine-java:jdk8
MAINTAINER Catz Sy <sy.catz@gmail.com> 
ADD /build/libs/helloworld-server.jar /helloworld-server.jar
ENTRYPOINT ["java", "-jar", "helloworld-server.jar"]
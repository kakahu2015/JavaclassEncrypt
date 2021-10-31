FROM java:8
VOLUME /tmp
ADD *.jar app.jar
ADD *.so libus.so
ADD lib lib
ADD config config
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-agentpath:/libus.so","-cp","/app.jar","org.kakahu.test.DemoApplication"]

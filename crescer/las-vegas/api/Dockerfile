FROM adoptopenjdk/openjdk11:alpine

RUN apk --no-cache update && \
    apk --no-cache upgrade && \
    apk add --update tzdata && \
    rm -rf /var/cache/apk/*
    
RUN echo ${TZ} > /etc/timezone
    
EXPOSE 80

ADD /target/las-vegas-api-0.0.1-SNAPSHOT.jar las-vegas-api.jar

ENTRYPOINT ["java","-jar","las-vegas-api.jar", "--server.port=80"]
FROM 127.0.0.1:5050/env/jdk7
     
MAINTAINER mark <mark@qq.com>

COPY ./target/train-mvc.jar /root/

WORKDIR /root/

EXPOSE 8080

CMD ["java" , "-Xmx1024m","-XX:MaxPermSize=128M","-jar", "train-mvc.jar"]
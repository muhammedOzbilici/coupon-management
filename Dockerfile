FROM openjdk:11
ADD target/coupon-management.jar coupon-management.jar
EXPOSE 8484
ENTRYPOINT ["java","-jar","coupon-management.jar"]
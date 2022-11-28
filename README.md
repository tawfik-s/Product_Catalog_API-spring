# Product_Catalog_API

API for E commerce app from Requirement document

[Requirement document](./requirements.md)


### post man for API end points map
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/30ef984c008334ca9696?action=collection%2Fimport)


### prepare local docker instance for ORACLEDB:XE 21

[How to create oracle docker image](https://github.com/oracle/docker-images/tree/main/OracleDatabase/SingleInstance)

```shell
 docker run --name oracle19 
 -p 1521:1521 -p 5500:5500 
 -e ORACLE_PWD=system 
 -v E:/docker/oraclexedatabase:/opt/oracle/oradata 
 6ca1a57e059ad388721234dbcacfcb2e1bd3c60c140ed2a24be32a9e99d1d504
#last number is the image id 
```
### database config 
modify the properties file

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=system
spring.datasource.password=system
```
### project entity diagram

![entity diagram](./images/entity%20diagram.png)

### run the project

- first install maven [steps](https://maven.apache.org/install.html)
- use this command to run spring project `mvn spring-boot:run`

### package this app

- clean create jar `mvn clean install`
- run this jar `java -jar <jar file name>`

### project class diagram

![class diagram](./images/product_catalog_api_class_diagram.png)
# JavaSwagger
This project uses the Swagger Specification to drive an API implementation. Rather than a typical top-down or bottom-up swagger integration, the Inflector uses the swagger specification as a DSL for the REST API. The spec drives the creation of routes and controllers automatically, matching methods and method signatures from the implementation. This brings a similar integration approach to the JVM as swagger-node brings to the javascript world.

To allow for an iterative development, the framework will mock responses for any unimplemented methods, based on the specification. That means you can ship your API to your consumers for review immediately as you build it out.

You have full control over the mapping of controllers to classes and methods as well as models.

Components

Inflector uses the following libraries:

Swagger models for the swagger definition
Jackson for JSON processing
Jersey 2.6 for REST

1)After import project to local 

go to commnad line and go to path where you have save your project
 For Example: I have save git project in following Path:
 ```
 /Users/admin/JavaSwagger
 ```
 then
 ```
 run ./editor.sh
 ```
 it will open Swagger Editor in your browser with documentation
 with
 ```
 http://localhost:8000/#
 ```
 and in command line it will show
 ```
[INFO] Started ServerConnector@270b6b5e{HTTP/1.1}{0.0.0.0:8000}
[INFO] Started @4598ms
[INFO] Started Jetty Server
```
2) open new tab in terminal and run
```
mvn package jetty:run
```
it will run your server on 
```
localhost://8080
```
Now Swagger editor is nothing but swagger.yaml file
which you can find in following path of your project
```
/Users/admin/JavaSwagger/src/main/swagger/swagger.yaml
```yaml
you can edit this file using editor and simultenously you can see your api related documentation will change

Now check swagger.yaml file

#swagger.yaml
-----------------------------------------------------------------
```yaml
swagger: '2.0'

info:
  version: "1.0.0"
  title: Sample API

basePath: /v1

host: localhost:8080


paths:
  "/pet/{petId}":
    get:
      tags:
        - pet
      summary: Find pet by ID
      description: Returns a single pet
      operationId: getPetById
      x-swagger-router-controller: io.swagger.sample.controllers.SampleController
      consumes:
        - application/xml
        - application/json
      produces:
        - application/xml
        - application/json
      parameters:
        - name: petId
          in: path
          description: ID of pet to return
          required: true
          type: integer
          format: int32

      responses:
        "200":
          description: successful operation
          schema:
            type: array
            items:
              $ref: "#/definitions/Pet"
        "400":
          description: Invalid ID supplied
        "404":
          description: Pet not found  
definitions:
  Pet:
    type: object
    properties:
      id:
        type: integer
      user:
        type: string
      name:
        type: string
      age:
        type: integer
      size:
        type: string
      petReqEnum:
        type: string 
      hostFeatureEnum:
        type: string
      petBreed:
        type: string  
 ```
--------------------------------------------------------------------------

In this yaml we have define local host:8080
which we have define in our pom.xml
#pom.xml
---------------------------------------------------------------------------
```yaml
   <plugin>
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>${jetty-version}</version>
                <configuration>
                    <monitoredDirName>.</monitoredDirName>
                    <scanTargets>
                        <scanTarget>inflector.yaml</scanTarget>
                        <scanTarget>src/main/swagger/swagger.yaml</scanTarget>
                    </scanTargets>
                    <scanIntervalSeconds>1</scanIntervalSeconds>
                    <webApp>
                        <contextPath>/</contextPath>
                    </webApp>
                    <httpConnector>
                        <port>8080</port>
                        <idleTimeout>60000</idleTimeout>
                    </httpConnector>
                </configuration>
            </plugin>
 ```
------------------------------------------------------------------------------------
Flow:
Now what happend when you hit 
http://localhost:8080/v1/pet/0

1)It will go to swagger.yaml
Then check for basePath:/v1  which should match with above url

2)It will check for paths:
"pet/{petId}" which match with our url:"pet/0"


then it will check for controller
 x-swagger-router-controller: io.swagger.sample.controllers.SampleController
 which we have define in /Users/admin/JavaSwagger/inflector.yaml
 
# inflector.yaml:
------------------------------------------------- 
```yaml
controllerPackage: io.swagger.sample.controllers
swaggerUrl: ./src/main/swagger/swagger.yaml
```
--------------------------------------------------  

where package:io.swagger.sample.controllers
and  java file:SampleController

Now if you go to this specific package in your eclipse inside "SampleController.java"

we have method:getPetById
this method name should match with operationId define in swagger.yaml
```yaml
operationId:getPetById
```

Next is produces and consumes define as 
application/xml
application/json

Parameters:
```yaml
 parameters:
        - name: petId(name of the parameter which should match with"/pet/petId")
          in: path
          description: ID of pet to return
          required: true(if you set as true then you must pass in your api request)
          type: integer
          format: int32
```
Response
```yaml
Responses:
        "200":
          description: successful operation
          schema:
            type: array
            items:
              $ref: "#/definitions/Pet"
 ```
this means if response 200 then you must send response as $ref: "#/definitions/Pet" which is nothing but pet
object you have define in definition

And in eclipse same pet Object we have created under:
io.swagger.sample.models.Pet

But you must define this models under inflector.yaml
----------------------------------------
```yaml
modelPackage: io.swagger.sample.models
modelMappings:
  Pet: io.swagger.sample.models.Pet
entityProcessors:
  - json
  - xml
  ```
-------------------------------------------  

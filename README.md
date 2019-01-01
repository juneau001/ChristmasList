# ChristmasList
Example of a MicroProfile Microservice

Demonstrates the abilty to utilize the MicroProfile Config API, Fault Tolerance with @Retry @Fallback

This repository provides a demo Microservice application that has been built using MicroProfile 2.1.  Build using Maven, and deploy to a compliant MicroProfile implementation.  In my talk "Lightweight Java EE Using MicroProfile", I utilize Payara Micro for deployment.

To deploy to Payara Micro, compile the WAR file and deploy as such:

<pre>
java -jar payara-micro-5.184.jar ChristmasList-1.0-SNAPSHOT.war
</pre>

To pass a set of externally defined system properties (properties file), use the following deployment:
<pre>
java -jar payara-micro-5.184.jar --systemProperties /Java_Dev/payara/micro/cjug/microprofile-config.properties ChristmasList-1.0-SNAPSHOT.war
</pre>

Once deployed, visit the following URLs to see demos.

Config Examples:

CONFIG

1) Embedded microprofile-config.properties
java -jar payara-micro-5.184.jar  ChristmasList-1.0-SNAPSHOT.war

http://localhost:8080/ChristmasList-1.0-SNAPSHOT/rest/christmasListService

2) External microprofile-config.properties
java -jar payara-micro-5.184.jar --systemProperties /Java_Dev/payara/micro/cjug/microprofile-config.properties  ChristmasList-1.0-SNAPSHOT.war

http://localhost:8080/ChristmasList-1.0-SNAPSHOT/rest/christmasListService

Dynamic:
http://localhost:8080/ChristmasList-1.0-SNAPSHOT/rest/christmasListService/user
FAULT TOLERANCE:

1) @Retry
http://localhost:8080/ChristmasList-1.0-SNAPSHOT/rest/christmasPresentSuggestionService/1

2) @Fallback (retry a number of times)
http://localhost:8080/ChristmasList-1.0-SNAPSHOT/rest/christmasPresentSuggestionService

HEALTH CHECKING:
1) http://localhost:8080/health
- Show how to implement healthCheck
2) UI:  http://localhost:8080/health-ui


METRICS

http://localhost:8080/metrics/base
http://localhost:8080/metrics/vendor
http://localhost:8080/metrics/application

OPENAPI

http://localhost:8080/openapi



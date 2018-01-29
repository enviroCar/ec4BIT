# ec4BIT

**E**nviro**C**ar **for** **B**ridging the **I**nteroperability **G**ap of the **I**nternet of **T**hings. ec4BIT
implements the connector and a testconsumer for the [BIG IoT marketplace](https://market.big-iot.org/). The connector
maps enviroCar tracks and measurements in a flattened json schema and produces as offerings for them on the BIG IoT marketplace.

BIG IoT is a European project to enable IoT Ecosystems. The BIG IoT API and the BIG IoT Marketplace form an IoT 
ecosystem where European companies can exploit the business potential of the IoT sector.

enviroCar was successfully applied in the First Open Call of the EC funded BIG IoT project. Thus, the enviroCar 
project is one of the first projects to appear on the [BIG IoT marketplace](https://market.big-iot.org/).

## Libraries and Licenses

#### third party
|Library|License|Link/Source|
|:----:|:----:|:----:|
|Eclipse Bridge.IoT|Eclipse Public License 2.0|[https://projects.eclipse.org/proposals/eclipse-bridge.iot](https://projects.eclipse.org/proposals/eclipse-bridge.iot)|
|Spring-framework|Apache License Version 2.0|[https://github.com/spring-projects/spring-framework/blob/master/src/docs/dist/license.txt](https://github.com/spring-projects/spring-framework/blob/master/src/docs/dist/license.txt)|
|Simple Logging Facade for Java|MIT License|[https://www.slf4j.org/license.html](https://www.slf4j.org/license.html)|
|commons-logging, commons-io|Apache License Version 2.0|[https://commons.apache.org/proper/commons-bsf/license.html](https://commons.apache.org/proper/commons-bsf/license.html)|
|squreup retrofit|Apache License Version 2.0|[https://github.com/square/retrofit/blob/master/LICENSE.txt](https://github.com/square/retrofit/blob/master/LICENSE.txt)|
|Joda-Time|Apache License Version 2.0|[http://joda-time.sourceforge.net/license.html](http://joda-time.sourceforge.net/license.html)|

#### ec4BIT
|Library|License|Link/Source|
|:----:|:----:|:-----:|
|ec4BIT|GNU General Public License v3.0|[https://github.com/enviroCar/ec4BIT/blob/master/LICENSE)|

## Installation

  1. Clone the repository `git clone https://github.com/enviroCar/ec4BIT`.

  2. Build the project's modules `mvn clean install`.

## Configuration

#### Register an organization, provider, and a consumer:

  1. First, you have to register an organization on the [BIG IoT marketplace](https://market.big-iot.org/). 

  2. Second, create a provider and a consumer.

#### Once you've registered your provider and your consumer, you can supply the IDs and SECRETs:

  1. Create a file `secret.yml` in `ec4BIT\connector\src\main\resources\` and replace IDs and SECRETs of your registered 
  provider and consumer:

  ```
  bigiot:
    provider:
        id: <YOUR_PROVIDER_ID>
        secret: <YOUR_PROVIDER_SECRET>
    consumer:
        id: <YOUR_CONSUMER_ID>
        secret: <YOUR_CONSUMER_SECRET>
  ```
  You find ID and SECRET of your provider and consumer on the marketplace, when being logged in into your organization.

  2. Copy and paste the modified `secret.yml` into `ec4BIT\consumer\src\main\resources\`.

#### Choose active profile modus:
There are 2 profiles preconfigured: [`development (default)`](https://github.com/enviroCar/ec4BIT/blob/master/connector/src/main/resources/application.yml#L16L37) 
and [`productive`](https://github.com/enviroCar/ec4BIT/blob/master/connector/src/main/resources/application.yml#L41L62). The development profile
marks the produced offerings as *testing*. More importantly, the spring application will be hosted at `localhost:7777`. The productive profile
uses reasonable offering names. The application will be hosted at the address and port of [your choice](https://github.com/enviroCar/ec4BIT/blob/master/connector/src/main/resources/application.yml#L45L46).

  * **development (default):**
  Change the active profile in [`line 5 of application.yml`](https://github.com/enviroCar/ec4BIT/blob/master/connector/src/main/resources/application.yml#L5) to `development`.
  
  * **productive:**
  Change the active profile in [`line 5 of application.yml`](https://github.com/enviroCar/ec4BIT/blob/master/connector/src/main/resources/application.yml#L5) to `productive`.

## How to deploy
This project is implemented using the spring-boot framework. Out of the box, Spring Boot provides an executable *.jar file, that can run the entire Spring application with no fuss: no build required, no setup, no web server configuration, etc.

    1. Build the project with Maven: `mvn clean install`
    2. Take the executable file `connector-0.1.jar` and move it to a application server of your choice (e.g. Apache Tomcat).
    3. Run `java -jar /path/to/file/connector-0.1.jar`

Further reading: [https://spring.io](https://spring.io/blog/2014/03/07/deploying-spring-boot-applications)

## Bugs and Feedback
Developer feedback goes a long way towards making this repository even better. Submit a bug report or request feature enhancements to [via mail to enviroCar@52north.org](mailto:enviroCar@52north.org?Subject=ec4BIT) or open a issue on this github repository.´

## Funding

This project has received funding from the European Union's Horizon 2020 research and innovation programme 
under grant agreement No 688038.

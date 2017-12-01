# ec4BIT

**E**nviro**C**ar **for** **B**ridging the **I**nteroperability **G**ap of the **I**nternet of **T**hings. ec4BIT
implements the connector and a testconsumer for the [BIG IoT marketplace](https://market.big-iot.org/). 

BIG IoT is a European project to enable IoT Ecosystems. The BIG IoT API and the BIG IoT Marketplace form an IoT 
ecosystem where European companies can exploit the business potential of the IoT sector.

enviroCar was successfully applied in the First Open Call of the EC funded BIG IoT project. Thus, the enviroCar 
project is one of the first projects to appear on the [BIG IoT marketplace](https://market.big-iot.org/).

## Funding

This project has received funding from the European Union's Horizon 2020 research and innovation programme 
under grant agreement No 688038.

## Libraries and Licenses

### third party
**_TODO_**

### ec4BIT
**_TODO_**

## Installation

1. Clone the repository `git clone https://github.com/enviroCar/ec4BIT`.

2. Build the project's modules `mvn clean build`.

## Configuration

* Register an organization, provider, and a consumer:

  1. First, you have to register an organization on the [BIG IoT marketplace](https://market.big-iot.org/). 

  2. Second, create a provider and a consumer.

* Once you've registered your provider and your consumer, you can supply the IDs and SECRETs:

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

* Choose active profile modus:
There are 2 profiles preconfigured: [`development (default)`](https://github.com/enviroCar/ec4BIT/blob/master/connector/src/main/resources/application.yml#L22L44) 
and [`productive`](https://github.com/enviroCar/ec4BIT/blob/master/connector/src/main/resources/application.yml#L48L70). The development profile
marks the produced offerings as *testing*. More importantly, the spring application will be hosted at `localhost:7777`. The productive profile
uses reasonable offering names. The application will be hosted at the address and port of [your choice](https://github.com/enviroCar/ec4BIT/blob/master/connector/src/main/resources/application.yml#L52L53).


  * **development (default):**
  Change the active profile in [`line 5 of application.yml`](https://github.com/enviroCar/ec4BIT/blob/master/connector/src/main/resources/application.yml#L5) to `development`.
  
  * **productive:**
  Change the active profile in [`line 5 of application.yml`](https://github.com/enviroCar/ec4BIT/blob/master/connector/src/main/resources/application.yml#L5) to `productive`.
  
## How to deploy
**_TODO_**

## Bugs and Feedback
**_TODO_**

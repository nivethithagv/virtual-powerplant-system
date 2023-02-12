# virtual-powerplant-system
This is a demo api to create, manage and store batteries across regions.

# Endpoints

The API has two endpoints 

``POST /batteries`` Creates or Updates the List of Batteries, in the given postcode range.

**Assumption 1** - Battery Name is unique within the postcode range. If same name is
found within the postcode, the watt capacity if the battery will be added to existing capacity.
Watt Capacity must be a positive value.


``GET /getbatteries?from=6000&to=6997`` Lists the Batteries for the given postcode range

**Assumption 2** - Returns total and average watt capacity for the fetched list as a whole,
not per postcode

**Assumption 3** - Can use only WA postcode range 6000-6997


## Prerequisites

Java - JDK17

apache-maven-3.9.0

Spring Boot Version - 3.0.2


## Build and Deployment in local

**Build**

``mvn clean install``

**Deploy**

``mvn spring-boot:run``

## H2 Database console enabled

Database connection details can be found in application.properties file

Console can be accessed here - http://localhost:8081/h2-console
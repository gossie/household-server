[![CircleCI](https://circleci.com/gh/gossie/household-server/tree/main.svg?style=svg&circle-token=ac9717c2c2741ee0aa407457dc945e2349280a1b)](https://circleci.com/gh/gossie/household-server/tree/main)

# household-server

The application provides the functionality to create an household and invite other members to this household. In a household it is possible to maintain
* shopping lists
* todo lists
  * periodic tasks
  * single tasks
* a food plan
* a cookbook

## Inside a module

Each module is implemented using a [hexagonal architecture](https://alistair.cockburn.us/hexagonal-architecture/). It consists of the following sub projects:
* domain<br />
  Provides a service implementation and an domain objects to implement the business logic. Also a repository and an observer interface is present to define what methods are needed for persistence (repository) and what events a broadcasted to notify other modules (observer).
* rest<br />
  Provides HTTP endpoints that might take DTO objects. Requests are delegated to the service in the domain project.
* persistence<br />
  Implements the repository interface of the domain. This implementation transforms the domain objects into a representation that is actually persisted.
* messaging<br />
  Implements the observer interface of the domain. This implementation publishes an event that can be consumed by other modules. It also provides an event handler that consumes events from other modules and delegates that action to the domain service.

![Module](/architecture02.png)

This design makes sure, that rest, messaging and persistence depend on the domain sub project. That way there are no unwanted transitive dependencies, for example a REST controller has no access to the underlying database connector.

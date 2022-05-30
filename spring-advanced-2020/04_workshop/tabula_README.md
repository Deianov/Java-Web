# Introduction

Welcome to the **Tabula** project. This sample project is meant to serve as a basis for the 
[Spring advanced course](https://softuni.bg/trainings/2845/spring-advanced-june-2020) at SoftUni.

The functionality in the app includes:

* authentication and authorization (Spring Security)
* creation of announcements
* creation of events (single, weekly, monthly, annual)
* validation
* logout
* facebook integration

# Used technologies

This is a Spring Boot application and the following libraries, integrations and technologies are used:

* Spring Data JPA
* Spring Security
* Thymeleaf and Thymeleaf security integration
* Spring Security OAuth2
* Liquibase
* Webjars
* HTML, jQuery, CSS, bootstrap
* Mapstruct, lombok
* MySQL 
* Gradle

# Running the app

## Starting MySQL

The application works with a running MySQL database. The easiest way to have an up and running
data base is by installing (if not alreay installed) docker and navigating to the `local` folder
of the project. Then execute:

`local git:(master) ✗ docker-compose -f docker-compose-local.yml up`

Once this is complete use have a running development instance of MySQL. The user is
`root` and it can log without password. Use your favourite SQL client to connect
to the server, e.g. MySQL Workbench MySQL CLI client, Table Plus, etc.

## Starting the application

### IntelliJ

You can import the project in IntelliJ and run the app. The main class is `TabulaApplication`.

### Gradle

You can run the app with gradle:

`tabula git:(master) ✗ ./gradlew bootRun`

# Contributing

Any contributions are welcomed with [pull requests](https://github.com/luchob/tabula/pulls).

Any bugs, questions or reasonable wishes may be tracked as [issues](https://github.com/luchob/tabula/issues).

Special thanks to [Martin-BG](https://github.com/Martin-BG) for the valuable contributions to this project.

# customer-app-components

The goal is to find out list of customers within given range in km. 
The application is design to stream all customer data and filter out and output only those customer Ids who are within given distance X.

## Minimum requirements:
1. Linux is supported as a development and production platform
2. Java 8 or above. You can download latest Oracle JDK from - http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html
3. Apache Maven 3.3.9 or above
4. Git client

## Installation and Run
Please make sure that commands - git, mvn and java can be executed directly from the command line. If not, please add them to the PATH environment variable.
Following are the steps to setup and build the project

1. Checkout the project `https://github.com/Vishal-Nagpure/customer-app-components.git`
2. Steps to Build
    `mvn clean install` 
3. Open target/conf/configuration.properties and add values for all the properties as per instructions in the file.
4. Run the project 
    `java -jar target/customer-app-1.0.jar`
# sky-parent-control-service
A simple service that verifies a parent control level preference and movie ID combination. 

Valid Parent Control Level preferences are U, PG, 12, 15 and 18.

Valid Movie IDs are 1-10.

# Valid Movie ID/Parent Control Level combinations

Movie ID  Parent Control Level  
------------------------------  
1          U  
2          U  
3          PG  
4  		     PG  
5          12  
6          12  
7          15  
8          15  
9          18  
10         18   

# Installation Requirements
Java 8 (v1.8.0_51)  
Apache Maven (v3.3.8)

# Build instructions
mvn package

# To execute unit tests
mvn test

# Run Instructions
To run the program performing the following steps in a command prompt window in the root directory of the project:-

mvn package  
java -jar target/parental-control-service-1.0.0-SNAPSHOT.jar PG 3

In the above example PG is the parent control level and 3 is the movie ID.

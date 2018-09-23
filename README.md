# liberty-mp
A sample project using IBM OpenLiberty along with Microprofile 1.4 To run you need to have at least Java 8 and maven installed (type ''mvn clean install'' in the base dir to compile&package the application). To run, just type 'java -jar target/liberty-mp.jar'. To try it out, open http://127.0.0.1:8080/liberty/hello/{your_name_goes_here} . 

This endpoint includes counting of the total number of request (MP Metrics) and a circuit breaker (MP Fault Tolerance). To emulate runtime failures and to demonstrate the circuit breaker, exceptions are randomly thrown.

The MP metrics can be viewed on http://localhost:8080/metrics and http://localhost:8080/metrics/application

# SolactiveCodingChallenge

Description
-------------

1. This application provides APIs which process tick values from various clients provided in the following format.

     TIMESTAMP=<linux timestamp>|PRICE=5.24|CLOSE_PRICE=|CURRENCY=EUR|RIC=AAPL.OQ

     TIMESTAMP=<linux timestamp>|PRICE=5.24|CLOSE_PRICE=|CURRENCY=EUR|RIC=IBM.N

     TIMESTAMP=<linux timestamp>|PRICE=|CLOSE_PRICE=7.5|CURRENCY=EUR|RIC=AAPL.OQ

2. API with resource path as "solactive/ticks" is used to consume the tick values.

3. Only ADMIN users will have the previlege to search tick values for any given RIC.

4. Users are given an option to download tick values with a valid close price as a .CSV file
     
5. RabbitMQ concurrency is leveraged to enhance the perfomance.
     
     Current implementation makes use of 4 concurrent consumers which can be modified according to the load expected
   
     ![image](https://user-images.githubusercontent.com/108806756/212099435-5f59a95d-870c-4c53-bc57-f1e1c9b21859.png)

5. Swagger has been used for documentation purpose.

    ![image](https://user-images.githubusercontent.com/108806756/211883408-a9a8801c-b7d3-4963-9dd6-c56a098cc7bc.png)


Assumptions
--------------

1. Input Tick values are in TEXT format and Output from search will be in JSON 

2. Tick values with future timestamps are not allowed

3. Tick price can not be negative

4. RIC cannot be empty

5. Expected message count is assumeed to be 50,000. This is set as the initial value for Concurrent hashmap.

6. Modification of tick values is not allowed. The tick values will always be inserted.

Technologies Used
-----------------

1. Java SE 17

2. Spring boot 3.0.1

4. RabbitMQ

5. Swagger

6. Spring boot test

Improvements
------------
1. Scope for Docker file or similar to run the application in a container

2. To imporve the latency and throughput,any other kind of asynchronus event processing architectures could be introduced. eg  LAMAX Disruptor
 
3. Scope for better test coverage

4. Extensive documentation
 
5. Option for users to send the exported .CSV file into a given mail ID

 
 Thank you!

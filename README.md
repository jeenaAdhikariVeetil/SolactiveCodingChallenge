# SolactiveCodingChallenge

Description
-------------

1. This application provides APIs which process tick values from various clients in the following format.

     TIMESTAMP=<linux timestamp>|PRICE=5.24|CLOSE_PRICE=|CURRENCY=EUR|RIC=AAPL.OQ

     TIMESTAMP=<linux timestamp>|PRICE=5.24|CLOSE_PRICE=|CURRENCY=EUR|RIC=IBM.N

     TIMESTAMP=<linux timestamp>|PRICE=|CLOSE_PRICE=7.5|CURRENCY=EUR|RIC=AAPL.OQ

2. API with resource path solactive/ticks is used to consume the tick values.

3. Only ADMIN users will have previlege to lookup the tick values for a specified ric with API of /solactive/tics/ric

4.There is an option for users to export the tick values for a specified ric where close price is not empty.

Tick values will be exported as CSV files in users local repository.

5. swagger implementation has been done for a better understanding and testing. It can be accessed via following url once the application is up.

http://localhost:8080/swagger-ui/index.html

![image](https://user-images.githubusercontent.com/108806756/211883408-a9a8801c-b7d3-4963-9dd6-c56a098cc7bc.png)


Assumptions
--------------

1. Tick values with future timestamps are not allowed

2. Tick price can not be negative

3. Ric cannot be empty

4. Application can hanle upto 50,000 tick values in a single request.

5. Modification of tick values is not allowed. The tick values always be inserted.

Technologies Used
-----------------

1. Java SE 17

2. Spring boot 3.0.1

4. RabbitMQ

5.Swagger

6.Spring boot test

Improvements
------------
1. To imporve the latency and throughput of the application Work Queues (aka: Task Queues) or LAMAX Disruptor could be introduced
 
 We can create concurrent consumers to easily parallelise the work
 
 2. More intergation tests could be added
 
 
 Thank you!

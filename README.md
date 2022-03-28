# EmailServer: assessment for (BMC Software)[https://www.bmc.com/] from Israel

## Goal

Send or load emails choosing from which account the user wants to achieve this, so he won't need to login in differents websites everytime. In this occasion I didn't use **Spring Boot**. As a server i used **Glashfish** and i implemented the REST communication with **JAX-RS**.

## Main parts

In order to preserve the security, i implemented an independent module -**emailserver-authentication** to request for a token, using the following request:

```
curl --location --request POST 'localhost:9090/login' \
--header 'Authorization: Basic YWRtaW46YWRtaW4=' \
--header 'Content-Type: application/json' \
--data-raw '{
    "user":"admin",
    "password":"admin"
}'
```
I simplfyied the username signed in, so there are just one possible user called **admin**. After getting the token, it will be possible to request the main module
**emailserver** for sending and loading emails.

![](https://github.com/mdymen85/emailserver/blob/main/interaction.png)

In the following diagram we can see the main flow. A client first need to ask for the token, then will request to send an email, and the emailserver will ask for the information to redis, and then will send the email, using the email the user wrote in the request, as follow:

```
curl --location --request POST 'localhost:8081/email' \
--header 'Authorization: eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNjQzMDQ1MjUxLCJzdWIiOiIiLCJpc3MiOiJhZG1pbiIsImV4cCI6MTY0MzA0ODg1MX0.u0ZEowkbEgB5_AtSLdRjlt-YPuJZWgjYULQH5hrJSRA' \
--header 'Content-Type: application/json' \
--data-raw '{
    "from":"martin.dymenstein@yahoo.com",
    "to":["martin@dymenstein.com"],
    "subject":"subject to send",
    "body":"text, content to send"
}'
```
Also, the client has the possibility to pick the emails he wants to read by using the following request:

```
curl --location --request GET 'localhost:8081/email/martin.dymenstein@yahoo.com/2' \
--header 'Authorization: eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNjQzMDQ1MjUxLCJzdWIiOiIiLCJpc3MiOiJhZG1pbiIsImV4cCI6MTY0MzA0ODg1MX0.u0ZEowkbEgB5_AtSLdRjlt-YPuJZWgjYULQH5hrJSRA'
```
In this case, the client is asking for the second -2- page of emails in their yahoo account. The number of emails that are being returning is something it can be configured in the **app.properties** by updating the property **application.max-load.emails**


## Architecture

As i point in the next image, i tryied to use **hexagonal architecture** with **domain driven design**. The domain communicates with the other parts of the system by interfaces --**ports**-- leaving the main activity to the **domain** sector. I wanted to insert business logic, just as validations, inside de domain. Because of that i develop objects for the infraestructure part --**repository**- and DTOs in the controller, to decouple the domain between the other layers, so this layers could be replaced easily.

![](https://github.com/mdymen85/emailserver/blob/main/architecture.png)

## Main components

As we can see in the following picture: there are a group of packages that manage receiving the requests from outside, and validate if the token its ok. This logic layer has a comunication, using an interface, with the domain logic. The domain logic will figure it out how to send the email according to the desire of the client. Also loads the emails from the account selected by a GET request. This layer will comunicate, using other interface, with the repository layer, that is the layer that manage to pick up the needed data.

![](https://github.com/mdymen85/emailserver/blob/main/packages.png)

## Design Patterns

In the code i used design patterns as **builder**, to create objects with the library **lombok**:

```
<dependency>
	<groupId>org.projectlombok</groupId>
	<artifactId>lombok</artifactId>
	<version>1.18.22</version>
	<scope>provided</scope>
</dependency>
```
For load one instance of **redis**, for example, i used **singleton**, because i don't need to create new instances of the object everytime i want to use it. To select what email i will use to send or load information, i developed a **factory** with an enum, so the factory will give me the instance -based on the host- i will need to use with the information of connection to the email server. So, passing just the host, i will get the instance -yahoo, gmail, walla- to use to send or load emails. I used **template method** because i have generic information to send or load emails, that all implementations must use, but also i have specific information, such as password or connection attributes, that depends on the host i use, that must be implemented in the subclasses. This approach is in the following image:

![](https://github.com/mdymen85/emailserver/blob/main/factory.png)

## Login user

As i mentioned before, exists an other module that it can be used for the client to request a JWT token. Inside this token is the username of the client, so everytime the main application receives a request for an operation, it will pick from the token, the username. With this information, it will pick the information of the connection, to authenticate to the email server, so the client can use their email inside the application. Its importante to say that i had to do manual configurations in the email i used, to allow third applications to use the accounts.

```
Set<UserEmailEntity> findEmailsByUsername(String username) {
		
	var yahoo = UserEmailEntity.builder()
			.email("martin.dymenstein@yahoo.com")
			.password("oejtgdmwxgjarjnp")
			.username("admin")
			.build();

	var google = UserEmailEntity.builder()
			.email("martin.dymenstein@gmail.com")
			.password("bcraamspiilnas")
			.username("admin")
			.build();

	var walla = UserEmailEntity.builder()
			.email("martin.dymenstein@walla.com")
			.password("")
			.username("admin")
			.build();


	return Set.of(yahoo, google, walla);
	}
}
```
I didn`t use a database because i think its not the porpuse of the excercise, so i mocked the database request for information as the above method shows. If there exists a desire to use a database, to pick the wanted information, it just need to connect and return the **Set<UserEmailEntity>** with the data that is in the database. This means that the application has a decouple layer, that can be replace with relational database, noSql, and else.

## Redis

Redis in this application enters in a trade off status. I decided to use it because i was thinking about scalability. So if i run more than one instance of the application, all will use a unique common place as cache. This brings some advantages, as i told before, but also brings us some disadvantages, for example, latency of connecting to an other instance, so this can add some extra miliseconds to the operation.  
In other hand, we can use local cache, inside the instances, managed by the application. This is not a scalable solution, because all instances has to add the information in their cache, so it can happend that: if we have a gateway, elb or else infront of the application, we might never repeat an instance that the information is cached. But, the advantage is that we don't have any extra latency when we want to retrieve data from the cache. In applications that runs in very few instances, it might be more confortable to have just local cache and not distributed cache.
How to implement this local cache? We need a structure such as HashTable, that needs to be Thread-Safe in order to share the same table between threads.

I used redis in docker, with the following command to pull the image and run the container:

```
 docker run --name redis -d -p 6379:6379 redis
```

### Circuit breaker

If Redis is off, the application can`t stop. The most elegant way of implementing this, is developing a circuit breaker when we achive some connection tentatives, in this case, we must ignore the redis exception and load the data from the database. In this case, to simplify the development, we just load the data in the catch exception.

### Email loading not cached

It will be fine to load and cache the emails received, we must have a policy to update those emails because, at the end, we can read dirty information if we are reading from the cache and we receive new emails. Also, must study about concurrency, because can happend that one thread is aggregating the cache while other can thread using the same accout its loading the email information with new emails received.

## Multi-thread

Glassfish allow multithread, and the application can work with it, thats why in many cases i used the singleton pattern, to not create many instances of an object, in order to avoid bad memory usage.

## Next steps

Something that will be nice is to dockerize the application. Push both separe applications in images in docker hub, and then, maybe, construct a docker-compose to create the three images in an instance, or just pull the images independently.

Idempotency treatment: request must be idempotent, so if happend to receive more than one time the request with the same id, musn`t do the request, but needs to return to the client the same results as the first one.

Sending email can get a lot of lag, so do it in an async way can help performance when we have many request. Also, its importante to analyse about checked exception


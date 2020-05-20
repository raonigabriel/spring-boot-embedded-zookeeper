# spring-boot-embedded-zookeeper
A small utility library that easily enables an embedded ZooKeeper server on SpringBoot apps.

## Maven import **(NOT WORKING YET!!) 
```xml
	<dependency>
		<groupId>com.github.raonigabriel</groupId>
		<artifactId>spring-boot-embedded-zookeeper</artifactId>
		<version>1.0.0-SNAPSHOT</version>
	</dependency>
```

## Usage
Just add @EnableEmbeddedZooKeeper to any @Configuration class as follows:
```java
package com.myco.myapp;

@SpringBootApplication
public class MyApp {

	public static void main(String[] args) {
		SpringApplication.run(MyApp.class, args);
	}
} 

```

## License

Released under the [Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0.html)
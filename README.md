[![Build Status](https://github.com/raonigabriel/spring-boot-embedded-zookeeper/workflows/Maven%20Build/badge.svg?branch=master)](https://github.com/raonigabriel/spring-boot-embedded-zookeeper/actions)
[![Codecov](https://codecov.io/gh/raonigabriel/spring-boot-embedded-zookeeper/branch/master/graph/badge.svg)](https://codecov.io/gh/raonigabriel/spring-boot-embedded-zookeeper)
[![Libraries](https://img.shields.io/librariesio/github/raonigabriel/spring-boot-embedded-zookeeper)](https://libraries.io/github/raonigabriel/spring-boot-embedded-zookeeper)
              https://img.shields.io/librariesio/github/raonigabriel/spring-boot-embedded-zookeeper

Spring Boot Embedded Zookeeper
-------------------
A small utility library that easily enables an embedded ZooKeeper server on SpringBoot apps.

### Using with Maven
Add the following dependency to your pom.xml:
```xml
<dependency>
    <groupId>io.github.raonigabriel</groupId>
    <artifactId>spring-boot-embedded-zookeeper</artifactId>
    <version>2.0.0</version>
</dependency>
```

## Usage
Just add @EnableEmbeddedZooKeeper to any @Configuration class as follows:
```java
package com.myco.myapp;

@EnableEmbeddedZooKeeper
@SpringBootApplication
public class MyApp {

    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
} 

```
Then you can inject the ZooKeeperServer bean on any Spring component, as following:
```java
package com.myco.myapp;

@Component
public class MyComponent {

	@Autowired
	ZooKeeperServer zookeeperServer;
	
	public void doSomething() {
		// Use the injected zookeeperServer
	}

}
```
To get the code:
-------------------
Clone the repository:
```
$ git clone https://github.com/raonigabriel/spring-boot-embedded-zookeeper.git
```
If this is your first time using Github, review http://help.github.com to learn the basics.

## License

Released under the [Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0.html)

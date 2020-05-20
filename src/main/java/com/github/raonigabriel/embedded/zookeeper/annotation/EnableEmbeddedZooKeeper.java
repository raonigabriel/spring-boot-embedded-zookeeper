package com.github.raonigabriel.embedded.zookeeper.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.zookeeper.server.ZooKeeperServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.github.raonigabriel.embedded.zookeeper.EmbeddedZooKeeperConfig;

/**
 * Enables an embedded Zookeper server instance that will run with the defaults.
 * To be used on @{@link Configuration} classes as follows:
 *
 * <pre class="code">
 * &#064;Configuration
 * &#064;EnableEmbeddedZooKeeper
 * public class AppConfig {
 *
 *     // various &#064;Bean definitions
 * }</pre>
 *
 * This enables injection of {@link ZooKeeperServer} Spring-managed
 * bean in the container. For example, given a class {@code MyComponent}
 *
 * <pre class="code">
 * package com.myco.component;
 *
 * &#064;Component
 * public class MyComponent {
 *
 *     &#064;Autowired
 *     ZooKeeperServer zookeeperServer;
 *
 * }</pre>
 *
 * @author Raoni Gabriel
 * @see EmbeddedZooKeeperConfig
 * @see ZooKeeperServer
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(EmbeddedZooKeeperConfig.class)
public @interface EnableEmbeddedZooKeeper {

	int port() default 2181;

	int tickTime() default 500;

	int maxConnections() default 1024;

	String zooDir() default "";

}
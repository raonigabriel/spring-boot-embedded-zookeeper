package io.github.raonigabriel.embedded.zookeeper;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.zookeeper.server.ZooKeeperServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.core.type.AnnotationMetadata;

import io.github.raonigabriel.embedded.zookeeper.annotation.EnableEmbeddedZooKeeper;

public class EmbeddedZooKeeperConfigTests {

	private EmbeddedZooKeeperConfig config;

	@BeforeEach
	public void setUp() {
		config = new EmbeddedZooKeeperConfig();
	}

	@Test
	public void given_empty_config_should_faild() {
		assertThrows(BeanInstantiationException.class, config::zookeeperServer);
	}

	@Test
	public void given_null_metadata_should_fail() {
		assertThrows(IllegalArgumentException.class, () -> {
			config.setImportMetadata(null);
		});
	}

	@Test
	public void given_invalid_metadata_should_fail() {
		assertThrows(IllegalArgumentException.class, () -> 
		config.setImportMetadata(AnnotationMetadata.introspect(String.class)));
	}

	@Test
	public void given_valid_metadata_should_set() {
		assertDoesNotThrow(() -> 
		config.setImportMetadata(AnnotationMetadata.introspect(MyApp.class)));
	}

	@Test
	public void given_valid_metadata_should_create_default_server() {
		config.setImportMetadata(AnnotationMetadata.introspect(MyApp.class));
		ZooKeeperServer server = config.zookeeperServer();
		assertNotNull(server);
		assertEquals(true, server.isRunning());
		assertEquals(2181, server.getClientPort());
		assertEquals(500, server.getTickTime());
		assertEquals(1024, server.getMaxClientCnxnsPerHost());
		assertDoesNotThrow(() -> server.shutdown());
		assertEquals(false, server.isRunning());
	}
	
	@Test
	public void given_valid_metadata_should_create_custom_server() {
		config.setImportMetadata(AnnotationMetadata.introspect(CustomApp.class));
		ZooKeeperServer server = config.zookeeperServer();
		assertNotNull(server);
		assertEquals(true, server.isRunning());
		assertEquals(2182, server.getClientPort());
		assertEquals(600, server.getTickTime());
		assertEquals(512, server.getMaxClientCnxnsPerHost());
		assertDoesNotThrow(() -> server.shutdown());
		assertEquals(false, server.isRunning());
	}
	
	@EnableEmbeddedZooKeeper(zooDir = "./target/", port = 2182, tickTime = 600, maxConnections = 512)
	private class CustomApp {
		
	}

}

package com.github.raonigabriel.embedded.zookeeper;

import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;package com.github.raonigabriel.embedded.zookeeper;

import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.zookeeper.server.NIOServerCnxnFactory;
import org.apache.zookeeper.server.ServerCnxnFactory;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import com.github.raonigabriel.embedded.zookeeper.annotation.EnableEmbeddedZooKeeper;

/**
 * {@code @Configuration} class that registers the Spring infrastructure bean necessary
 * to enable an embedded Zookeeper instance.
 *
 * @author Raoni Gabriel
 * @see EnableEmbeddedZooKeeper
 */
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class EmbeddedZooKeeperConfig implements ImportAware {

	@Nullable
	private AnnotationAttributes enableEmbeddedZooKeeper;

	@Bean(destroyMethod = "shutdown")
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	public ZooKeeperServer zookeeperServer() {
		try {
			if (enableEmbeddedZooKeeper == null) {
				throw new InvalidPropertyException(ZooKeeperServer.class, "enableEmbeddedZooKeeper", "Property cannot be null");
			}
			
			String zooDir = enableEmbeddedZooKeeper.getString("zooDir");
			int port = enableEmbeddedZooKeeper.getNumber("port");
			int maxConnections = enableEmbeddedZooKeeper.getNumber("maxConnections");
			int tickTime = enableEmbeddedZooKeeper.getNumber("tickTime");
			Path zooPath = null;

			if (StringUtils.isEmpty(zooDir)) {
				zooPath = Files.createTempDirectory("zookeeper");
				zooPath.toFile().deleteOnExit();
			} else {
				zooPath = Paths.get(zooDir);
			}

			ZooKeeperServer server = new ZooKeeperServer(zooPath.toFile(), zooPath.toFile(), tickTime);
			ServerCnxnFactory factory = new NIOServerCnxnFactory();
			System.setProperty("zookeeper.maxCnxns", Integer.toString(maxConnections));
			factory.configure(new InetSocketAddress(port), maxConnections);
			factory.startup(server);
			return server;
		} catch (Exception ex) {
			throw new BeanInstantiationException(EmbeddedZooKeeperConfig.class, "Failed to start Zookeeper", ex);
		}
	}

	@Override
	public void setImportMetadata(AnnotationMetadata importMetadata) {

		if (importMetadata == null) {
			throw new IllegalArgumentException("importMetadata cannot be null");
		}
		
		this.enableEmbeddedZooKeeper = AnnotationAttributes.fromMap(
				importMetadata.getAnnotationAttributes(EnableEmbeddedZooKeeper.class.getName()));

		if (this.enableEmbeddedZooKeeper == null) {
			throw new IllegalArgumentException(
					"@EnableEmbeddedZooKeeper is not present on importing class " + importMetadata.getClassName());
		}
	}

}

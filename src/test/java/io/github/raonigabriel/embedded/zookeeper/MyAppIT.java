package io.github.raonigabriel.embedded.zookeeper;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.zookeeper.server.ZooKeeperServer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
public class MyAppIT {

	@Autowired
	ZooKeeperServer zooKeeperServer;

	@Test
	void contextLoads(CapturedOutput output) {
		assertThat(zooKeeperServer).isNotNull();
		assertThat(output.getOut()).contains("Created server with tickTime 500");
		assertThat(output.getOut()).contains("maxCnxns configured value is 1024");
		assertThat(output.getOut()).contains("binding to port 0.0.0.0/0.0.0.0:2181");
		assertThat(zooKeeperServer.getClientPort()).isEqualTo(2181);
		assertThat(zooKeeperServer.getTickTime()).isEqualTo(500);
		assertThat(zooKeeperServer.getMaxClientCnxnsPerHost()).isEqualTo(1024);
	}

}

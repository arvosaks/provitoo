package ee.rik.provitoo;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.sql.SQLException;

@SpringBootApplication
public class ProvitooApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProvitooApplication.class, args);
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-web", "-webAllowOthers", "-webPort", "8082");
	}
}

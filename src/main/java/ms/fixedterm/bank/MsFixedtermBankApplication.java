package ms.fixedterm.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsFixedtermBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsFixedtermBankApplication.class, args);
	}

}

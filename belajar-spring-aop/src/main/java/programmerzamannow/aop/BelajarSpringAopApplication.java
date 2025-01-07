package programmerzamannow.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class BelajarSpringAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BelajarSpringAopApplication.class, args);
	}

}

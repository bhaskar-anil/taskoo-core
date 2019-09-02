package in.taskoo.core.application;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("in.taskoo")
public class TaskooCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskooCoreApplication.class, args);
	}
	
	@Bean
	  public Mapper mapper() {
	    return new DozerBeanMapper();
	  }

}

package in.taskoo.core.application;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan("in.taskoo")
@EnableJpaRepositories("in.taskoo.core.repository")
@EntityScan("in.taskoo.core.entity")
public class TaskooCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskooCoreApplication.class, args);
	}
	
	@Bean
	  public Mapper mapper() {
	    return new DozerBeanMapper();
	  }

}

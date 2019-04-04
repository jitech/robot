package br.com.robot.robot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

import br.com.robot.entity.User;
import br.com.robot.repository.UserRepository;

@RestController
@SpringBootApplication
@EntityScan(basePackages = { "br.com.robot.entity" })
@EnableJpaRepositories(basePackages = { "br.com.robot.repository" })
public class Application implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Override
	public void run(String... args) throws Exception {
    	userRepository.save(new User("UserA"));
    	userRepository.save(new User("UserB"));
    	userRepository.save(new User("UserC"));
    	System.out.println("Salvou usuarios");
    }
}
package com.koseksi.app;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.koseksi.app.modals.FileStorageProperties;
import com.koseksi.app.repository.BlogsMongoRepository;
import com.koseksi.app.repository.ChatsRepository;
import com.koseksi.app.repository.FileRepository;
import com.koseksi.app.repository.MessagesRepository;
import com.koseksi.app.repository.RoleRepository;
import com.koseksi.app.repository.UserRepository;
import com.koseksi.app.repository.UserRoleRepository;
import com.koseksi.pachipulusula.config.RootAppConfig;
import com.koseksi.pachipulusula.config.WebMVCConfig;

@SpringBootApplication
@Import({WebMVCConfig.class,RootAppConfig.class})
@EnableConfigurationProperties({
    FileStorageProperties.class
})
@EnableJpaRepositories(basePackageClasses = {
		UserRepository.class,
		BlogsMongoRepository.class,
		RoleRepository.class,
		UserRoleRepository.class,
		ChatsRepository.class,
		FileRepository.class,
		MessagesRepository.class
})
public class JwtProjectApplication {
	private static final Logger logger = LoggerFactory.getLogger(JwtProjectApplication.class);
	
	@Autowired
	private Environment environment;
    
	public static void main(String[] args) {
		logger.info("===============Spring Boot Application Started==========================");
		SpringApplication.run(JwtProjectApplication.class, args);
		logger.info("===============Spring Boot Application Stopped==========================");
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return  new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedHeaders("*").allowedOrigins("*");	
			}
		};
		
	}
	
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(environment.getProperty("spring.mail.host"));
		mailSender.setPort(Integer.valueOf(environment.getProperty("spring.mail.port")));
		mailSender.setUsername(environment.getProperty("spring.mail.username"));
		mailSender.setPassword(environment.getProperty("spring.mail.password"));

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "false");

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}
}

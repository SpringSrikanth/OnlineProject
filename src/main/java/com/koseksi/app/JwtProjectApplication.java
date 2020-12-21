package com.koseksi.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.koseksi.app.modals.FileStorageProperties;
import com.koseksi.app.repository.BlogsMongoRepository;
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
@EnableJpaRepositories(basePackageClasses = {UserRepository.class,BlogsMongoRepository.class,RoleRepository.class,UserRoleRepository.class})
public class JwtProjectApplication {
	private static final Logger logger = LoggerFactory.getLogger(JwtProjectApplication.class);
    
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
}

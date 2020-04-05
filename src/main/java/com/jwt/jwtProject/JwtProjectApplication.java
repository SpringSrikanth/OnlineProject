package com.jwt.jwtProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jwt.jwtProject.modals.RoleRepository;
import com.jwt.jwtProject.modals.UserRepository;
import com.jwt.jwtProject.modals.UserRoleRepository;
import com.koseksi.pachipulusula.config.RootAppConfig;
import com.koseksi.pachipulusula.config.WebMVCConfig;

@SpringBootApplication
@Import({WebMVCConfig.class,RootAppConfig.class})
@EnableJpaRepositories(basePackageClasses = {UserRepository.class,RoleRepository.class,UserRoleRepository.class})
public class JwtProjectApplication {
	private static final Logger logger = LoggerFactory.getLogger(JwtProjectApplication.class);
    
	public static void main(String[] args) {
		logger.info("===============Spring Boot Application Started==========================");
		SpringApplication.run(JwtProjectApplication.class, args);
		logger.info("===============Spring Boot Application Stopped==========================");
	}
}

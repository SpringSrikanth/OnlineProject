package com.koseksi.pachipulusula.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.koseksi.pachipulusula.dao")
public class PersistenceConfig {

}

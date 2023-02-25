package com.kevinmilet.myfreezerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@EnableJpaAuditing
public class MyfreezerApiApplication extends SpringBootServletInitializer {

//    @Bean
//    public ModelMapper modelMapper() {
//	return new ModelMapper();
//    }

    public static void main(String[] args) {
	SpringApplication.run(MyfreezerApiApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//	return builder.sources(MyfreezerApiApplication.class);
//    }

}

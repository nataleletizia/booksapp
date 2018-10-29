package com.okta.developer.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.stream.Stream;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
@EnableSwagger2
@EnableResourceServer
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
    ApplicationRunner init(BookRepository repository) {
		return args -> {
			Book[] books = new Book[4];
			books[0] = new Book("Umberto Eco", "Il nome della rosa", LocalDate.of(2001, Month.MARCH, 01), "Best seller");
			repository.save(books[0]);
			books[1] = new Book("Michael Ende", "The never ending story", LocalDate.of(1983, Month.MARCH, 01), "Best seller");
			repository.save(books[1]);
			books[2] = new Book("Umberto Eco", "A passo di gambero", LocalDate.of(2001, Month.MARCH, 01), "Best seller");
			repository.save(books[2]);
			books[3] = new Book("Jules Verne", "20 mila leghe sotto i mari", LocalDate.of(1870, Month.MARCH, 01), "Best seller");
			repository.save(books[3]);
			repository.findAll().forEach(System.out::println);
		};
	}

    @Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}

package com.alkemy.disney;

import com.alkemy.disney.models.responses.MovieRest;
import com.alkemy.disney.security.AppProperties;
import com.alkemy.disney.shared.dto.MovieDto;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class DisneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisneyApplication.class, args);
		System.out.println("Disney Application Started");
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

	@Bean(name = "AppProperties")
	public AppProperties appProperties() {
		return new AppProperties();
	}

	@Bean
	public ModelMapper modelMapper(){
		ModelMapper mapper = new ModelMapper();

		//skipeo el genero para que no haga un ciclo infinito al devolver MovieRest
		mapper.typeMap(MovieDto.class, MovieRest.class).addMappings(m -> m.skip(MovieRest::setGenres));
		return mapper;
	}

}
	
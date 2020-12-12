package com.bluetech.issuemanagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@OpenAPIDefinition(
        info = @Info(
                title = "Issue Management API Reference",
                version = "1.0.0",
                description = "Issue Management API",
                license = @License(name = "Apache 2.0"),
                contact = @Contact(name = "Yasin Kilinc", email = "yasinkilinc05@gmail.com")
        )
)
@SpringBootApplication
public class IssuemanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(IssuemanagementApplication.class, args);
    }

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
/*
    @Bean
    public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setResources(new Resource[]{new ClassPathResource("projects.json")});
        return factory;
    }
*/
}

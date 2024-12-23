package fr.uge.jee.springmvc;

import fr.uge.jee.springmvc.pokematch.Pokemon;
import fr.uge.jee.springmvc.reststudents.StudentController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner printAllBeans(ApplicationContext applicationContext) {
        return args -> {
            WebClient webClient = WebClient.create();
            Pokemon p = webClient.get()
                    .uri("http://pokeapi.co/api/v2/pokemon")
                    .retrieve()
                    .bodyToMono(Pokemon.class).block();
            System.out.println(p);
        };
    }
    @Bean
    WebClient getWebClient(WebClient.Builder defaultBuilder) {
        return defaultBuilder.exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024)).build()).build();
    }
}

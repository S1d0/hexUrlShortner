package pl.idzse.shortener.url;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShortenerConfig {

    @Bean
    ShortenerFacade facade(ShortUrlRepository repository, TransactionalService transactionService) {
        return new ShortenerFacade(repository, transactionService);
    }
}

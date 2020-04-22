package pl.idzse.shortener.url;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.idzse.shortener.domain.ShortDomainFacade;

@Configuration
public class ShortenerConfig {

    @Bean
    ShortenerFacade facade(ShortUrlRepository repository, TransactionalService transactionService, ShortDomainFacade domainFacade) {
        return new ShortenerFacade(repository, transactionService, domainFacade);
    }
}

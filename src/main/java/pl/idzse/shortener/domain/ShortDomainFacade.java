package pl.idzse.shortener.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ShortDomainFacade {
    private final ShortDomainRepository repository;
    public ShortDomain createShortDomain(String domain) {

        ShortDomain shortDomain = ShortDomain.builder()
                .originalDomain(domain)
                .shortDomain(ShortDomainCreator.create(domain))
                .creation(LocalDateTime.now())
                .build();

        return repository.save(shortDomain);
    }
}

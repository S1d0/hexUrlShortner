package pl.idzse.shortener.url;

import lombok.RequiredArgsConstructor;
import pl.idzse.shortener.domain.ShortDomain;
import pl.idzse.shortener.domain.ShortDomainCreator;
import pl.idzse.shortener.domain.ShortDomainFacade;
import pl.idzse.shortener.url.dto.OriginalUrlDto;
import pl.idzse.shortener.url.dto.ShortUrlDto;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class ShortenerFacade {
    private final ShortUrlRepository repository;
    private final TransactionalService txService;
    private final ShortDomainFacade domainFacade;

    @Transactional
    public ShortUrlDto createShortUrl(String url) {
        String domain = url.split("/")[0];
        String path = url.split("/")[1];

        ShortDomain shortDomain = domainFacade.createShortDomain(domain);

        return repository
                .findByOriginalUrlAndDomain(path, shortDomain)
                .orElseGet(() -> txService.save(new ShortUrl(path, shortDomain)))
                .getDto();
    }

    public OriginalUrlDto getOriginalUrl(String shortUrl) {
        Optional<ShortUrl> optionalUrl = repository.findByShortUrl(shortUrl);
        if (optionalUrl.isPresent()) {
            return optionalUrl.get().getOriginalDto();
        } else {
            return new OriginalUrlDto("");
        }
    }
}

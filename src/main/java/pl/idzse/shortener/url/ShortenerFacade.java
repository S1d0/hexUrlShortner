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

@RequiredArgsConstructor
public class ShortenerFacade {
    private final ShortUrlRepository repository;
    private final TransactionalService transactionService;
    private final ShortDomainFacade domainFacade;

    @Transactional
    public ShortUrlDto createShortUrl(String path) {
        String domain = path.split("/")[0];
        String url = path.split("/")[1];

        ShortDomain shortDomain = domainFacade.createShortDomain(domain);

        ShortUrl shortUrl = ShortUrl.builder()
                .originalUrl(url)
                .domain(shortDomain)
                .creation(LocalDateTime.now())
                .build();

        return transactionService.
                save(shortUrl)
                .getDto();
    }

    public OriginalUrlDto getOrginalUrl(String shortUrl) {
        Optional<ShortUrl> optionalUrl = repository.findByShortUrl(shortUrl);
        if (optionalUrl.isPresent()) {
            return optionalUrl.get().getOriginalDto();
        } else {
            return new OriginalUrlDto("");
        }
    }
}

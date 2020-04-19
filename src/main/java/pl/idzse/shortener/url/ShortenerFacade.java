package pl.idzse.shortener.url;

import lombok.RequiredArgsConstructor;
import pl.idzse.shortener.url.dto.OriginalUrlDto;
import pl.idzse.shortener.url.dto.ShortUrlDto;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
public class ShortenerFacade {
    private final ShortUrlRepository repository;
    private final TransactionalService transactionService;

    @Transactional
    public ShortUrlDto createShortUrl(String originalUrl) {
        ShortUrl shortUrl = ShortUrl.builder()
                .originalUrl(originalUrl)
                .build();
        ShortUrl url = transactionService.save(shortUrl);
        return url.getDto();
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

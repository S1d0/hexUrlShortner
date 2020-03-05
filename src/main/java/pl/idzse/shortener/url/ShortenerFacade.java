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
    public ShortUrlDto createShortUrl(String orginalUrl) {
        ShortUrl shortUrl = ShortUrl.builder()
                .originalUrl(orginalUrl)
                .build();
        ShortUrl url = transactionService.save(shortUrl);
        return url.getDto();
    }

    public OriginalUrlDto getOrginalUrl(String shortUrl) {
        Optional<ShortUrl> optionalUrl = repository.findByShortedUrl(shortUrl);
        if (optionalUrl.isPresent()) {
            return optionalUrl.get().getOriginalDto();
        } else {
            return new OriginalUrlDto("");
        }
    }
}

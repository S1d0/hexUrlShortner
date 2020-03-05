package pl.idzse.shortener.url;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.idzse.shortener.url.dto.OriginalUrlDto;
import pl.idzse.shortener.url.dto.ShortUrlDto;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class ShortnerController {
    private final ShortenerFacade facade;

    @GetMapping("/short")
    ShortUrlDto getShortUrl(@RequestParam String originalUrl) {
        return facade.createShortUrl(originalUrl);
    }

    @GetMapping("/original")
    OriginalUrlDto getOriginalUrl(@RequestParam String shortUrl) {
        return facade.getOrginalUrl(shortUrl);
    }
}

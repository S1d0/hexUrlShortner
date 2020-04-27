package pl.idzse.shortener.url;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
        return facade.getOriginalUrl(shortUrl);
    }

//    @PostMapping("/short")
//    ShortUrlDto create(@RequestBody ShortDomainDto dto) {
//        return null;
//    }
}

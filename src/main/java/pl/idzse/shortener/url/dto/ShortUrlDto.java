package pl.idzse.shortener.url.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
public class ShortUrlDto {
    private final String shortUrl;
    private final String shortDomain;
    private final String shortPath;
}

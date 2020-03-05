package pl.idzse.shortener.url

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
class TransactionalServiceTest extends Specification{
    @Autowired
    private TransactionalService transactionalService
    @Autowired
    private ShortUrlRepository repository

    @Transactional
    def "Should update entity whit short url"() {
        given:
        ShortUrl shortUrl = new ShortUrl()
        shortUrl.setOriginalUrl("https://spring.io")

        when:
        ShortUrl shortUrl1 = transactionalService.save(shortUrl)

        then:
        shortUrl1 != null
        shortUrl1.getShortedUrl() == "https://1"
        shortUrl1.getId() != null
        repository.findByShortedUrl(shortUrl.getShortedUrl()).isPresent()

    }


}

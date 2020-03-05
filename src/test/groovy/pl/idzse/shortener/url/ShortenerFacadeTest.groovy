package pl.idzse.shortener.url

import pl.idzse.shortener.url.dto.ShortUrlDto
import spock.lang.Shared
import spock.lang.Specification

import javax.persistence.EntityManager

class ShortenerFacadeTest extends Specification {
    @Shared
    ShortUrlRepository repository = new InMemoShortUrlRepository()
    ShortenerConfig config = new ShortenerConfig()
    EntityManager entityManager = Stub(EntityManager.class)
    TransactionalService transactionService = new TransactionalService(repository, entityManager)
    ShortenerFacade facade = config.facade(repository, transactionService)

    static String[] getWebsites() {
        return  [
        "youtube.com",
        "tmall.com",
        "baidu.com",
        "qq.com",
        "sohu.com",
        "facebook.com",
        "taobao.com",
        "login.tmall.com",
        "wikipedia.org",
        "yahoo.com",
        "amazon.com",
        "jd.com",
        "weibo.com",
        "sina.com",
        "live.com",
        "pages.tmall.com",
        "reddit.com",
        "vk.com",
        "netflix.com",
        "blogspot.com",
        "alipay.com",
        "csdn.net",
        "bing.com",
        "yahoo.co.jp",
        "Okezone.com",
        "instagram.com",
        "google.com",
        "office.com",
        "xinhuanet.com",
        "linkedin.com",
        "livejasmin.com",
        "microsoft.com",
        "twitter.com",
        "ebay.com",
        "Twitch.tv",
        "mail.ru",
        "pornhub.com",
        "naver.com",
        "stackoverflow.com",
        "aliexpress.com",
        "google.co.in",
        "yandex.ru",
        "tribunnews.com",
        "soso.com",
        "msn.com",
        "imdb.com",
        "bilibili.com",
        "whatsapp.com",
        "xvideos.com",
        "apple.com",
        "babytree.com",
        "github.com",
        "fandom.com",
        "xhamster.com",
        "booking.com",
        "paypal.com",
        "bbc.com",
        "pinterest.com",
        "sogou.com",
        "xnxx.com",
        "quora.com",
        "samsung.com",
        "redtube.com",
        "accuweather.com",
        "ampproject.org",
        "sm.cn"]
    }

    def setupSpec() {
        fillRepositoryWithRandomValues(repository)
    }

    def "ShortUrlRepository save in database long url and return id of row"() {
        when:
        entityManager.persist(_ as ShortUrl) >> {ShortUrl shortUrl -> {
            repository.save(shortUrl)
        }}
        ShortUrlDto dto = facade.createShortUrl("https://start.spring.io/")

        then:
        1 == 1
        repository.findByShortedUrl(dto.getShortUrl()).isPresent()
    }

    private static void fillRepositoryWithRandomValues(InMemoShortUrlRepository inMemoShortUrlRepository) {
        ShortUrl shortUrl = new ShortUrl()
        ShortUrlCreator creator = new ShortUrlCreator()
        String[] websites = getWebsites()
        for(int i=0; i < websites.size()-1; i++) {
            String originalUrl = "https://" + websites[i]
            shortUrl.setOriginalUrl(originalUrl)
            String shortenUrl = creator.getShortUrl(i)
            shortUrl.setShortedUrl(shortenUrl)
            inMemoShortUrlRepository.save(shortUrl)
        }
    }
}



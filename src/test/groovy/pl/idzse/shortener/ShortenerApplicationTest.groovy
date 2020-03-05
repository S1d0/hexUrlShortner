package pl.idzse.shortener

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ShortenerApplicationTest extends Specification {
    def "Main"() {
        expect:
        1==1
    }
}

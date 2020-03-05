package pl.idzse.shortener.url

import spock.lang.Specification

class ShortUrlCreatorTest extends Specification {
    ShortUrlCreator creator = new ShortUrlCreator()
    def "CreateShort"() {
        expect:
        creator.getBase62From10(idx) == output

        where:
        idx | output
        0   | '0'
        5   | '5'
        10  | 'a'
        61  | 'Z'
        62  | '10'
        63  | '11'
        124 | '20'
        197 |  '3b'
    }

    def "Create Long"() {
        expect:
        creator.getBase10From62(base62) == output

        where:
        base62 | output
        '0'    | 0
        '9'    | 9
        'a'    | 10
        '20'   | 124
        '3b'   | 197
        'b33'  | 42473
    }
}

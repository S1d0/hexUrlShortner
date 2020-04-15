package pl.idzse.shortener.url;

import org.springframework.data.domain.*;

import java.util.*;

class InMemoShortUrlRepository implements ShortUrlRepository {
    private Map<Long, ShortUrl> repository = new HashMap();
    private long uniqeId = 0;

    @Override
    public List<ShortUrl> findAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public List<ShortUrl> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ShortUrl> findAll(Pageable pageable) {
        return new PageImpl<ShortUrl>(new ArrayList<>(repository.values()), pageable, repository.size());
    }

    @Override
    public List<ShortUrl> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return repository.size();
    }

    @Override
    public void deleteById(Long id) {
        repository.remove(id);
    }

    @Override
    public void delete(ShortUrl shortUrl) {
    }

    @Override
    public void deleteAll(Iterable<? extends ShortUrl> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends ShortUrl> S save(S shortUrl) {
        uniqeId = uniqeId +1;
//        shortUrl.setId(uniqeId);
        repository.put(uniqeId, shortUrl);
        return shortUrl;
    }

    @Override
    public <S extends ShortUrl> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<ShortUrl> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends ShortUrl> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<ShortUrl> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public ShortUrl getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends ShortUrl> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ShortUrl> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ShortUrl> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ShortUrl> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ShortUrl> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ShortUrl> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public Optional<ShortUrl> findByShortedUrl(String shortedUrl) {
        return null;
    }
}

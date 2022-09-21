package com.goncharov.tinyurl.repository;

import com.goncharov.tinyurl.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Goncharov Aleksandr
 */
@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByAlias(String alias);

    void deleteByAlias(String alias);

    void deleteAllByExpirationDateBefore(LocalDateTime time);
}

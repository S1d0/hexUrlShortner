package pl.idzse.shortener.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ShortDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    String shortDomain;

    @Column
    String originalDomain;

    @Column(columnDefinition = "TIMESTAMP", updatable = false)
    LocalDateTime creation;
}
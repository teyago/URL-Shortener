package com.goncharov.tinyurl.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Url")
public class Url {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(4)
    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "alias", unique = true, nullable = false)
    private String alias;

    @Column(name = "number_of_clicks")
    private int numberOfClicks;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;
}

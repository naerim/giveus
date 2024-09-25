package com.giveus.funding.domain.review.domain;

import com.giveus.funding.domain.funding.domain.Funding;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;

@Entity
@Table(name = "review")
@Getter
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @Column(name = "review_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewNo;

    @OneToOne
    @JoinColumn(name = "funding_no")
    private Funding funding;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "url")
    @Setter
    private String url;

    @Builder
    public Review(Funding funding, String title, String content) {
        this.funding = funding;
        this.title = title;
        this.content = content;
    }
}

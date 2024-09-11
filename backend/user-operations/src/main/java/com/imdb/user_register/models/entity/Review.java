package com.imdb.user_register.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "review")
@Getter
@Setter
public class Review {
    @Id
    @Column(name = "review_id")
    private int reviewId;

    @NotNull
    private int titleId;

    @Column(name = "review_rating")
    @Max(10)
    @Min(1)
    private int rating;

    @Column(name = "review_text")
    private String text;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(name = "fk_review_user_id"))
    private User author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review)) return false;
        return reviewId == review.reviewId && titleId == review.titleId && rating == review.rating && Objects.equals(text, review.text) && Objects.equals(author, review.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, titleId, rating, text, author);
    }
}

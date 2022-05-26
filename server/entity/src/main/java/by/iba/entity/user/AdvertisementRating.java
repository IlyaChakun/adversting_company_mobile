package by.iba.entity.user;

import by.iba.common.domain.core.FullAbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "advertisement_reviews")
@Getter
@Setter
public class AdvertisementRating extends FullAbstractEntity {

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "review_message", length = 10000)
    private String reviewMessage;

}
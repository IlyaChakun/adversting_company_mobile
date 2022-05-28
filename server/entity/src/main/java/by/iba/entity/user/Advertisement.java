package by.iba.entity.user;

import by.iba.common.domain.core.FullAbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "advertisements")
@Getter
@Setter
public class Advertisement extends FullAbstractEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "picture")
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AdvertisementType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AdvertisementStatus status;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<AdvertisementRating> ratingSet = new HashSet<>();

    @Column(name = "rating_sum")
    private Double ratingSum;

    @OneToOne
    private User user;
}

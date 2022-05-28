package by.iba.specification;

import by.iba.entity.user.Advertisement;
import by.iba.entity.user.AdvertisementRating;
import by.iba.entity.user.AdvertisementType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public final class AdvertisementSpecification {

    private AdvertisementSpecification() {
    }

    public static Specification<Advertisement> sortByAdvertisementRating(List<AdvertisementRating> ratings) {
        return (Specification<Advertisement>) (root, query, builder) -> {
            List<Predicate> predicates = buildPredicates(root, builder, ratings);
            return
                    builder.or(predicates.toArray(new Predicate[predicates.size()]));

        };
    }

    private static List<Predicate> buildPredicates(Root<Advertisement> root,
                                                   CriteriaBuilder criteriaBuilder,
                                                   List<AdvertisementRating> ratings) {
        ///TODO join(types)
        List<Predicate> predicates = new ArrayList<>();
        ratings.forEach(rating -> predicates.add(
                criteriaBuilder.equal(root.join("advertisement_reviews").get("rating"), rating.getRating())));
        return predicates;
    }


    public static Specification<Advertisement> findByAdvertisementTypeLike(String advertisementType) {
        return (Specification<Advertisement>) (root, query, criteriaBuilder) ->
                criteriaBuilder
                        .like(root.get("type"), '%' + advertisementType + '%');
    }

}

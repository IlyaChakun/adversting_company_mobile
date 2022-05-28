package by.iba.specification;

import by.iba.entity.user.Advertisement;
import by.iba.entity.user.AdvertisementType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class AdvertisementSpecification {

    private AdvertisementSpecification() {
    }

    public static Specification<Advertisement> findAllByAdvertisementType(Set<String> types) {
        return (Specification<Advertisement>) (root, query, builder) -> {
            List<Predicate> predicates = buildPredicates(root, builder, types);
            return
                    builder.or(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    private static List<Predicate> buildPredicates(Root<Advertisement> root,
                                                   CriteriaBuilder criteriaBuilder,
                                                   Set<String> types) {
        List<Predicate> predicates = new ArrayList<>();

        types.forEach(type -> predicates.add(
                criteriaBuilder.equal(root.get("type"), AdvertisementType.valueOf(type))));

        return predicates;
    }

}

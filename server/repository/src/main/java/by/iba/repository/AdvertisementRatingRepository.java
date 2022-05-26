package by.iba.repository;

import by.iba.common.repository.BaseAbstractLongKeyRepository;
import by.iba.entity.user.AdvertisementRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRatingRepository extends BaseAbstractLongKeyRepository<AdvertisementRating> {
}

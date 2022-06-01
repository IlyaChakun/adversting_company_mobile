package by.iba.repository;

import by.iba.common.repository.BaseAbstractLongKeyRepository;
import by.iba.entity.user.Advertisement;
import by.iba.entity.user.AdvertisementStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends BaseAbstractLongKeyRepository<Advertisement> {

    Page<Advertisement> findAllByStatusEquals(Specification<Advertisement> specification, Pageable pageable, AdvertisementStatus status);

    Page<Advertisement> findAllByStatusEqualsOrStatusEqualsAndUserId(Pageable pageable, AdvertisementStatus firstStatus, AdvertisementStatus secondStatus, Long id);

   //Page<Advertisement> findAllByStatus(Pageable pageable, AdvertisementStatus firstStatus);

    Page<Advertisement> findAllByStatusIsNot(Pageable pageable, AdvertisementStatus firstStatus);

    Page<Advertisement> findAllByStatusEquals(Pageable pageable, AdvertisementStatus status);

}

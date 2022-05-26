package by.iba.repository;

import by.iba.common.repository.BaseAbstractLongKeyRepository;
import by.iba.entity.user.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends BaseAbstractLongKeyRepository<Advertisement> {

}

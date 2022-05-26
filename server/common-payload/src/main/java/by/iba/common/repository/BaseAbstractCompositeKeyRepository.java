package by.iba.common.repository;

import by.iba.common.domain.BaseCompositeKey;
import by.iba.common.domain.core.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseAbstractCompositeKeyRepository<T extends BaseEntity, K extends BaseCompositeKey>
        extends BaseAbstractRepository<T, K> {
}

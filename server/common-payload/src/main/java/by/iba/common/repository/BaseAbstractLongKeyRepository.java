package by.iba.common.repository;


import by.iba.common.domain.core.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseAbstractLongKeyRepository<T extends BaseEntity>
        extends BaseAbstractRepository<T, Long> {
}
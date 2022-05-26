package by.iba.common.repository;

import by.iba.common.domain.core.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseAbstractRepository<T extends BaseEntity, K>
        extends JpaRepository<T, K>, JpaSpecificationExecutor<T> {
}
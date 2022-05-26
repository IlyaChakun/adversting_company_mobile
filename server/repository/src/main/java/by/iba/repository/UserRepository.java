package by.iba.repository;

import by.iba.common.repository.BaseAbstractLongKeyRepository;
import by.iba.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseAbstractLongKeyRepository<User> {

    Optional<User> findByEmail(String email);

}

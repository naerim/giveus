package com.giveus.user.repository;

import com.giveus.user.entity.User;
import java.util.Optional;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepositoryCustom {
    Optional<User> findUserByUserId(String userId);

}

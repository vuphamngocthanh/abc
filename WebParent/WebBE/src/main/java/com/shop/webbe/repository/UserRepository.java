package com.shop.webbe.repository;



import com.shop.webcommon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
//    Page<User> findAllByFullNameContaining(String fullName, Pageable pageable);
    @Query("SELECT u FROM User  u WHERE u.username = :username")
    Optional<User> findByName(@Param("username") String username);

}

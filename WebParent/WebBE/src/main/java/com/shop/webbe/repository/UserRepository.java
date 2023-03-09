package com.shop.webbe.repository;

import com.shop.webcommon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User  u WHERE u.username = :username")
    Optional<User> findByName(@Param("username") String username);

    @Query(  nativeQuery = true,
            value = "select * from users where fullname like "+"%"+ ":name"+"%")
    List<User> findAllByNameContaining(@Param("name") String name);

}

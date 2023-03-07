package com.shop.webbe.repository;



import com.shop.webcommon.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {


    @Query("Select u.email FROM Customer u where u.email  = :email")
    String findEmail(@Param(value = "email") String email);
    @Override
    Optional<Customer> findById(Long aLong);
}

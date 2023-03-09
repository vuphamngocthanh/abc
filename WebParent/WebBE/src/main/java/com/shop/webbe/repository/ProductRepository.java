package com.shop.webbe.repository;


import com.shop.webcommon.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    @Query( value = "SELECT * FROM products WHERE name LIKE %:name%", nativeQuery = true)
    List<Product> findAllByNameProduct(@Param("name") String name);
}

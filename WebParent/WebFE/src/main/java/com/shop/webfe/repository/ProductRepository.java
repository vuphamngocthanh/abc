package com.shop.webfe.repository;



import com.shop.webcommon.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
    @Query(nativeQuery = true,
            value = "select * from products order by created_at desc limit 5")
    List<Product> findAllByCreateAt();
}

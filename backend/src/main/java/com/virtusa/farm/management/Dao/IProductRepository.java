package com.virtusa.farm.management.Dao;
import com.virtusa.farm.management.DTO.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByCategory(String category);

     Product findByName(String name);

    Product findByQuantity(Long quantity);
//    @Query(value = "select * from farm.product where name like %:keyword or category like %:keyword%",nativeQuery = true)
//    List<Product> findByKeyword(@Param("keyword") String keyword);
}

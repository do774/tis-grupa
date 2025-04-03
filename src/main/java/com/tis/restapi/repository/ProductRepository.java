package com.tis.restapi.repository;

import com.tis.restapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findByCode(String code);

//	Nasao sam JPA implementaciju, malo ljepše riješenje, pa ovo zakomentiram
//	@Query(value = "SELECT * FROM PRODUCT p " + "WHERE LOWER(p.CODE) LIKE '%' || LOWER(:code) || '%' "
//			+ "AND LOWER(p.NAME) LIKE '%' || LOWER(:name) || '%'", nativeQuery = true)
//	List<Product> searchProducts(@Param("code") String code, @Param("name") String name);

	List<Product> findByCodeContainingIgnoreCaseAndNameContainingIgnoreCase(String code, String name);
}
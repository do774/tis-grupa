package com.tis.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tis.restapi.model.Review;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByProductId(Long productId);
}

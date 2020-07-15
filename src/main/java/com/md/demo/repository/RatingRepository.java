package com.md.demo.repository;

import com.md.demo.model.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Integer> {

	@Query("SELECT r FROM Rating r JOIN r.itemId item where item.id = :id")
	List<Rating> findAllRatingByItemId(Integer id);
}

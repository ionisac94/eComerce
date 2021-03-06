package com.md.demo.repository;

import com.md.demo.model.Item;
import com.md.demo.model.Rating;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Integer> {

	@Transactional
	@Query("SELECT r FROM Rating r JOIN r.item item WHERE item.id = :id")
	List<Rating> findAllRatingByItemId(Integer id);

	@Transactional
	@Query("SELECT averageRating FROM Item item WHERE item.id = :itemId")
	Double getAverageRatingByItemId(Integer itemId);

	@Transactional
	@Query("SELECT AVG(r.value) FROM Rating r WHERE r.item = :itemId")
	Double getAverageRating(Item itemId);

	@Transactional
	@Query("UPDATE Rating r SET r.value = :newValue WHERE r.id = :id")
	@Modifying
	void updateRatingValue(Integer newValue, Integer id);
}

package com.md.demo.repository;

import com.md.demo.model.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

	@Query("SELECT c FROM Comment c JOIN c.item item where item.id = :id")
	List<Comment> findAllCommentsByItemId(Integer id);

	@Query("UPDATE Comment c SET c.content = :newContent, c.version = c.version + 1 WHERE c.id = :commentId")
	@Modifying
	void updateContent(String newContent, Integer commentId);
}

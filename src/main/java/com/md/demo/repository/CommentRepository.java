package com.md.demo.repository;

import com.md.demo.model.Comment;
import com.md.demo.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
	List<Comment> findAllByItem(Item item);
}

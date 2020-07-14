package com.md.demo.service;

import com.md.demo.model.Comment;
import com.md.demo.model.Item;
import com.md.demo.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
public class CommentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemService.class);

	private CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = requireNonNull(commentRepository, "commentRepository can not be null");
	}

	public List<Comment> getAllComments(Integer ItemId) {
		LOGGER.info("About getting comments from DB");

		List<Comment> allByItem = commentRepository.findAllCommentsByItemId(ItemId);

		if (allByItem == null) {
			return Collections.emptyList();
		} else {
			return allByItem;
		}
	}

	public void deleteComment(Integer id) {
		commentRepository.deleteById(id);
	}

	public Comment findCommentById(Integer id) {
		Optional<Comment> optionalComment = commentRepository.findById(id);

		return optionalComment.orElse(null);
	}
}

package com.md.demo.service;

import com.md.demo.exception.NoSuchCommentExistException;
import com.md.demo.model.Comment;
import com.md.demo.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
public class CommentServiceImpl implements CommentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

	private CommentRepository commentRepository;

	public CommentServiceImpl(CommentRepository commentRepository) {
		this.commentRepository = requireNonNull(commentRepository, "commentRepository can not be null");
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public List<Comment> getAllComments(Integer itemId) {
		LOGGER.info("About getting comments from DB");

		List<Comment> allByItem = commentRepository.findAllCommentsByItemId(itemId);

		if (allByItem == null) {
			return Collections.emptyList();
		} else {
			return allByItem;
		}
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Comment findCommentById(Integer id) {
		LOGGER.info("About getting comment by id from DB");
		Optional<Comment> optionalComment = commentRepository.findById(id);
		return optionalComment.orElseThrow(() -> new NoSuchCommentExistException("No such comment in DB"));
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void deleteComment(Integer id) {
		LOGGER.info("About removing comment by id from DB");
		Comment commentById = findCommentById(id);
		if (commentById.getId() != null) {
			commentRepository.deleteById(id);
		} else {
			throw new NoSuchCommentExistException("No such comment in DB");
		}
	}
}

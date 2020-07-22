package com.md.demo.service;

import com.md.demo.exception.NoSuchCommentExistException;
import com.md.demo.model.Comment;
import com.md.demo.model.Item;
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

	private ItemService itemService;

	public CommentServiceImpl(CommentRepository commentRepository, ItemService itemService) {
		this.commentRepository = requireNonNull(commentRepository, "commentRepository can not be null");
		this.itemService = requireNonNull(itemService, "itemService can not be null");
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public List<Comment> getAllComments(Integer itemId) {
		LOGGER.info("About getting comments from DB");

		List<Comment> allByItem = commentRepository.findAllCommentsByItemId(itemId);

		if (allByItem == null) {
			return Collections.emptyList();
		} else {
			return allByItem;
		}
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
	public Comment findCommentById(Integer id) {
		Optional<Comment> optionalComment = commentRepository.findById(id);
		LOGGER.info("Fetched a Comment from DB: {}", optionalComment);

		return optionalComment.orElseThrow(() -> new NoSuchCommentExistException("No such Comment in DB!"));
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false)
	public boolean isCommentDeleted(Integer id) {
		boolean commentIsDeleted;
		LOGGER.warn("Checking if Comment with id: {} exists in DB", id);
		Comment commentById = findCommentById(id);
		LOGGER.info("Comment with id {} exists in DB", id);
		if (commentById.getId() != null) {
			commentRepository.deleteById(id);
			return commentIsDeleted = true;
		} else {
			throw new NoSuchCommentExistException("No such Comment exists in DB!");
		}
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Comment addComment(Integer itemId, String comment) {
		Item itemById = itemService.getItemById(itemId);
		Comment newComment = Comment.builder().content(comment).item(itemById).build();

		return commentRepository.save(newComment);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Comment createComment(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Comment modifyComment(Integer itemId, Integer commentId, String newContent) {
		List<Comment> commentsByItemId = commentRepository.findAllCommentsByItemId(itemId);

		Comment commentToUpdate = commentsByItemId
				.stream()
				.filter(e -> e.getId().equals(commentId))
				.findFirst().get();

		String actualContent = commentToUpdate.getContent();
		actualContent = newContent;
		commentToUpdate.setContent(actualContent);

		return commentRepository.save(commentToUpdate);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void updateComment(String newContent, Integer commentId) {
		findCommentById(commentId);
		commentRepository.updateContent(newContent, commentId);
	}
}

package com.md.demo.service;

import com.md.demo.exception.NoSuchCommentExistException;
import com.md.demo.model.Comment;
import com.md.demo.model.Item;
import com.md.demo.repository.CommentRepository;
import com.md.demo.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
public class CommentServiceImpl implements CommentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

	private CommentRepository commentRepository;

	private ItemRepository itemRepository;

	private ItemService itemService;

	public CommentServiceImpl(CommentRepository commentRepository, ItemService itemService, ItemRepository itemRepository) {
		this.commentRepository = requireNonNull(commentRepository, "commentRepository can not be null");
		this.itemRepository = requireNonNull(itemRepository, "itemRepository ca not be null");
		this.itemService = requireNonNull(itemService, "itemService can not be null");
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
		LOGGER.info("Fetching a comment from DB with id {}", id);
		Optional<Comment> optionalComment = commentRepository.findById(id);
		return optionalComment.orElseThrow(() -> new NoSuchCommentExistException("No such comment in DB"));
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public boolean isCommentDeleted(Integer id) {
		boolean commentIsDeleted;
		LOGGER.info("About removing comment by id from DB");
		Comment commentById = findCommentById(id);
		if (commentById.getId() != null) {
			commentRepository.deleteById(id);
			return commentIsDeleted = true;
		} else {
			throw new NoSuchCommentExistException("No such comment in DB");
		}
	}

	@Override
	public Comment addComment(Integer itemId, String comment) {
		Item itemById = itemService.getItemById(itemId);
		Comment newComment = Comment.builder().content(comment).itemId(itemById).build();
		commentRepository.save(newComment);
		return newComment;
	}

	@Override
	public Comment modifyComment(Integer itemId, Integer commentId, String newContent) {
		List<Comment> commentsByItemId = commentRepository.findAllCommentsByItemId(itemId);

		List<Comment> collect = commentsByItemId
				.stream()
				.filter(e -> e.getId() == commentId)
				.collect(Collectors.toList());
		Comment commentToUpdate = collect.get(0);

		String actualContent = commentToUpdate.getContent();
		actualContent = newContent;
		commentToUpdate.setContent(actualContent);
		commentRepository.save(commentToUpdate);
		return commentToUpdate;
	}
}

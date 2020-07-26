package com.md.demo.service;

import com.md.demo.model.Comment;
import com.md.demo.model.Item;
import com.md.demo.repository.CommentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CommentServiceTest {

	@InjectMocks
	private CommentServiceImpl commentService;

	@Mock
	private CommentRepository commentRepository;

	@Mock
	private ItemService itemService;

	private List<Comment> comments;

	private Comment comment;

	@Before
	public void setUp() {
		comment = new Comment();
		comment.setId(1);
		comment.setContent("good content");
		comment.setDatePosted(LocalDateTime.now());
		comment.setVersion(1);
		comment.setItem(Item.builder().averageRating(2.3).title("good item").id(1).build());

		comments = new ArrayList<>();
		comments.add(comment);
	}

	@Test
	public void testGetAllComments() {

		Mockito.when(commentRepository.findAllCommentsByItemId(anyInt())).thenReturn(comments);

		List<Comment> expectedAllComments = commentService.getAllCommentsByItemId(1);

		Assert.assertEquals("should be one comment", 1, expectedAllComments.size());

		Mockito.verify(commentRepository, times(1)).findAllCommentsByItemId(anyInt());
	}

	@Test
	public void testFindCommentById() {

		Mockito.when(commentRepository.findById(anyInt())).thenReturn(Optional.ofNullable(comment));

		Comment expectedComment = commentService.findCommentById(1);

		Assert.assertNotNull("comment should not be null", expectedComment);

		Mockito.verify(commentRepository, times(1)).findById(anyInt());
	}

	@Test
	public void testCommentIsDeleted() {

		Mockito.when(commentRepository.findById(anyInt())).thenReturn(Optional.ofNullable(comment));

		Comment expectedComment = commentService.findCommentById(1);

		boolean commentDeleted = commentService.isCommentDeleted(1);

		Assert.assertNotNull("comment should not be null", expectedComment);
		Assert.assertTrue("comment should be deleted", commentDeleted);

		Mockito.verify(commentRepository, times(2)).findById(anyInt());
		Mockito.verify(commentRepository, times(1)).deleteById(anyInt());
	}

	// TODO
//	@Test
//	public void testAddComment() {
//
//		Mockito.when(itemService.getItemById(any())).thenReturn(any());
//
//		Comment expectedComment = commentService.addComment(1, "new Comment");
//
//		Assert.assertNotNull("comment should not be null", expectedComment);
//
//		Mockito.verify(itemService, times(1)).getItemById(anyInt());
//	}

	@Test
	public void testCreateComment() {

		Mockito.when(commentRepository.save(any(Comment.class))).thenReturn(comment);

		Comment expectedComment = commentService.createComment(comment);

		Assert.assertNotNull("expectedComment should not be null", expectedComment);

		Mockito.verify(commentRepository, times(1)).save(any());
	}

// TODO
//	@Test
//	public void testModifyComment() {
//
//		Mockito.when(commentRepository.findAllCommentsByItemId(any())).thenReturn(comments);
//
//		Comment expectedComment = commentService.modifyComment(1, 1, "hello");
//
//		Assert.assertNotNull("expectedComment should not be null", expectedComment);
//
//		Mockito.verify(commentRepository, times(1)).save(any());
//	}

}

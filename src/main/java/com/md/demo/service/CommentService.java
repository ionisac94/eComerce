package com.md.demo.service;

import com.md.demo.model.Comment;

import java.util.List;

public interface CommentService {

	List<Comment> getAllComments(Integer itemId);

	Comment findCommentById(Integer id);

	void deleteComment(Integer id);
}

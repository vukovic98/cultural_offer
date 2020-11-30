package com.ftn.kts_nvt.helper;

import com.ftn.kts_nvt.beans.Comment;
import com.ftn.kts_nvt.dto.CommentDTO;

public class CommentMapper implements MapperInterface<Comment, CommentDTO> {

	@Override
	public Comment toEntity(CommentDTO dto) {
		Comment comment = new Comment();
		
		comment.setCommentId(dto.getId());
		comment.setContent(dto.getContent());
		comment.setImages(dto.getImages());
		
		return comment;
	}

	@Override
	public CommentDTO toDto(Comment entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

package com.ftn.kts_nvt.helper;

import java.util.ArrayList;
import java.util.List;

import com.ftn.kts_nvt.beans.Comment;
import com.ftn.kts_nvt.dto.CommentDTO;

public class CommentMapper implements MapperInterface<Comment, CommentDTO> {

	@Override
	public Comment toEntity(CommentDTO dto) {
		Comment comment = new Comment();
		
		comment.setCommentId(dto.getId());
		comment.setContent(dto.getContent());
		comment.setImage(dto.getImage());
		
		return comment;
	}

	@Override
	public CommentDTO toDto(Comment entity) {
		CommentDTO dto = new CommentDTO();
		
		dto.setContent(entity.getContent());
		dto.setId(entity.getCommentId());
		dto.setImage(entity.getImage());
		
		return dto;
	}
	
	public List<CommentDTO> listToDto(List<Comment> comments) {
		List<CommentDTO> dtos = new ArrayList<CommentDTO>();
		for(Comment c : comments) {
			dtos.add(toDto(c));
		}
		
		return dtos;
	}
	
}

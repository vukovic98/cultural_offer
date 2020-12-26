package com.ftn.kts_nvt.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ftn.kts_nvt.beans.Comment;
import com.ftn.kts_nvt.dto.CommentUserDTO;

@Component
public class CommentMapper implements MapperInterface<Comment, CommentUserDTO> {

	@Override
	public CommentUserDTO toDto(Comment entity) {
		CommentUserDTO dto = new CommentUserDTO();
		
		dto.setCommenterEmail(entity.getCommenter().getEmail());
		dto.setCommenterName(entity.getCommenter().getFirstName()+" "+entity.getCommenter().getLastName());
		dto.setContent(entity.getContent());
		dto.setId(entity.getCommentId());
		dto.setImage(entity.getImage());

		return dto;
	}
	
	public List<CommentUserDTO> listToDto(List<Comment> comments) {
		List<CommentUserDTO> dtos = new ArrayList<CommentUserDTO>();
		for(Comment c : comments) {
			dtos.add(toDto(c));
		}
		
		return dtos;
	}

	@Override
	public Comment toEntity(CommentUserDTO dto) {
		Comment c = new Comment();
		
		c.setContent(dto.getContent());
		c.setImage(dto.getImage());
		c.setApproved(true);
		c.setCommentId(dto.getId());

		return c;
	}
	
}

package com.ftn.kts_nvt.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.kts_nvt.beans.Comment;
import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.dto.CommentUserDTO;
import com.ftn.kts_nvt.repositories.CommentRepository;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;

@Component
public class CommentMapper implements MapperInterface<Comment, CommentUserDTO> {

	@Autowired
	private CulturalOfferRepository offerRepository;

	@Override
	public CommentUserDTO toDto(Comment entity) {
		CommentUserDTO dto = new CommentUserDTO();
		
		dto.setCommenterEmail(entity.getCommenter().getEmail());
		dto.setCommenterName(entity.getCommenter().getFirstName()+" "+entity.getCommenter().getLastName());
		dto.setContent(entity.getContent());
		dto.setId(entity.getCommentId());
		dto.setImage(entity.getImage());
		
		CulturalOffer offer = this.offerRepository.getOfferByComment(entity.getCommentId());
		dto.setOffer(offer.getName());
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
		// TODO Auto-generated method stub
		return null;
	}
	
}

package com.ftn.kts_nvt.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.Grade;
import com.ftn.kts_nvt.dto.CulturalOfferDTO;
import com.ftn.kts_nvt.dto.CulturalOfferDetailsDTO;

@Component
public class CulturalOfferMapper implements MapperInterface<CulturalOffer, CulturalOfferDTO> {

	@Override
	public CulturalOffer toEntity(CulturalOfferDTO dto) {
		
		CulturalOffer offer = new CulturalOffer();
		
		offer.setId(dto.getId());
		offer.setComments(new ArrayList<>());
		offer.setDescription(dto.getDescription());
		offer.setImages(dto.getImages());
		offer.setLocation(dto.getLocation());
		offer.setName(dto.getName());
		offer.setPosts(new ArrayList<>());
		offer.setSubscribedUsers(new ArrayList<>());
		
		return offer;
	}

	public CulturalOfferDetailsDTO toDetailsDTO(CulturalOffer entity) {
		PostMapper postMapper = new PostMapper();
		CommentMapper commentMapper = new CommentMapper();
		GradeMapper gradeMapper = new GradeMapper();
		CulturalOfferTypeMapper typeMapper = new CulturalOfferTypeMapper();
		CulturalOfferDetailsDTO dto = new CulturalOfferDetailsDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		dto.setLocation(entity.getLocation());
		dto.setImages(entity.getImages());
		dto.setPosts(postMapper.listToDto(entity.getPosts()));
		dto.setComments(commentMapper.listToDto(entity.getComments()));
		dto.setGrades(gradeMapper.toGradeDTOList(entity.getGrades()));
		dto.setType(typeMapper.toDto(entity.getType()));
		
		if(entity.getGrades() != null && entity.getGrades().size() != 0) {
			double sum = 0;
			for(Grade g : entity.getGrades()) {
				sum += g.getValue();
			}
			dto.setAvgGrade(sum/entity.getGrades().size());
		}else {
			dto.setAvgGrade(0);
		}
		
		if(entity.getSubscribedUsers() == null) {
			dto.setSubscribersCount(0);
		}else {
			dto.setSubscribersCount(entity.getSubscribedUsers().size());
		}
		
		if(entity.getPosts() == null) {
			dto.setPostsCount(0);
		}else {
			dto.setPostsCount(entity.getPosts().size());
		}
		
		if(entity.getComments() == null) {
			dto.setCommentsCount(0);
		}else {
			dto.setCommentsCount(entity.getComments().size());
		}
		return dto;
	}
	
	@Override
	public CulturalOfferDTO toDto(CulturalOffer entity) {
		CulturalOfferDTO offer = new CulturalOfferDTO();
		
		offer.setId(entity.getId());
		offer.setDescription(entity.getDescription());
		offer.setImages(entity.getImages());
		offer.setLocation(entity.getLocation());
		offer.setName(entity.getName());
		
		if(entity.getGrades() != null && entity.getGrades().size() != 0) {
			double sum = 0;
			for(Grade g : entity.getGrades()) {
				sum += g.getValue();
			}
			offer.setAvgGrade(sum/entity.getGrades().size());
		}else {
			offer.setAvgGrade(0);
		}
		if(entity.getSubscribedUsers() == null) {
			offer.setSubscribersCount(0);
		}else {
			offer.setSubscribersCount(entity.getSubscribedUsers().size());
		}
		
		return offer;
	}
	
	public List<CulturalOfferDTO> listToDTO(List<CulturalOffer> offers) {
		List<CulturalOfferDTO> dtos = new ArrayList<CulturalOfferDTO>();
		
		for(CulturalOffer c : offers) {
			dtos.add(toDto(c));
		}
		
		return dtos;
	}

}

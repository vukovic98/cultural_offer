package com.ftn.kts_nvt.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ftn.kts_nvt.beans.Post;
import com.ftn.kts_nvt.dto.PostDTO;


@Component
public class PostMapper implements MapperInterface<Post, PostDTO>{

	public PostMapper() {}
	
	@Override
	public Post toEntity(PostDTO dto) {		
		CulturalOfferMapper mapper = new CulturalOfferMapper();
		return new Post(dto.getTitle(), dto.getContent(), dto.getPostTime(), mapper.toEntity(dto.getOffer()));
	}

	@Override
	public PostDTO toDto(Post entity) {
		CulturalOfferMapper mapper = new CulturalOfferMapper();
		return new PostDTO(entity.getPostId(), entity.getTitle(),
						entity.getContent(), entity.getPostTime(), mapper.toDto(entity.getOffer()));
	}
	
	public ArrayList<PostDTO> listToDto(List<Post> list) {
		ArrayList<PostDTO> dtos = new ArrayList<>();
		
		for(Post p : list) {
			dtos.add(toDto(p));
		}
		
		return dtos;
	}

}

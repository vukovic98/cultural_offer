package com.ftn.kts_nvt.helper;

import java.time.Instant;

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.Post;
import com.ftn.kts_nvt.dto.PostDTO;

public class PostMapper implements MapperInterface<Post, PostDTO>{

	public PostMapper() {}
	
	@Override
	public Post toEntity(PostDTO dto) {		
		return new Post(dto.getTitle(), dto.getContent(), dto.getPostTime(), dto.getOffer());
	}

	@Override
	public PostDTO toDto(Post entity) {
		return new PostDTO(entity.getPostId(), entity.getTitle(),
						entity.getContent(), entity.getPostTime(), entity.getOffer());
	}

}
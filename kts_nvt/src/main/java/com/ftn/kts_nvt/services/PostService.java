package com.ftn.kts_nvt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.Post;
import com.ftn.kts_nvt.repositories.PostRepository;

@Service
public class PostService implements ServiceInterface<Post>{

	@Autowired
	private PostRepository postRepository;

	@Override
	public List<Post> findAll() {
        return postRepository.findAll();
	}

	@Override
	public Post findOne(Long id) {
        return postRepository.findById(id).orElse(null);
	}

	@Override
	public Post create(Post entity) throws Exception {
        return postRepository.save(entity);
	}

	@Override
	public Post update(Post entity, Long id) throws Exception {
		Post existingPost =  postRepository.findById(id).orElse(null);
        if(existingPost == null){
            throw new Exception("Post with given id doesn't exist");
        }
        existingPost.setContent(entity.getContent());
        existingPost.setOffer(entity.getOffer());
        existingPost.setPostTime(entity.getPostTime());
        existingPost.setTitle(entity.getTitle());
        
        return postRepository.save(existingPost);
	}

	@Override
	public void delete(Long id) throws Exception {
		Post existingPost = postRepository.findById(id).orElse(null);
	    if(existingPost == null){
	    	throw new Exception("Post with given id doesn't exist");
	    }
	    postRepository.delete(existingPost);
	}
}

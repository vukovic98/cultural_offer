package com.ftn.kts_nvt.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.kts_nvt.beans.Comment;
import com.ftn.kts_nvt.beans.Image;
import com.ftn.kts_nvt.repositories.CommentRepository;
import com.ftn.kts_nvt.repositories.ImageRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	public Comment save(Comment c) {
		return this.commentRepository.save(c);
	}
	
	public ArrayList<Comment> findAll() {
		return (ArrayList<Comment>) this.commentRepository.findAll();
	}
	
	public Page<Comment> findAll(Pageable pagable) {
		return this.commentRepository.findAll(pagable);
	}
	
	public Comment findById(long id) {
		Optional<Comment> foundOptional =  this.commentRepository.findById(id);
		
		if(foundOptional.isPresent()) {
			Comment found = foundOptional.get();
			return found;
		}
		else
			return null;
	}
	
	public boolean delete(Comment c) {
		boolean exists = this.commentRepository.existsById(c.getCommentId());
		
		if(exists)
			this.commentRepository.delete(c);
		
		return exists;
	}
	
	public boolean deleteById(long id) {
		try {
			Comment exists = this.commentRepository.getOne(id);
			
			Image image = exists.getImage();
			
			if(image != null) {
				
				exists.setImage(null);
				imageRepository.delete(image);
				
			}
			
			this.commentRepository.delete(exists);
			return true;
		}catch(Exception e){
			return false;
		}
		
	}
	
	public Comment update(Comment changedComment, long id) {
		Optional<Comment> oldComment = this.commentRepository.findById(id);
		
		if(oldComment.isPresent()) {
			Comment found = oldComment.get();
			
			found.setContent(changedComment.getContent());
			found.setImage(changedComment.getImage());
			
			return this.commentRepository.save(found);
		} else {
			return null;
		}
	}

	public Page<Comment> findAllPendingComments(Pageable pagable) {
	
		return this.commentRepository.findAllPendingComments(pagable);
	}

	
}

package com.ftn.kts_nvt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.Comment;
import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.Image;
import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.dto.CommentDTO;
import com.ftn.kts_nvt.dto.NewCommentDTO;
import com.ftn.kts_nvt.repositories.CommentRepository;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;
import com.ftn.kts_nvt.repositories.ImageRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private CulturalOfferRepository culturalOfferRepository;
	
	@Autowired
	private ImageService imageService;

	@Autowired
	private RegisteredUserService registeredUserService;

	public Comment save(Comment c) {
		return this.commentRepository.save(c);
	}
	
	public ResponseEntity<CommentDTO> save(NewCommentDTO commentDto, RegisteredUser user) {
		
		CulturalOffer offer = culturalOfferRepository.getOne(commentDto.getOfferId());
		Image image = null;
		if(commentDto.getImage() != null) {
			image = new Image(commentDto.getImage());
			imageRepository.save(image);
		}
		
		Comment newComment = new Comment(commentDto.getContent(), image, user);
		Comment ok = commentRepository.save(newComment);
		if (offer != null) {
			offer.getComments().add(newComment);
			culturalOfferRepository.save(offer);

			user.getComments().add(ok);
			this.registeredUserService.save(user);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (ok != null) {
			CommentDTO ret = new CommentDTO(ok.getCommentId(), ok.getContent(), ok.getImage());
			return new ResponseEntity<>(ret, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

	public ArrayList<Comment> findCommentsForOffer(long offer_id) {
		return this.commentRepository.findCommentsForOffer(offer_id);
	}
	
	public ArrayList<Comment> findCommentsForUser(long user_id) {
		return this.commentRepository.findCommentsForUser(user_id);
	}

	public Page<Comment> findCommentsForOffer(long offer_id, Pageable pageable) {
		return this.commentRepository.findCommentsForOffer(offer_id, pageable);
	}

	public ArrayList<Comment> findAll() {
		return (ArrayList<Comment>) this.commentRepository.findAll();
	}

	public Page<Comment> findAll(Pageable pagable) {
		return this.commentRepository.findAll(pagable);
	}

	public Comment findById(long id) {
		return this.commentRepository.findById(id).orElse(null);
	}

	public boolean delete(Comment c) {
		boolean exists = this.commentRepository.existsById(c.getCommentId());
		if (exists)
			this.commentRepository.delete(c);

		return exists;
	}

	public boolean deleteById(long id) {
		try {
			Comment exists = this.commentRepository.findById(id).orElse(null);
			System.out.println(exists.getContent());
			Image image = exists.getImage();

			if (image != null) {

//				exists.setImage(null);
//				
//				this.commentRepository.save(exists);
//
//				imageRepository.delete(image);

			}
			System.out.println("prodje2");
			CulturalOffer offer = this.culturalOfferRepository.getOfferByComment(exists.getCommentId());
			System.out.println(offer.getName());
			System.out.println("prodje3" + offer);

			List<Comment> comments = this.commentRepository.findCommentsForOffer(offer.getId());
			System.out.println(comments.get(comments.indexOf(exists)).getContent());
			comments.remove(exists);
			offer.setComments(comments);
			
			this.culturalOfferRepository.save(offer);
			System.out.println("prodje4");

			RegisteredUser user = this.registeredUserService.findOne(exists.getCommenter().getId());
			System.out.println(user.getEmail());
			
			List<Comment> userComments = this.commentRepository.findCommentsForUser(exists.getCommenter().getId());
			System.out.println(userComments.get(userComments.indexOf(exists)).getContent());
			userComments.remove(exists);
			user.setComments(userComments);
			
			this.registeredUserService.save(user);
			
			this.commentRepository.delete(exists);
			System.out.println("prodje5");
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Comment update(Comment changedComment, long id) {
		Optional<Comment> oldComment = this.commentRepository.findById(id);

		if (oldComment.isPresent()) {
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

	public Comment approve(long id) {
		Comment c = this.commentRepository.findById(id).orElse(null);
		if (c != null) {
			c.setApproved(true);
			return this.commentRepository.save(c);
		} else
			return null;
	}

}

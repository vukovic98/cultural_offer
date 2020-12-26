package com.ftn.kts_nvt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.Comment;
import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.Image;
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

	public Comment save(Comment c) {
		return this.commentRepository.save(c);
	}
	
	public ArrayList<Comment> findCommentsForOffer(long offer_id) {
		return this.commentRepository.findCommentsForOffer(offer_id);
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
			System.out.println("prodje1");
			Image image = exists.getImage();

			if (image != null) {

				exists.setImage(null);
				imageRepository.delete(image);

			}
			System.out.println("prodje2");
			CulturalOffer offer = this.culturalOfferRepository.getOfferByComment(exists.getCommentId());
			System.out.println("prodje3" + offer);
			
			List<Comment> comments = this.commentRepository.findCommentsForOffer(offer.getId());
			comments.remove(exists);
			offer.setComments(comments);
			this.culturalOfferRepository.save(offer);
			System.out.println("prodje4");

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
		}
		else
			return null;
	}

}

package com.ftn.kts_nvt.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.ftn.kts_nvt.beans.Comment;
import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.dto.CommentUserDTO;
import com.ftn.kts_nvt.dto.NewCommentDTO;
import com.ftn.kts_nvt.helper.CommentMapper;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;
import com.ftn.kts_nvt.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CulturalOfferRepository offerRepository;

	@Autowired
	private CommentMapper mapper;

	// GET: http://localhost:8080/comments
	@GetMapping
	public ResponseEntity<ArrayList<CommentUserDTO>> findAll() {
		ArrayList<Comment> comments = this.commentService.findAll();
		ArrayList<CommentUserDTO> dtos = (ArrayList<CommentUserDTO>) this.mapper.listToDto(comments);
		if (!comments.isEmpty())
			return new ResponseEntity<ArrayList<CommentUserDTO>>(dtos, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// GET: http://localhost:8080/comments/by-page
	@GetMapping(value = "/by-page/{pageNum}")
	public ResponseEntity<Page<CommentUserDTO>> getAllCulturalContentCategories(@PathVariable int pageNum) {
		Pageable pageRequest = PageRequest.of(pageNum - 1, 10);

		Page<Comment> page = this.commentService.findAll(pageRequest);
		List<CommentUserDTO> commentDTOS = mapper.listToDto(page.toList());
		Page<CommentUserDTO> pageCommentDTOS = new PageImpl<>(commentDTOS, page.getPageable(), page.getTotalElements());

		return new ResponseEntity<>(pageCommentDTOS, HttpStatus.OK);
	}

	// GET: http://localhost:8080/comments/{id}
	@GetMapping(path = "/{id}")
	public ResponseEntity<Comment> findById(@PathVariable("id") long id) {
		Comment found = this.commentService.findById(id);

		if (found != null)
			return new ResponseEntity<Comment>(found, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// POST: http://localhost:8080/comments -> RequestBody (DTO)
	@PostMapping(consumes = "application/json")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<HttpStatus> create(@Valid @RequestBody NewCommentDTO commentDto) {

		//Comment newComment = this.mapper.toEntity(commentDto);

		RegisteredUser user = (RegisteredUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// TODO Add logged user to comment

		Comment newComment = new Comment(commentDto.getContent(), commentDto.getImage(), user);
		CulturalOffer offer = offerRepository.getOne(commentDto.getOfferId());
		

		
		
		Comment ok = this.commentService.save(newComment);

		if(offer != null) {
			offer.getComments().add(newComment);
			offerRepository.save(offer);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if (ok != null) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// DELETE http://localhost:8080/comments -> RequestBody (DTO)
	@DeleteMapping(consumes = "application/json")
	@PreAuthorize("hasAnyRole('ROLE_USER,ROLE_ADMIN')")
	public ResponseEntity<HttpStatus> deleteEntity(@RequestBody CommentUserDTO commentDto) {

		Comment comment = this.commentService.findById(commentDto.getId());

		if (comment != null) {
			boolean ok = this.commentService.delete(comment);
			if (ok)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	// DELETE http://localhost:8080/comments/{id}
	@DeleteMapping(path = "/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_USER')")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
		boolean ok = this.commentService.deleteById(id);

		if (ok)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// PUT: http://localhost:8080/comments/{id} -> RequestBody (DTO)
	@PutMapping(path = "/{id}", consumes = "application/json")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<HttpStatus> update(@PathVariable("id") long id, @RequestBody CommentUserDTO commentDto) {

		CommentMapper mapper = new CommentMapper();

		Comment changedComment = mapper.toEntity(commentDto);

		Comment newComment = this.commentService.update(changedComment, id);

		if (newComment != null)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	// GET: http://localhost:8080/comments/pendingComments/1
	@GetMapping(path = "/pendingComments/{pageNum}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<CommentUserDTO>> findAllPendingComments(@PathVariable int pageNum) {
		Pageable pageRequest = PageRequest.of(pageNum - 1, 5);
		Page<Comment> page = this.commentService.findAllPendingComments(pageRequest);
		List<CommentUserDTO> pendingCommentsDto = this.mapper.listToDto(page.toList());
		Page<CommentUserDTO> pagePendingCommentsDtop = new PageImpl<>(pendingCommentsDto, page.getPageable(),
				page.getTotalElements());

		return new ResponseEntity<>(pagePendingCommentsDtop, HttpStatus.OK);
	}
	
	// PUT: http://localhost:8080/comments/approve/{id} 
	@PutMapping(path = "/approve/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<HttpStatus> approve(@PathVariable("id") long id) {
		Comment newComment = this.commentService.approve(id);
		if (newComment != null)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

}

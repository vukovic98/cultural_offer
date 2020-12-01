package com.ftn.kts_nvt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.data.domain.Pageable;

import com.ftn.kts_nvt.beans.Comment;
import com.ftn.kts_nvt.dto.CommentDTO;
import com.ftn.kts_nvt.helper.CommentMapper;
import com.ftn.kts_nvt.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	private CommentMapper mapper;

	// GET: http://localhost:8080/comments
	@GetMapping
	public ResponseEntity<ArrayList<Comment>> findAll() {
		ArrayList<Comment> comments = this.commentService.findAll();

		if (!comments.isEmpty())
			return new ResponseEntity<ArrayList<Comment>>(comments, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// GET: http://localhost:8080/comments/by-page
	@GetMapping(value = "/by-page")
	public ResponseEntity<Page<CommentDTO>> getAllCulturalContentCategories(Pageable pageable) {
		Page<Comment> page = this.commentService.findAll(pageable);
		List<CommentDTO> commentDTOS = mapper.listToDto(page.toList());
		Page<CommentDTO> pageCommentDTOS = new PageImpl<>(commentDTOS, page.getPageable(), page.getTotalElements());

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
	public ResponseEntity<HttpStatus> create(@RequestBody CommentDTO commentDto) {
		CommentMapper mapper = new CommentMapper();

		Comment newComment = mapper.toEntity(commentDto);

		// String currentUser =
		// SecurityContextHolder.getContext().getAuthentication().getName();

		// TODO Add logged user to comment

		Comment ok = this.commentService.save(newComment);

		if (ok != null) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// DELETE http://localhost:8080/comments -> RequestBody (DTO)
	@DeleteMapping(consumes = "application/json")
	public ResponseEntity<HttpStatus> deleteEntity(@RequestBody CommentDTO commentDto) {

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
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
		boolean ok = this.commentService.deleteById(id);

		if (ok)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	// PUT: http://localhost:8080/comments/{id} -> RequestBody (DTO)
	@PutMapping(path = "/id", consumes = "application/json")
	public ResponseEntity<HttpStatus> update(@PathVariable("id") long id, @RequestBody CommentDTO commentDto) {

		CommentMapper mapper = new CommentMapper();

		Comment changedComment = mapper.toEntity(commentDto);

		Comment newComment = this.commentService.update(changedComment, id);

		if (newComment != null)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

}

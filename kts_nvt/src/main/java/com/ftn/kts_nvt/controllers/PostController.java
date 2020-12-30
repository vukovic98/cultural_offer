package com.ftn.kts_nvt.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.kts_nvt.beans.Post;
import com.ftn.kts_nvt.dto.PostDTO;
import com.ftn.kts_nvt.helper.PostMapper;
import com.ftn.kts_nvt.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private PostMapper postMapper;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PostDTO>> getAllPosts() {
		List<Post> posts = postService.findAll();
		return new ResponseEntity<>(toPostDTOList(posts), HttpStatus.OK);
	}

	@GetMapping(path = "/by-page/{pageNum}")
	public ResponseEntity<Page<PostDTO>> findAll(@PathVariable int pageNum) {
		Pageable pageRequest = PageRequest.of(pageNum - 1, 10);

		Page<Post> page = this.postService.findAll(pageRequest);

		List<PostDTO> postDTOS = this.postMapper.listToDto(page.toList());
		Page<PostDTO> pagePostDTOS = new PageImpl<>(postDTOS, page.getPageable(), page.getTotalElements());

		return new ResponseEntity<>(pagePostDTOS, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PostDTO> getPost(@PathVariable Long id) {
		Post post = postService.findOne(id);
		if (post == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(postMapper.toDto(post), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {
		Post post;
		try {
			post = postService.create(postMapper.toEntity(postDTO));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(postMapper.toDto(post), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable Long id) {
		Post post = new Post(postDTO.getTitle(), postDTO.getContent(), postDTO.getPostTime());
		try {
			post = postService.update(post, id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(this.postMapper.toDto(post), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> deletePost(@PathVariable Long id) {
		try {
			postService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	private List<PostDTO> toPostDTOList(List<Post> posts) {
		List<PostDTO> postDTOS = new ArrayList<>();
		for (Post post : posts) {
			postDTOS.add(postMapper.toDto(post));
		}
		return postDTOS;
	}
}

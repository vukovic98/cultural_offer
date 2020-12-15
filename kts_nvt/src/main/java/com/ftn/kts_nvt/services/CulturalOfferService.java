package com.ftn.kts_nvt.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.CulturalOfferType;
import com.ftn.kts_nvt.beans.Post;
import com.ftn.kts_nvt.dto.CulturalOfferAddDTO;
import com.ftn.kts_nvt.repositories.CulturalOfferCategoryRepository;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;
import com.ftn.kts_nvt.repositories.PostRepository;

@Service
public class CulturalOfferService {

	@Autowired
	private CulturalOfferRepository culturalOfferRepository;
	
	@Autowired
	private CulturalOfferTypeService type;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private GeoLocationService location;
	
	public CulturalOffer save(CulturalOffer c) {
		return this.culturalOfferRepository.save(c);
	}
	
	public CulturalOffer save(CulturalOfferAddDTO dto) {
		CulturalOffer c = new CulturalOffer();
		c.setName(dto.getName());
		c.setDescription(dto.getDescription());
		c.setComments(new ArrayList<>());
		c.setGrades(new ArrayList<>());
		c.setPosts(new ArrayList<>());
		c.setSubscribedUsers(new ArrayList<>());
		c.setLocation(location.save(dto.getLocation()));		
		c.setType(type.findById(dto.getType()));
		return this.culturalOfferRepository.save(c);
	}
	
	public ArrayList<CulturalOffer> findAll() {
		return (ArrayList<CulturalOffer>) this.culturalOfferRepository.findAll();
	}

	public Page<CulturalOffer> findAll(Pageable pageable) {
		return (Page<CulturalOffer>) this.culturalOfferRepository.findAll(pageable);
	}

	public CulturalOffer findById(long id) {
		Optional<CulturalOffer> found = this.culturalOfferRepository.findById(id);

		if (found.isPresent()) {
			return found.get();
		} else
			return null;
	}

	public boolean delete(CulturalOffer c) {
		boolean exists = this.culturalOfferRepository.existsById(c.getId());

		if (exists)
			this.culturalOfferRepository.delete(c);

		return exists;
	}

	@Transactional
	public boolean deleteById(long id) {
		boolean exists = this.culturalOfferRepository.existsById(id);
		CulturalOffer c = null;
		if (exists) {
			c = this.culturalOfferRepository.findById(id).orElse(null);
			if(c != null) {
				List<Post> newPosts = c.getPosts();
				c.setPosts(new ArrayList<>());
				this.culturalOfferRepository.save(c);
				System.out.println(newPosts);
				for(Post p : newPosts) {
					p.setOffer(null);
					this.postRepository.save(p);
					this.postRepository.delete(p);
					
				}
				
				this.culturalOfferRepository.deleteById(id);
			}
		}
		return exists;
	}

	public CulturalOffer update(CulturalOffer changedOffer, long id) {
		Optional<CulturalOffer> foundOptional = this.culturalOfferRepository.findById(id);

		if (foundOptional.isPresent()) {
			CulturalOffer found = foundOptional.get();
			found.setName(changedOffer.getName());
			found.setDescription(changedOffer.getDescription());
			found.setImages(changedOffer.getImages());
			found.setLocation(location.update(changedOffer.getLocation(), changedOffer.getId()));
			return this.culturalOfferRepository.save(found);
		} else
			return null;
	}

	public Page<CulturalOffer> filter(Pageable pageRequest, String exp) {
		return this.culturalOfferRepository.filter(pageRequest, exp);
	}
}

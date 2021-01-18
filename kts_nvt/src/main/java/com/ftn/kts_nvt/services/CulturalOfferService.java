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
import com.ftn.kts_nvt.beans.GeoLocation;
import com.ftn.kts_nvt.beans.Image;
import com.ftn.kts_nvt.beans.Post;
import com.ftn.kts_nvt.dto.CulturalOfferAddDTO;
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

	@Autowired
	private ImageService imageService;

	public CulturalOffer save(CulturalOffer c) {
		return this.culturalOfferRepository.save(c);
	}

	public List<CulturalOffer> findByName(String name) {
		return this.culturalOfferRepository.findByName(name);
	}

	@Transactional
	public CulturalOffer save(CulturalOfferAddDTO dto) {
		List<CulturalOffer> found = this.culturalOfferRepository.findByName(dto.getName());

		if (found.isEmpty()) {
			ArrayList<Image> images = new ArrayList<>();

			if (dto.getImages() != null) {
				for (byte[] a : dto.getImages()) {
					System.out.println(a);
					Image i = new Image(a);
					this.imageService.save(i);
					images.add(i);
				}
			}

			CulturalOffer c = new CulturalOffer();
			c.setName(dto.getName());
			c.setDescription(dto.getDescription());
			c.setComments(new ArrayList<>());
			c.setGrades(new ArrayList<>());
			c.setPosts(new ArrayList<>());
			c.setSubscribedUsers(new ArrayList<>());
			c.setLocation(location.save(dto.getLocation()));
			c.setType(type.findById(dto.getType()));
			c.setImages(images);

			return this.culturalOfferRepository.save(c);
		} else
			return null;
	}

	public ArrayList<CulturalOffer> findAll() {
		return (ArrayList<CulturalOffer>) this.culturalOfferRepository.findAll();
	}

	public Page<CulturalOffer> findAll(Pageable pageable) {
		return (Page<CulturalOffer>) this.culturalOfferRepository.findAll(pageable);
	}

	public CulturalOffer findById(long id) {
		CulturalOffer found = this.culturalOfferRepository.findById(id).orElse(null);

		if (found != null) {
			return found;
		} else
			return null;
	}

	@Transactional
	public boolean deleteById(long id) {
		boolean exists = this.culturalOfferRepository.existsById(id);
		CulturalOffer c = null;
		if (exists) {
			c = this.culturalOfferRepository.findById(id).orElse(null);
			if (c != null) {
				List<Post> newPosts = c.getPosts();
				c.setPosts(new ArrayList<>());
				this.culturalOfferRepository.save(c);
				System.out.println(newPosts);
				for (Post p : newPosts) {
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

			List<CulturalOffer> nameFound = this.culturalOfferRepository.findByName(changedOffer.getName());

			if (nameFound.isEmpty()) {
				found.setName(changedOffer.getName());
				found.setDescription(changedOffer.getDescription());
				GeoLocation lc = location.findById(found.getLocation().getLocationId());
				lc.setLatitude(changedOffer.getLocation().getLatitude());
				lc.setLongitude(changedOffer.getLocation().getLongitude());
				lc.setPlace(changedOffer.getLocation().getPlace());
				GeoLocation savedLocation = location.save(lc);
				found.setLocation(savedLocation);
				// found.setImages(changedOffer.getImages());
				return this.culturalOfferRepository.save(found);
			} else
				return null;
		} else
			return null;
	}

	public Page<CulturalOffer> filter(Pageable pageRequest, String exp, ArrayList<String> types) {
		if (!exp.equals("") && types.size() == 0)
			return this.culturalOfferRepository.filter(pageRequest, exp);
		else if (!exp.equals("") && types.size() > 0)
			return this.culturalOfferRepository.filter(pageRequest, exp, types);
		else if (exp.equals("") && types.size() > 0)
			return this.culturalOfferRepository.filter(pageRequest, types);
		else
			return null;
	}
}

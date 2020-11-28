package com.ftn.kts_nvt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.Monument;
import com.ftn.kts_nvt.repositories.MonumentRepository;

@Service
public class MonumentService implements ServiceInterface<Monument>{

	@Autowired
	private MonumentRepository monumentRepository;

	@Override
	public List<Monument> findAll() {
		return monumentRepository.findAll();
	}

	@Override
	public Monument findOne(Long id) {
		return monumentRepository.findById(id).orElse(null);
	}

	@Override
	public Monument create(Monument entity) throws Exception {
		return monumentRepository.save(entity);
	}

	@Override
	public Monument update(Monument entity, Long id) throws Exception {
		Monument existingMonument =  monumentRepository.findById(id).orElse(null);
        if(existingMonument == null){
            throw new Exception("Monument with given id doesn't exist");
        }
        existingMonument.setDescription(entity.getDescription());
        existingMonument.setLocation(entity.getLocation());
        existingMonument.setName(entity.getName());
        existingMonument.setYear(entity.getYear());
        existingMonument.setComments(entity.getComments());
        existingMonument.setImages(entity.getImages());
        existingMonument.setPosts(entity.getPosts());
        existingMonument.setSubscribedUsers(entity.getSubscribedUsers());

        return monumentRepository.save(existingMonument);
	}

	@Override
	public void delete(Long id) throws Exception {
		Monument existingMonument = monumentRepository.findById(id).orElse(null);
        if(existingMonument == null){
            throw new Exception("Monument with given id doesn't exist");
        }
        monumentRepository.delete(existingMonument);
	}
}

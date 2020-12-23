package com.ftn.kts_nvt.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.kts_nvt.beans.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	@Query(
			value = "select * from post where post.offer_id = ?1",
			nativeQuery = true)
	public ArrayList<Post> findPostsForOffer(Long id);
}

package com.ftn.kts_nvt.repositories;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.kts_nvt.beans.Comment;
import com.ftn.kts_nvt.beans.CulturalOffer;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	@Query(value="select * from comment where comment.approved=false", nativeQuery = true)
	Page<Comment> findAllPendingComments(Pageable pagable);



}

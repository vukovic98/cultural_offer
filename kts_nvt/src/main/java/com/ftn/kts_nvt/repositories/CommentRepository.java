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
	
	@Query(
			value="SELECT comment_id, approved, content, commenter_id, image_id FROM (kts_nvt_test.cultural_offer c inner join cultural_offer_comment com on c.cultural_offer_id = com.cultural_offer_id) inner join comment cm on cm.comment_id = com.comments_comment_id where c.cultural_offer_id = ?1",
			nativeQuery = true
			)
	ArrayList<Comment> findCommentsForOffer(long offer_id);

}

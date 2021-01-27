package com.ftn.kts_nvt.services;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.Comment;
import com.ftn.kts_nvt.beans.CulturalOffer;
import com.ftn.kts_nvt.beans.Post;
import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.beans.User;
import com.ftn.kts_nvt.beans.VerificationCode;
import com.ftn.kts_nvt.repositories.CulturalOfferRepository;
import com.ftn.kts_nvt.repositories.PostRepository;

@Service
public class PostService implements ServiceInterface<Post>{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CulturalOfferRepository offerRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public List<Post> findAll() {
        return postRepository.findAll(Sort.by("postTime").ascending());
	}
	
	public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
	}

	@Override
	public Post findOne(Long id) {
        return postRepository.findById(id).orElse(null);
	}

	@Override
	public Post create(Post entity){
        Post added = postRepository.save(entity);
        
        entity.getOffer().getPosts().add(added);
        this.offerRepository.save(entity.getOffer());
        this.sendMailToSubscribedUsers(entity.getOffer(), added.getTitle());
        
        return added;
        
	}
	
	public String createMailBody(RegisteredUser u, CulturalOffer c, String title) {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<h2>" + u.getFirstName() + ", new post for offer you are subscribed to has arrived!</h2><br><br>");
		sb.append("<h3>" + title + "</h3> <br><br>");
		sb.append("<h4>Go check it out <a href='https://localhost:4200/cultural-offer/offer-details/" + c.getId() + "'>here!</a></h4>");
		
		return sb.toString();
	}
	
	@Async
	public void sendMailToSubscribedUsers(CulturalOffer c, String title) {
		try {
			for(RegisteredUser u : c.getSubscribedUsers()) {
				MimeMessage msg = this.javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(msg, true);
	
				helper.setTo(u.getEmail());
				helper.setSubject(c.getName() + " : New post is here!");
	
				helper.setText(createMailBody(u, c, title), true);
				this.javaMailSender.send(msg);
	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Page<Post> findPostsForOffer(long offer_id, Pageable pageable) {
		return postRepository.findPostsForOffer(offer_id, pageable);
	}
	
	@Override
	public Post update(Post entity, Long id) throws Exception {
		Post existingPost =  postRepository.findById(id).orElse(null);
        if(existingPost == null){
            throw new Exception("Post with given id doesn't exist");
        }
        
        existingPost.setContent(entity.getContent());
        existingPost.setPostTime(entity.getPostTime());
        existingPost.setTitle(entity.getTitle());
        
        return postRepository.save(existingPost);
	}

	@Override
	public void delete(Long id) throws Exception {
		Post existingPost = postRepository.findById(id).orElse(null);
	    if(existingPost == null){
	    	throw new Exception("Post with given id doesn't exist");
	    }
	    
	    CulturalOffer offer = offerRepository.findById(existingPost.getOffer().getId()).orElse(null);
	    
	    List<Post> posts = offer.getPosts();
	    posts.remove(existingPost);
	    
	    offer.setPosts(posts);
	    offerRepository.save(offer);
	    postRepository.delete(existingPost);
	}
}

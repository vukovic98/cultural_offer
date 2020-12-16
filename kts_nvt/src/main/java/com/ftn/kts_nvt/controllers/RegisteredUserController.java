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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.beans.User;
import com.ftn.kts_nvt.dto.ChangePasswordDto;
import com.ftn.kts_nvt.dto.CulturalOfferDTO;
import com.ftn.kts_nvt.dto.UserDTO;
import com.ftn.kts_nvt.dto.UserNameDto;
import com.ftn.kts_nvt.helper.CulturalOfferMapper;
import com.ftn.kts_nvt.helper.UserMapper;
import com.ftn.kts_nvt.services.RegisteredUserService;

@RestController
@RequestMapping("/registeredUser")
public class RegisteredUserController {

	@Autowired
	private RegisteredUserService registeredUserService;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CulturalOfferMapper offerMapper;

	@GetMapping()
	public ResponseEntity<List<UserDTO>> getAllRegisteredUsers() {
		List<RegisteredUser> users = registeredUserService.findAll();

		return new ResponseEntity<>(userMapper.toUserDTORegUserList(users), HttpStatus.OK);
	}
	
    @GetMapping(value = "/by-page/{pageNum}")
 	public ResponseEntity<Page<UserDTO>> getAll(@PathVariable int pageNum) {
    	Pageable pageable = PageRequest.of(pageNum, 10);
 		Page<RegisteredUser> page = registeredUserService.findAll(pageable);
 		List<UserDTO> userDTOS = userMapper.toUserDTORegUserList(page.toList());
 		Page<UserDTO> pageUserDTOS = new PageImpl<>(userDTOS,
										 				page.getPageable(), 
										 				page.getTotalElements());

 		return new ResponseEntity<>(pageUserDTOS, HttpStatus.OK);
 	}

    @GetMapping(value = "/subscribedItems")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ArrayList<CulturalOfferDTO>> getSubscribedItems() {
		Authentication data = SecurityContextHolder.getContext().getAuthentication();
		String email = data.getName();
		
		RegisteredUser user = this.registeredUserService.findOneByEmail(email);
		
		if(user != null) {
			ArrayList<CulturalOfferDTO> dtos = (ArrayList<CulturalOfferDTO>) this.offerMapper.listToDTO(user.getCulturalOffers());

	 		return new ResponseEntity<>(dtos, HttpStatus.OK);
		} else 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/subscribe")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<HttpStatus> subscribe(@RequestParam("offer_id") Long id) {
		String email =  SecurityContextHolder.getContext().getAuthentication().getName();
		RegisteredUser user = this.registeredUserService.findOneByEmail(email);
		
		if(user != null) {
			boolean ok = this.registeredUserService.subscribe(user, id);
			
			if(ok)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    
    
    @DeleteMapping(value = "/unsubscribe")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<HttpStatus> unsubscribe(@RequestParam("offer_id") Long id) {
    	
    	Authentication data = SecurityContextHolder.getContext().getAuthentication();
		String email = data.getName();
		
		RegisteredUser user = this.registeredUserService.findOneByEmail(email);
		
		if(user != null) {
			boolean ok = this.registeredUserService.unsubscribe(user, id);
			
			if(ok)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
    @GetMapping(value="/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
    	System.out.println("getRegisteredUser with id = " + id);
        RegisteredUser user = registeredUserService.findOne(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    	System.out.println("getRegisteredUser " + user);

        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }
    
    @RequestMapping(value="byEmail/{email}", method=RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email){
        User user = registeredUserService.findOneByEmail(email);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        RegisteredUser user;
        try {
             user = registeredUserService.create((RegisteredUser)userMapper.toEntity(userDTO));
        } catch (Exception e) {
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.CREATED);
    }
	
    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id){
    	RegisteredUser user;
        try {
            user = registeredUserService.update((RegisteredUser)userMapper.toEntity(userDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }
    

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        try {
        	registeredUserService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}

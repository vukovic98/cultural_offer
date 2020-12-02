package com.ftn.kts_nvt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.kts_nvt.beans.Admin;
import com.ftn.kts_nvt.dto.UserDTO;
import com.ftn.kts_nvt.helper.UserMapper;
import com.ftn.kts_nvt.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping()
	public ResponseEntity<List<UserDTO>> getAllRegisteredUsers() {
		List<Admin> users = adminService.findAll();

		return new ResponseEntity<>(userMapper.toUserDTOAdminList(users), HttpStatus.OK);
	}
	
    @GetMapping(value = "/by-page")
 	public ResponseEntity<Page<UserDTO>> getAll(Pageable pageable) {
 		Page<Admin> page = adminService.findAll(pageable);
 		List<UserDTO> userDTOS = userMapper.toUserDTOAdminList(page.toList());
 		Page<UserDTO> pageUserDTOS = new PageImpl<>(userDTOS,
										 				page.getPageable(), 
										 				page.getTotalElements());

 		return new ResponseEntity<>(pageUserDTOS, HttpStatus.OK);
 	}
    
    @GetMapping(value="/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
    	System.out.println("getAdmin with id = " + id);
        Admin user = adminService.findOne(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    	System.out.println("getAdmin " + user);

        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        Admin user;
        try {
             user = adminService.create((Admin)userMapper.toEntity(userDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Long id){
    	Admin user;
        try {
            user = adminService.update((Admin)userMapper.toEntity(userDTO), id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        try {
        	adminService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

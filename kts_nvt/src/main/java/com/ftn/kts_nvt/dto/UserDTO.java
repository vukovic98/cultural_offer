package com.ftn.kts_nvt.dto;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDTO {

	private Long id;

	
	@NotBlank(message = "First name cannot be empty.")
	private String firstName;

	@NotBlank(message = "Last name cannot be empty.")
	private String lastName;

	@NotBlank(message = "Email cannot be empty.")
	@Email(message = "Email format is not valid.")
	private String email;

	@NotBlank(message = "Password cannot be empty.")
	private String password;

	public UserDTO() {
		super();
	}

	public UserDTO(Long id, @NotBlank(message = "First name cannot be empty.") String firstName,
			@NotBlank(message = "Last name cannot be empty.") String lastName,
			@NotBlank(message = "Email cannot be empty.") @Email(message = "Email format is not valid.") String email,
			@NotBlank(message = "Password cannot be empty.") String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}

}

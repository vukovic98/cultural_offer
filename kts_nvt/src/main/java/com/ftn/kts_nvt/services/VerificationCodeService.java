package com.ftn.kts_nvt.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ftn.kts_nvt.beans.User;
import com.ftn.kts_nvt.beans.VerificationCode;
import com.ftn.kts_nvt.repositories.VerificationCodeRepository;

@Service
public class VerificationCodeService {
	
	@Autowired
	private VerificationCodeRepository verificationCodeRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public VerificationCode findCodeById(long id) {
		return this.verificationCodeRepository.findById(id).orElse(null);
	}
	
	public VerificationCode findCodeByUser(Long id) {
		return this.verificationCodeRepository.findByUserId(id);
	}
	
	public boolean deleteCodeForUser(Long id) {
		VerificationCode exists = this.verificationCodeRepository.findByUserId(id);
		
		if(exists != null) {
			this.verificationCodeRepository.delete(exists);
			return true;
		} else
			return false;
	}
	
	public boolean create(VerificationCode c) {
		VerificationCode code = this.verificationCodeRepository.save(c);
		
		if(code != null)
			return true;
		else
			return false;
	}

	public String createCode(User existUser, VerificationCode code) {
		
		
		StringBuffer sb = new StringBuffer();

		sb.append("<h2>" + existUser.getFirstName() + ", in order to successfully register on our website, please use following code</h2><br>");
		sb.append("<h3>" + code.getCode() + "</h3> <br>");
		sb.append("<h2>to verify your account.</h2>");
		
		return sb.toString();
	}
	
	@Async
	public void sendCode(User existUser, VerificationCode code) {
		try {
			MimeMessage msg = this.javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);

			helper.setTo(existUser.getEmail());
			helper.setSubject("Cultural Offer: Verification Mail");
			helper.setText(createCode(existUser, code), true);
			this.javaMailSender.send(msg);

			System.out.println("VERIFICATION MAIL SENT!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

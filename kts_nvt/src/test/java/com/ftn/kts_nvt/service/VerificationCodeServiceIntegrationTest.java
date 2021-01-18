package com.ftn.kts_nvt.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.kts_nvt.beans.RegisteredUser;
import com.ftn.kts_nvt.beans.VerificationCode;
import com.ftn.kts_nvt.repositories.RegisteredUserRepository;
import com.ftn.kts_nvt.services.VerificationCodeService;
import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class VerificationCodeServiceIntegrationTest {

	@Autowired
	private VerificationCodeService verificationCodeService;
	
	@Autowired
	private RegisteredUserRepository userRepository;
	
	@Rule
	public final GreenMailRule greenMail = new GreenMailRule(ServerSetupTest.ALL);
	
	@Test
	public void testFindById() {
		
		long id = 1L;
		
		VerificationCode code = verificationCodeService.findCodeById(id);
		
		assertNotNull(code);
		assertEquals(id, code.getId());
		
	}
	
	@Test
	public void testFindByUserId() {
		
		long id = 1L;
		
		VerificationCode code = verificationCodeService.findCodeByUser(id);
		
		assertNotNull(code);
		assertEquals(id, code.getUser().getId());
		
	}
	
	@Test
	@Transactional
	public void testCreateDeleteForUser() {
		
		RegisteredUser user = userRepository.findById(4L).orElse(null);
		
		VerificationCode code = new VerificationCode(user);
		
		boolean created = verificationCodeService.create(code);
		
		assertEquals(true, created);
		
		VerificationCode found = verificationCodeService.findCodeByUser(4L);
		
		assertEquals(code.getCode(), found.getCode());
		
		boolean deleted = verificationCodeService.deleteCodeForUser(1L);
		
		assertEquals(true, deleted);
		
		
	}
	
	@Test
	public void testEmailSend() throws MessagingException {
		
		long id = 1L;
		RegisteredUser user = userRepository.findById(id).orElse(null);
		VerificationCode code = verificationCodeService.findCodeByUser(id);
		
	    GreenMailUtil.sendTextEmailTest(user.getEmail(), "mrs.isa2020@gmail.com", "Cultural Offer: Verification Mail", verificationCodeService.createCode(user, code));
	    MimeMessage[] emails = greenMail.getReceivedMessages();
	    
	    
	    assertEquals(1, emails.length);
	    assertEquals("Cultural Offer: Verification Mail", emails[0].getSubject());
	    assertEquals("<h2>Vladimir, in order to successfully register on our website, please use following code</h2><br><h3>qwre</h3> <br><h2>to verify your account.</h2>", GreenMailUtil.getBody(emails[0]));
	    
	}
	
}

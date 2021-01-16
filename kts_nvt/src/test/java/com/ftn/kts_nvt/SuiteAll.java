package com.ftn.kts_nvt;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.TestPropertySource;


import com.ftn.kts_nvt.e2e.HomePageAdminE2ETest;
import com.ftn.kts_nvt.e2e.HomePageRegisteredUserE2ETest;
import com.ftn.kts_nvt.e2e.HomePageUnregisteredUserE2ETest;
import com.ftn.kts_nvt.e2e.LoginE2ETest;
import com.ftn.kts_nvt.e2e.OfferDetailsAdminE2ETest;
import com.ftn.kts_nvt.e2e.OfferDetailsRegisteredUserE2ETest;
import com.ftn.kts_nvt.e2e.SubscribedItemsE2ETest;
import com.ftn.kts_nvt.e2e.AddCategoryE2ETest;
import com.ftn.kts_nvt.e2e.AddOfferE2ETest;
import com.ftn.kts_nvt.e2e.AddTypeE2ETest;
import com.ftn.kts_nvt.e2e.ApprovingCommentsE2ETest;
import com.ftn.kts_nvt.e2e.ChangePasswordE2ETest;
import com.ftn.kts_nvt.e2e.EditProfileE2ETest;
import com.ftn.kts_nvt.e2e.SignUpE2ETest;
import com.ftn.kts_nvt.e2e.VerifyUserE2ETest;


@RunWith(Suite.class)
@SuiteClasses({
	
		//controller I
		//CulturalOfferCategoryControllerIntegrationTest.class,
		//PostControllerIntegrationTest.class,
		//GeoLocationControllerIntegrationTest.class,
	    //CommentControllerIntegrationTest.class,
	    //CulturalOfferControllerIntegrationTest.class,
	    //GradeControllerIntegrationTest.class,
	    //ImageControllerIntegrationTest.class,
	    //CulturalOfferTypeControllerIntegrationTest.class,
	    //AdminControllerIntegrationTest.class,
	    //AuthenticationControllerIntegrationTest.class,
	    //RegisteredUserControllerIntegrationTest.class,
	    
	    //service U
		//CulturalOfferServiceUnitTest.class,
		//CommentServiceUnitTest.class,
	    //CulturalOfferCategoryServiceUnitTest.class,
	    //GeoLocationServiceUnitTest.class,
	    //ImageServiceUnitTest.class,
	    //PostServiceUnitTest.class,
	    //GradeServiceUnitTest.class,
	    
	    //service I
	    //CommentServiceIntegrationTest.class,
	    //CulturalOfferServiceIntegrationTest.class,
	    //CulturalOfferCategoryServiceIntegrationTest.class,
	    //CulturalOfferTypeServiceIntegrationTest.class,
	    //GeoLocationServiceIntegrationTest.class,
	    //GradeServiceIntegrationTest.class,
	    //ImageServiceIntegrationTest.class,
	    //PostServiceIntegrationTest.class,
	    //AdminServiceIntegrationTest.class,
	    //RegisteredUserServiceIntegrationTest.class,
	    //VerificationCodeServiceIntegrationTest.class,
     
	    //repository I
		//CulturalOfferCategoryRepositoryIntegrationTest.class,
		//CulturalOfferTypeRepositoryIntegrationTest.class,
		//PostRepositoryIntegrationTest.class,
		//GradeRepositoryIntegrationTest.class,
		//CulturalOfferRepositoryIntegrationTest.class,
		//CommentRepositoryIntegrationTest.class, 
		//GradeRepositoryIntegrationTest.class,
		//AdminRepositoryIntegrationTest.class,
		//RegisteredUserRepositoryIntegrationTest.class,
		//VerificationCodeRepositoryIntegrationTest.class,
	
		//E2E SW3
//		LoginE2ETest.class,
//		HomePageUnregisteredUserE2ETest.class,
//		HomePageRegisteredUserE2ETest.class,
//		HomePageAdminE2ETest.class,
//		SubscribedItemsE2ETest.class,
		

		//E2E SW78
		//SignUpE2ETest.class,
		//VerifyUserE2ETest.class,
		//EditProfileE2ETest.class,
		//ChangePasswordE2ETest.class,
		//ApprovingCommentsE2ETest.class
		
		//E2E SW1
		AddTypeE2ETest.class,
		//AddOfferE2ETest.class,
		//OfferDetailsRegisteredUserE2ETest.class,
		//OfferDetailsAdminE2ETest.class,
		//AddCategoryE2ETest.class
	
	
})
@TestPropertySource("classpath:test.properties")
public class SuiteAll {

	@Test
	void contextLoads() {
	}
}

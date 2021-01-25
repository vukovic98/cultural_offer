package com.ftn.kts_nvt;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.TestPropertySource;

import com.ftn.kts_nvt.controller.AdminControllerIntegrationTest;
import com.ftn.kts_nvt.controller.AuthenticationControllerIntegrationTest;
import com.ftn.kts_nvt.controller.CommentControllerIntegrationTest;
import com.ftn.kts_nvt.controller.CulturalOfferCategoryControllerIntegrationTest;
import com.ftn.kts_nvt.controller.CulturalOfferControllerIntegrationTest;
import com.ftn.kts_nvt.controller.CulturalOfferTypeControllerIntegrationTest;
import com.ftn.kts_nvt.controller.GeoLocationControllerIntegrationTest;
import com.ftn.kts_nvt.controller.GradeControllerIntegrationTest;
import com.ftn.kts_nvt.controller.ImageControllerIntegrationTest;
import com.ftn.kts_nvt.controller.PostControllerIntegrationTest;
import com.ftn.kts_nvt.controller.RegisteredUserControllerIntegrationTest;


@RunWith(Suite.class)
@SuiteClasses({
	
		//controller I
		CulturalOfferCategoryControllerIntegrationTest.class,
		PostControllerIntegrationTest.class,
		GeoLocationControllerIntegrationTest.class,
	    CommentControllerIntegrationTest.class,
	    CulturalOfferControllerIntegrationTest.class,
	    GradeControllerIntegrationTest.class,
	    ImageControllerIntegrationTest.class,
	    CulturalOfferTypeControllerIntegrationTest.class,
	    AdminControllerIntegrationTest.class,
	    AuthenticationControllerIntegrationTest.class,
	    RegisteredUserControllerIntegrationTest.class,
	    
	    
	    //service U
//		CulturalOfferServiceUnitTest.class,
//		CommentServiceUnitTest.class,
//	    CulturalOfferCategoryServiceUnitTest.class,
//	    GeoLocationServiceUnitTest.class,
//	    ImageServiceUnitTest.class,
//	    PostServiceUnitTest.class,
//	    GradeServiceUnitTest.class,
	    
	    //service I
//	    CommentServiceIntegrationTest.class,
//	    CulturalOfferServiceIntegrationTest.class,
//	    CulturalOfferCategoryServiceIntegrationTest.class,
//	    GeoLocationServiceIntegrationTest.class,
//	    ImageServiceIntegrationTest.class, 
//	    AdminServiceIntegrationTest.class,
//	    RegisteredUserServiceIntegrationTest.class,
//	    VerificationCodeServiceIntegrationTest.class,
//	    PostServiceIntegrationTest.class,
//	    //------x_testCreateAndDeleteEntity, x_testUpdate
//		GradeServiceIntegrationTest.class,
//	    //------x_testCreateAndDelete
//		CulturalOfferTypeServiceIntegrationTest.class,
//		//------h_testCreateAndDelete

//	    //repository I
//		CulturalOfferCategoryRepositoryIntegrationTest.class,
//		CulturalOfferTypeRepositoryIntegrationTest.class,
//		PostRepositoryIntegrationTest.class,
//		GradeRepositoryIntegrationTest.class,
//		CulturalOfferRepositoryIntegrationTest.class,
//		CommentRepositoryIntegrationTest.class, 
//		GradeRepositoryIntegrationTest.class,
//		AdminRepositoryIntegrationTest.class,
//		RegisteredUserRepositoryIntegrationTest.class,
//		VerificationCodeRepositoryIntegrationTest.class,
	
		//E2E SW3
		//LoginE2ETest.class,
		//HomePageUnregisteredUserE2ETest.class,
		//HomePageRegisteredUserE2ETest.class,
		//HomePageAdminE2ETest.class,
		//SubscribedItemsE2ETest.class,
		

		//E2E SW78
		//SignUpE2ETest.class,
		//VerifyUserE2ETest.class,
		//EditProfileE2ETest.class,
		//ChangePasswordE2ETest.class,
		//ApprovingCommentsE2ETest.class
		
		//E2E SW1
		//AddTypeE2ETest.class,
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

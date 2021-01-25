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
import com.ftn.kts_nvt.repository.AdminRepositoryIntegrationTest;
import com.ftn.kts_nvt.repository.CommentRepositoryIntegrationTest;
import com.ftn.kts_nvt.repository.CulturalOfferCategoryRepositoryIntegrationTest;
import com.ftn.kts_nvt.repository.CulturalOfferRepositoryIntegrationTest;
import com.ftn.kts_nvt.repository.CulturalOfferTypeRepositoryIntegrationTest;
import com.ftn.kts_nvt.repository.GradeRepositoryIntegrationTest;
import com.ftn.kts_nvt.repository.PostRepositoryIntegrationTest;
import com.ftn.kts_nvt.repository.RegisteredUserRepositoryIntegrationTest;
import com.ftn.kts_nvt.repository.VerificationCodeRepositoryIntegrationTest;
import com.ftn.kts_nvt.service.AdminServiceIntegrationTest;
import com.ftn.kts_nvt.service.CommentServiceIntegrationTest;
import com.ftn.kts_nvt.service.CommentServiceUnitTest;
import com.ftn.kts_nvt.service.CulturalOfferCategoryServiceIntegrationTest;
import com.ftn.kts_nvt.service.CulturalOfferCategoryServiceUnitTest;
import com.ftn.kts_nvt.service.CulturalOfferServiceIntegrationTest;
import com.ftn.kts_nvt.service.CulturalOfferServiceUnitTest;
import com.ftn.kts_nvt.service.CulturalOfferTypeServiceIntegrationTest;
import com.ftn.kts_nvt.service.GeoLocationServiceIntegrationTest;
import com.ftn.kts_nvt.service.GeoLocationServiceUnitTest;
import com.ftn.kts_nvt.service.GradeServiceIntegrationTest;
import com.ftn.kts_nvt.service.GradeServiceUnitTest;
import com.ftn.kts_nvt.service.ImageServiceIntegrationTest;
import com.ftn.kts_nvt.service.ImageServiceUnitTest;
import com.ftn.kts_nvt.service.PostServiceIntegrationTest;
import com.ftn.kts_nvt.service.PostServiceUnitTest;
import com.ftn.kts_nvt.service.RegisteredUserServiceIntegrationTest;
import com.ftn.kts_nvt.service.VerificationCodeServiceIntegrationTest;
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
import com.ftn.kts_nvt.e2e.AddCategoryE2ETest;
import com.ftn.kts_nvt.e2e.AddOfferE2ETest;
import com.ftn.kts_nvt.e2e.AddTypeE2ETest;
import com.ftn.kts_nvt.e2e.ApprovingCommentsE2ETest;
import com.ftn.kts_nvt.e2e.ChangePasswordE2ETest;
import com.ftn.kts_nvt.e2e.EditProfileE2ETest;
import com.ftn.kts_nvt.e2e.SignUpE2ETest;


@RunWith(Suite.class)
@SuiteClasses({
	
		//controller I
	    
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

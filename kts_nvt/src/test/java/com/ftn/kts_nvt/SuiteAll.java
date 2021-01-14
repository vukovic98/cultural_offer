package com.ftn.kts_nvt;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.TestPropertySource;

import com.ftn.kts_nvt.e2e.HomePageRegisteredUserE2ETest;

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
		//LoginE2ETest.class,
		//HomePageUnregisteredUser.class,
		HomePageRegisteredUserE2ETest.class

})
@TestPropertySource("classpath:test.properties")
public class SuiteAll {

	@Test
	void contextLoads() {
	}
}

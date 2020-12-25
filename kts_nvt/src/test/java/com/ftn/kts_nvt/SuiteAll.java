package com.ftn.kts_nvt;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.TestPropertySource;

import com.ftn.kts_nvt.controller.CulturalOfferTypeControllerIntegrationTest;
import com.ftn.kts_nvt.controller.GradeControllerIntegrationTest;
import com.ftn.kts_nvt.controller.ImageControllerIntegrationTest;
import com.ftn.kts_nvt.repository.CulturalOfferTypeRepositoryIntegrationTest;
import com.ftn.kts_nvt.repository.GradeRepositoryIntegrationTest;
import com.ftn.kts_nvt.service.CulturalOfferTypeServiceIntegrationTest;
import com.ftn.kts_nvt.service.GradeServiceIntegrationTest;
import com.ftn.kts_nvt.service.ImageServiceIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({
		// CulturalOfferServiceUnitTest.class,
		// CommentServiceUnitTest.class,
		//CulturalOfferTypeRepositoryIntegrationTest.class,
		// GradeRepositoryIntegrationTest.class,
		// CulturalOfferRepositoryIntegrationTest.class,
		// CulturalOfferServiceIntegrationTest.class,
		// CommentRepositoryIntegrationTest.class, 
		// CommentServiceIntegrationTest.class,
	  	// CulturalOfferControllerIntegrationTest.class,
	  	// CommentControllerIntegrationTest.class,

		GradeRepositoryIntegrationTest.class,
		CulturalOfferTypeRepositoryIntegrationTest.class,

		GradeServiceIntegrationTest.class,
		CulturalOfferTypeServiceIntegrationTest.class,
		ImageServiceIntegrationTest.class,
	
		CulturalOfferTypeControllerIntegrationTest.class,
		ImageControllerIntegrationTest.class,
		GradeControllerIntegrationTest.class
})
@TestPropertySource("classpath:test.properties")
public class SuiteAll {

}

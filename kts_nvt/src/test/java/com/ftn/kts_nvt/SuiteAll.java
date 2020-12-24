package com.ftn.kts_nvt;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.TestPropertySource;

@RunWith(Suite.class)
@SuiteClasses({
		// CulturalOfferServiceUnitTest.class,
		// CulturalOfferTypeServiceIntegrationTest.class,
		// CommentServiceUnitTest.class,
		// GradeServiceIntegrationTest.class,
		// CulturalOfferTypeRepositoryIntegrationTest.class,
		// GradeRepositoryIntegrationTest.class,
		// CulturalOfferTypeServiceIntegrationTest.class
		// CulturalOfferTypeServiceIntegrationTest.class,
		//CulturalOfferRepositoryIntegrationTest.class,
		// CulturalOfferServiceIntegrationTest.class,
		//CommentRepositoryIntegrationTest.class, 
		//CommentServiceIntegrationTest.class,
	  //CulturalOfferControllerIntegrationTest.class,
	  //CommentControllerIntegrationTest.class
})
@TestPropertySource("classpath:test.properties")
public class SuiteAll {

}

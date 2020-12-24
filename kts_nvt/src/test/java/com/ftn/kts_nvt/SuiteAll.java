package com.ftn.kts_nvt;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.TestPropertySource;

import com.ftn.kts_nvt.controller.CulturalOfferControllerIntegrationTest;
import com.ftn.kts_nvt.repository.CommentRepositoryIntegrationTest;
import com.ftn.kts_nvt.repository.CulturalOfferRepositoryIntegrationTest;
import com.ftn.kts_nvt.service.CommentServiceIntegrationTest;
import com.ftn.kts_nvt.service.CommentServiceUnitTest;
import com.ftn.kts_nvt.service.CulturalOfferServiceIntegrationTest;
import com.ftn.kts_nvt.service.CulturalOfferServiceUnitTest;
import com.ftn.kts_nvt.service.CulturalOfferTypeServiceIntegrationTest;
import com.ftn.kts_nvt.service.ImageServiceIntegrationTest;

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
		// CulturalOfferRepositoryIntegrationTest.class,
		// CulturalOfferServiceIntegrationTest.class,
		CommentRepositoryIntegrationTest.class, 
		CommentServiceIntegrationTest.class })
@TestPropertySource("classpath:test.properties")
public class SuiteAll {

}

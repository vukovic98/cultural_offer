package com.ftn.kts_nvt;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.TestPropertySource;

import com.ftn.kts_nvt.controller.CulturalOfferControllerIntegrationTest;
import com.ftn.kts_nvt.service.CommentServiceUnitTest;
import com.ftn.kts_nvt.service.CulturalOfferServiceIntegrationTest;
import com.ftn.kts_nvt.service.CulturalOfferServiceUnitTest;
import com.ftn.kts_nvt.service.CulturalOfferTypeServiceIntegrationTest;
import com.ftn.kts_nvt.service.ImageServiceIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({
	//CulturalOfferServiceUnitTest.class,
	//CommentServiceUnitTest.class,
	CulturalOfferTypeServiceIntegrationTest.class,
	//CulturalOfferServiceUnitTest.class,
	//CommentServiceUnitTest.class,
	//GradeServiceIntegrationTest.class,
	//CulturalOfferTypeRepositoryIntegrationTest.class,
	//GradeRepositoryIntegrationTest.class,
	//CulturalOfferTypeServiceIntegrationTest.class
	//CulturalOfferTypeServiceIntegrationTest.class,
	//CulturalOfferRepositoryIntegrationTest.class,
	//CulturalOfferServiceIntegrationTest.class})
@TestPropertySource("classpath:test.properties")public class SuiteAll {


}

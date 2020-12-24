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

@RunWith(Suite.class)
@SuiteClasses({
	//CulturalOfferServiceUnitTest.class,
	//CommentServiceUnitTest.class,
	//CulturalOfferServiceUnitTest.class,
	//CommentServiceUnitTest.class,
	//ImageServiceIntegrationTest.class,
	//CulturalOfferTypeServiceIntegrationTest.class,
	//CulturalOfferTypeServiceIntegrationTest.class,
	//CulturalOfferRepositoryIntegrationTest.class,
	//CulturalOfferServiceIntegrationTest.class,
	CulturalOfferControllerIntegrationTest.class
})
@TestPropertySource("classpath:test.properties")
public class SuiteAll {


}

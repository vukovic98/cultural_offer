package com.ftn.kts_nvt;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.TestPropertySource;

import com.ftn.kts_nvt.repository.CulturalOfferRepositoryIntegrationTest;
import com.ftn.kts_nvt.service.CommentServiceUnitTest;
import com.ftn.kts_nvt.service.CulturalOfferServiceIntegrationTest;
import com.ftn.kts_nvt.service.CulturalOfferServiceUnitTest;

@RunWith(Suite.class)
@SuiteClasses({
	CulturalOfferServiceUnitTest.class,
	CommentServiceUnitTest.class,
	CulturalOfferRepositoryIntegrationTest.class,
	CulturalOfferServiceIntegrationTest.class
	//ImageServiceIntegrationTest.class,
	//CulturalOfferTypeServiceIntegrationTest.class
	//CulturalOfferTypeServiceIntegrationTest.class,
	//CulturalOfferRepositoryIntegrationTest.class,
	//
	})@TestPropertySource("classpath:test.properties")
public class SuiteAll {


}

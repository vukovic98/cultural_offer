package com.ftn.kts_nvt;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.TestPropertySource;

import com.ftn.kts_nvt.repositories.CulturalOfferCategoryRepository;
import com.ftn.kts_nvt.repository.CulturalOfferCategoryRepositoryIntegrationTest;
import com.ftn.kts_nvt.repository.CulturalOfferRepositoryIntegrationTest;
import com.ftn.kts_nvt.repository.PostRepositoryIntegrationTest;
import com.ftn.kts_nvt.service.CommentServiceUnitTest;
import com.ftn.kts_nvt.service.CulturalOfferCategoryIntegrationTest;
import com.ftn.kts_nvt.service.CulturalOfferCategoryServiceUnitTest;
import com.ftn.kts_nvt.service.CulturalOfferServiceIntegrationTest;
import com.ftn.kts_nvt.service.CulturalOfferServiceUnitTest;
import com.ftn.kts_nvt.service.GeoLocationServiceIntegrationTest;
import com.ftn.kts_nvt.service.GeoLocationServiceUnitTest;
import com.ftn.kts_nvt.service.PostServiceIntegrationTest;
import com.ftn.kts_nvt.service.PostServiceUnitTest;

@RunWith(Suite.class)
@SuiteClasses({
	GeoLocationServiceIntegrationTest.class
	//CulturalOfferCategoryIntegrationTest.class
	//CulturalOfferServiceIntegrationTest.class
	//CulturalOfferCategoryRepositoryIntegrationTest.class
	//PostServiceIntegrationTest.class
	//CulturalOfferCategoryServiceUnitTest.class,
	//GeoLocationServiceUnitTest.class,
	//PostServiceUnitTest.class
	//ImageServiceIntegrationTest.class,
	//CulturalOfferTypeServiceIntegrationTest.class
	//CulturalOfferTypeServiceIntegrationTest.class,
	//CulturalOfferRepositoryIntegrationTest.class,
	})@TestPropertySource("classpath:test.properties")
public class SuiteAll {


}

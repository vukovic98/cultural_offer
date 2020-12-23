package com.ftn.kts_nvt;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.TestPropertySource;

import com.ftn.kts_nvt.service.CommentServiceUnitTest;
import com.ftn.kts_nvt.service.CulturalOfferServiceUnitTest;
import com.ftn.kts_nvt.service.CulturalOfferTypeServiceIntegrationTest;
import com.ftn.kts_nvt.service.CulturalOfferTypeServiceUnitTest; 
import com.ftn.kts_nvt.service.ImageServiceIntegrationTest;
import com.ftn.kts_nvt.service.ImageServiceUnitTest;

@RunWith(Suite.class)
@SuiteClasses({
	CulturalOfferServiceUnitTest.class,
	CommentServiceUnitTest.class,
	//ImageServiceUnitTest.class,
	//ImageServiceIntegrationTest.class,
	CulturalOfferTypeServiceIntegrationTest.class
})@TestPropertySource("classpath:test.properties")
public class SuiteAll {


}

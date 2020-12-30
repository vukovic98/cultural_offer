package com.ftn.kts_nvt.helper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageImplMapper<T> {
	
	public PageImplementation<T> toPageImpl(Page<T> newPage) {
		PageImplementation<T> pageImpl = new PageImplementation<>();
		
		pageImpl.setContent(newPage.getContent());
		pageImpl.setEmpty(newPage.isEmpty());
		pageImpl.setFirst(newPage.isFirst());
		pageImpl.setLast(newPage.isLast());
		pageImpl.setNumber(newPage.getNumber());
		pageImpl.setNumberOfElements(newPage.getNumberOfElements());
		pageImpl.setPageNumber(newPage.getPageable().getPageNumber());
		pageImpl.setPageSize(newPage.getPageable().getPageSize());
		pageImpl.setSize(newPage.getSize());
		pageImpl.setTotalElements(newPage.getTotalElements());
		pageImpl.setTotalPages(newPage.getTotalPages());
		
		return pageImpl;
	}
}

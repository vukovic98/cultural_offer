package com.ftn.kts_nvt.helper;

import java.util.List;

public class PageImplementation<T> {
	private List<T> content;
	private long totalElements;
	private boolean last;
	private int totalPages;
	private int size;
	private int number;
	private int numberOfElements;
	private boolean first;
	private boolean empty;
	private int pageNumber;
	private int pageSize;

	public PageImplementation() {
		super();
	}

	public PageImplementation(List<T> content, long totalElements, boolean last, int totalPages, int size, int number,
			int numberOfElements, boolean first, boolean empty, int pageNumber, int pageSize) {
		super();
		this.content = content;
		this.totalElements = totalElements;
		this.last = last;
		this.totalPages = totalPages;
		this.size = size;
		this.number = number;
		this.numberOfElements = numberOfElements;
		this.first = first;
		this.empty = empty;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}

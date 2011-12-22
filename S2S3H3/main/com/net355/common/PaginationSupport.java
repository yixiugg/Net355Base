package com.net355.common;

import java.util.Iterator;
import java.util.List;

public class PaginationSupport<E> implements Iterable<E> {
	public final static int PAGESIZE = 10;

	private int pageSize = PAGESIZE;

	private int totalCount;

	private int currentPage;

	private int nextPage;

	private int previousPage;

	private int startIndex;

	private int[] indexes = new int[0];

	private int nextIndex;

	private int previousIndex;

	private int pageCount;

	private List<E> items;

	private int lastIndex;

	public PaginationSupport(int pageSize, int startIndex) {
		setPageSize(pageSize);
		setStartIndex(startIndex);

	}

	public PaginationSupport(List<E> items, int totalCount) {
		setPageSize(PAGESIZE);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(0);

	}

	public PaginationSupport(List<E> items, int totalCount, int startIndex) {
		setPageSize(PAGESIZE);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(startIndex);

	}

	public PaginationSupport(List<E> items, int totalCount, int pageSize,
			int startIndex) {
		setPageSize(pageSize);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(startIndex);

	}

	public void setTotalCount(int totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
			int count = totalCount / pageSize;
			if (totalCount % pageSize > 0)
				count++;
			indexes = new int[count];
			for (int i = 0; i < count; i++) {
				indexes[i] = pageSize * i;
			}
		} else {
			this.totalCount = 0;
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setIndexes(int[] indexes) {
		this.indexes = indexes;
	}

	public int[] getIndexes() {
		return indexes;
	}

	public void setStartIndex(int startIndex) {
		if (totalCount <= 0)
			this.startIndex = 0;
		else if (startIndex >= totalCount)
			this.startIndex = indexes[indexes.length - 1];
		else if (startIndex < 0)
			this.startIndex = 0;
		else {
			this.startIndex = indexes[startIndex / pageSize];
		}
		{
			pageCount = indexes.length;
			currentPage = startIndex / pageSize + 1;
			previousPage = currentPage > 1 ? currentPage - 1 : currentPage;
			nextPage = currentPage < pageCount ? currentPage + 1 : currentPage;
			nextIndex = startIndex + pageSize;
			nextIndex = nextIndex >= totalCount ? startIndex : nextIndex;
			previousIndex = startIndex - pageSize;
			previousIndex = previousIndex < 0 ? 0 : previousIndex;
			lastIndex = indexes.length == 0 ? 0 : indexes[indexes.length - 1];
		}
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setNextIndex(int nextIndex) {
		this.nextIndex = nextIndex;
	}

	public int getNextIndex() {
		return nextIndex;
	}

	public void setPreviousIndex(int previousIndex) {
		this.previousIndex = previousIndex;
	}

	public int getPreviousIndex() {
		return previousIndex;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<E> getItems() {
		return items;
	}

	public void setItems(List<E> items) {
		this.items = items;
	}

	public int getNextPage() {
		return nextPage;

	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}

	public Iterator<E> iterator() {
		return items.iterator();
	}

	public int size() {
		return items.size();
	}
}
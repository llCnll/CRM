package domain;

import java.util.List;

public class PageBean {
	
	//1. 总页数
	Integer totalPage;
	//2. 总记录数
	Integer totalCount;
	//3. 当前页
	Integer pageSize;
	//4. 每页显示条数 
	Integer currentPage;
	//数据
	List list;
	
	
	
	public PageBean(Integer totalCount, Integer pageSize, Integer currentPage) {
		this.totalCount = totalCount;
		
		if(pageSize == null){
			//默认每页显示3个
			this.pageSize = 3;
		}else {
			this.pageSize = pageSize;
		}
		
		if(currentPage == null){
			//默认显示第一页
			this.currentPage = 1;
		}else{
			this.currentPage = currentPage;
		}
		
		//计算总页数
		this.totalPage = (this.totalCount+this.pageSize-1)/this.pageSize;
		
		if(this.totalPage < this.currentPage ){
			this.currentPage = this.totalPage;
		}
		if(this.currentPage < 1){
			this.currentPage = 1;
		}
		
	}
	//计算索引值
	public int getStart(){
		return (this.currentPage-1)*this.pageSize;
	}
	
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "PageBean [totalPage=" + totalPage + ", totalCount="
				+ totalCount + ", pageSize=" + pageSize + ", currentPage="
				+ currentPage + ", list=" + list + "]";
	}
}

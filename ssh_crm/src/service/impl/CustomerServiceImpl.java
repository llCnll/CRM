package service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import service.CustomerService;
import dao.CustomerDao;
import domain.Customer;
import domain.PageBean;

public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao cd;

	@Override
	public PageBean getPageBean(DetachedCriteria dc,
			Integer currentPage, Integer pageSize) {
		
		//1. 查询总记录数
		Integer totalCount = cd.getTotalCount(dc);
		//2. 封装PageBean, 计算总页数
		PageBean pb = new PageBean(totalCount, pageSize, currentPage);
		//3. 查询客户列表
		List<Customer> list = cd.getPageList(dc, pb.getStart(), pb.getPageSize());
		
		pb.setList(list);
		
		return pb;
	}

	public void setCd(CustomerDao cd) {
		this.cd = cd;
	}

	@Override
	public void save(Customer customer) {
		cd.save(customer);
	}

}

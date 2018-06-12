package service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import service.LinkManService;
import dao.LinkManDao;
import domain.Customer;
import domain.LinkMan;
import domain.PageBean;

public class LinkManServiceImpl implements LinkManService {
	
	private LinkManDao lmd;

	@Override
	public void save(LinkMan linkMan) {
		lmd.saveOrUpdate(linkMan);
	}


	public void setLmd(LinkManDao lmd) {
		this.lmd = lmd;
	}


	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage,
			Integer pageSize) {
		//1. 查询总记录数
		Integer totalCount = lmd.getTotalCount(dc);
		//2. 封装PageBean, 计算总页数
		PageBean pb = new PageBean(totalCount, pageSize, currentPage);
		//3. 查询客户列表
		List<LinkMan> list = lmd.getPageList(dc, pb.getStart(), pb.getPageSize());
		
		pb.setList(list);
		
		return pb;
	}


	@Override
	public LinkMan getById(Long lkm_id) {

		
		return lmd.getById(lkm_id);
	}

}

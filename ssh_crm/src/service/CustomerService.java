package service;

import org.hibernate.criterion.DetachedCriteria;

import domain.Customer;
import domain.PageBean;

public interface CustomerService {

	PageBean getPageBean(DetachedCriteria dc, Integer currentPage,
			Integer pageSize);

}

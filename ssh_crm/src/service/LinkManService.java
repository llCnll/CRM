package service;

import org.hibernate.criterion.DetachedCriteria;

import domain.LinkMan;
import domain.PageBean;

public interface LinkManService {

	void save(LinkMan linkMan);

	PageBean getPageBean(DetachedCriteria dc, Integer currentPage,
			Integer pageSize);

	LinkMan getById(Long lkm_id);

}

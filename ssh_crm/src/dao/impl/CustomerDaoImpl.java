package dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import dao.CustomerDao;
import domain.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	@Override
	public Integer getTotalCount(DetachedCriteria dc) {

		dc.setProjection(Projections.rowCount());
		
		List<Long> list = (List<Long>) super.getHibernateTemplate().findByCriteria(dc);
		
		//清空之前设置的聚合函数
		dc.setProjection(null);

		if(list!=null && list.size()>0){
			Long count = list.get(0);
			System.out.println("count = "+ count);
			return count.intValue();
		}else{
			return null;
		}
	}

	@Override
	public List<Customer> getPageList(DetachedCriteria dc, int start,
			Integer pageSize) {
		
		List<Customer> list = (List<Customer>) super.getHibernateTemplate().findByCriteria(dc, start, pageSize);
		
		return list;
	}

}

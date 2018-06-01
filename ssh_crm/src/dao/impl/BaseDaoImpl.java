package dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import dao.BaseDao;
import domain.Customer;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class clazz;
	
	public BaseDaoImpl(){
		//获得当前类型的带有泛型的父类
		ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得运行期的泛型类型
		clazz = (Class)ptClass.getActualTypeArguments()[0];
	}
	
	@Override
	public void save(T t) {
		getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		getHibernateTemplate().delete(t);
	}

	@Override
	public void delete(Serializable id) {
		getHibernateTemplate().delete(id);
	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	@Override
	public T getById(Serializable id) {
		
		T t = (T) getHibernateTemplate().get(clazz, id);
		
		return t;
	}

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
	public List<T> getPageList(DetachedCriteria dc, Integer start, Integer pageSize) {
		
		List<T> list = (List<T>) super.getHibernateTemplate().findByCriteria(dc, start, pageSize);
		
		return list;
	}

}

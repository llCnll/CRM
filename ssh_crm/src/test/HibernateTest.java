package test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import service.UserService;
import dao.UserDao;
import domain.User;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HibernateTest {
	@Resource(name="sessionFactory")
	private SessionFactory sf;
	
	@Test
	//单独测试hibernate
	public void fun1(){
		
		Configuration conf = new Configuration().configure();
		
		SessionFactory sf = conf.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		//--------------------------------------
		
		User u = new User();
		u.setUser_code("tom");
		u.setUser_name("tom");
		u.setUser_password("123456");
		
		session.save(u);
		
		//--------------------------------------
		tx.commit();
		session.close();
		sf.close();
		
	}
	@Test
	//测试spring管理事务
	public void fun2(){
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		//--------------------------------------
		
		User u = new User();
		u.setUser_code("rose");
		u.setUser_name("rose");
		u.setUser_password("123456");
		
		session.save(u);
		
		//--------------------------------------
		tx.commit();
		session.close();
	}
	@Resource(name="userDao")
	private UserDao ud;
	@Test
	//测试Dao Hibernate模板
	public void fun3(){
		User user = ud.getByUserCode("tom");
		System.out.println(user);
	}
	@Resource(name="userService")
	private UserService us;
	@Test
	//测试aop事务
	public void fun4(){
		User u = new User();
		
		u.setUser_code("jer");
		u.setUser_name("jer");
		u.setUser_password("123456");
		
		us.saveUser(u);
	}

}

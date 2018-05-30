package dao;

import domain.User;

public interface UserDao {
	
	//根据登陆名称查询user对象
	User getByUserCode(String code);

	void save(User u);

}

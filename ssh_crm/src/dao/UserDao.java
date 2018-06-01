package dao;

import domain.User;

public interface UserDao extends BaseDao<User>{
	
	//根据登陆名称查询user对象
	User getByUserCode(String code);

}

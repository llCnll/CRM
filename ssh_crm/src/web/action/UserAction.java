package web.action;

import service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import domain.User;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	private UserService  userService;
	private User user = new User();

	public String login() throws Exception {
		
		//1, 调用Service执行登陆逻辑
		User u = userService.getUserByCodePassword(user);
		//2. 将返回的User对象放入session域
		ActionContext.getContext().getSession().put("user", u);
		//3, 重定向到项目首页
		
		return "toHome";
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	
	
}

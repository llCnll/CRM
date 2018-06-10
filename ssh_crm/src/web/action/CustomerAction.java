package web.action;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import service.CustomerService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import domain.Customer;
import domain.PageBean;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	private Customer customer = new Customer();
	private Integer currentPage;
	private Integer pageSize;
	private CustomerService cs;
	
	public String add() throws Exception {
		
		System.out.println(customer);
		cs.save(customer);
		
		return "toList";
	}
	public String list() throws Exception {
		
		//封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		//判断并封装参数
		if(StringUtils.isNotEmpty(customer.getCust_name())&&StringUtils.isNotBlank(customer.getCust_name())){
			dc.add(Restrictions.ilike("cust_name", "%"+customer.getCust_name()+"%"));
		}
		//1 调用Service查询分页数据(PageBean)
		PageBean pb = cs.getPageBean(dc, currentPage, pageSize);
		//System.out.println(pb);
		//2 将PageBean放入request域,转发到列表页面显示
		ActionContext.getContext().put("PageBean", pb);
		return "list";
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setCs(CustomerService cs) {
		this.cs = cs;
	}

	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}

}

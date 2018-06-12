package web.action;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import service.LinkManService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import domain.Customer;
import domain.LinkMan;
import domain.PageBean;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{

	LinkMan linkMan = new LinkMan();
	private LinkManService lms;
	private Integer currentPage;
	private Integer pageSize;
	
	public String toEdit() throws Exception {
		
		//通过cust_id查询信息
		LinkMan l = lms.getById(linkMan.getLkm_id());
		//存放至域中
		ActionContext.getContext().put("linkMan", l);
		
		return "edit";
	}
	
	public String add() throws Exception {
		
		lms.save(linkMan);
		
		return "toList";
	}
	
	public String list() throws Exception {
		
		//封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(LinkMan.class);
		//判断并封装参数
		if(StringUtils.isNotEmpty(linkMan.getLkm_name())&&StringUtils.isNotBlank(linkMan.getLkm_name())){
			dc.add(Restrictions.ilike("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		if(linkMan.getCustomer()!=null && linkMan.getCustomer().getCust_id()!=null){
			dc.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
		}
		//1 调用Service查询分页数据(PageBean)
		PageBean pb = lms.getPageBean(dc, currentPage, pageSize);
		//System.out.println(pb);
		//2 将PageBean放入request域,转发到列表页面显示
		ActionContext.getContext().put("PageBean", pb);
		
		return "list";
	}
	
	
	@Override
	public LinkMan getModel() {
		return linkMan;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setLms(LinkManService lms) {
		this.lms = lms;
	}

}

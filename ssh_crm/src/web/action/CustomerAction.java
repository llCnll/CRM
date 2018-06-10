package web.action;

import java.io.File;

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
	//在后台提供一个与前台input type=file 组件name相同的属性
	private File photo;	//上传的文件会自动封装到File对象
	private String photoFileName;//在提交键名后加上固定FileName, 文件名称会自动封装到属性中
	private String photoContentType;//在提交键名后加上固定ContentType, 文件MIME类型会自动封装到属性中 text/html
	
	public String add() throws Exception {
		System.out.println("photoFileName: "+photoFileName);//Hearthstone Screenshot 04-13-18 13.22.25.png
		System.out.println("photoContentType: "+ photoContentType);//image/png
		//将上传的文件保存到指定位置
		photo.renameTo(new File("f:/photo.jpg"));
		
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
	public File getPhoto() {
		return photo;
	}
	public void setPhoto(File photo) {
		this.photo = photo;
	}
	public String getPhotoFileName() {
		return photoFileName;
	}
	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}
	public String getPhotoContentType() {
		return photoContentType;
	}
	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}
	

}

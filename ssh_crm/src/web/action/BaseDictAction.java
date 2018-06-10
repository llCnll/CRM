package web.action;

import java.util.List;

import javax.xml.ws.Response;

import org.apache.struts2.ServletActionContext;

import service.BaseDictService;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import domain.BaseDict;

public class BaseDictAction extends ActionSupport{
	
	private BaseDictService bds;
	private String dict_type_code;
	
	@Override
	public String execute() throws Exception {
		
		List<BaseDict> list = bds.getListByTypeCode(dict_type_code);
		//System.out.println(list);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		
		return null;
	}
	public void setBds(BaseDictService bds) {
		this.bds = bds;
	}
	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}

}

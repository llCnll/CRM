<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<SCRIPT language=javascript>
	function to_page(page){
		
		$('#currentPage').val(page);
		
		$("#customerForm").submit();
	}
	function changePageSize(pageSize){
		$('#pageSize').val(pageSize);
		
		$("#customerForm").submit();
	}
	
	function selectCustomer(cust_id, cust_name){
		//获得add.jsp的window对象
		var win = window.opener;
		//获得add.jsp的documnet对象
		var doc = win.document;
		//获得隐藏域和文本框赋值;
		doc.getElementById("cust_id").value=cust_id;
		doc.getElementById("cust_name").value=cust_name;
		//关闭当前窗口
		window.close();
	}
	
</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户管理 &gt; 客户列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<FORM id="customerForm" name="customerForm"
											action="${pageContext.request.contextPath }/CustomerAction_list"
											method=post>
											<!-- 隐藏域 当前页数 -->
											<input type="hidden" id="currentPage" name="currentPage" value="<s:property value="#PageBean.currentPage"/>"/>
											<!-- 隐藏域 每页显示个数 -->
											<input type="hidden" id="pageSize" name="pageSize" value="<s:property value="#PageBean.pageSize"/>"/>
											<!-- 隐藏域 每页显示个数 -->
											<input type="hidden" id="select" name="select" value="<s:property value="#parameters.select"/>"/>
											<TABLE cellSpacing=0 cellPadding=2 border=0>
												<TBODY>
													<TR>
														<TD>客户名称：</TD>
														<TD><INPUT class=textbox id=sChannel2
															style="WIDTH: 80px" maxLength=50 name="cust_name" value="${param.cust_name }"></TD>
														
														<TD><INPUT class=button id=sButton2 type=submit
															value=" 筛选 " name=sButton2></TD>
													</TR>
												</TBODY>
											</TABLE>
										</FORM>
									</TD>
								</TR>
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>客户名称</TD>
													<TD>客户级别</TD>
													<TD>客户来源</TD>
													<TD>联系人</TD>
													<TD>电话</TD>
													<TD>手机</TD>
													<TD>操作</TD>
												</TR>
												
												<s:iterator value="#PageBean.list" var="cust">
													<TR
														style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
														<TD><s:property value="#cust.cust_name"/></TD>
														<TD><s:property value="#cust.cust_level.dict_item_name"/></TD>
														<TD><s:property value="#cust.cust_source.dict_item_name"/></TD>
														<TD><s:property value="#cust.cust_linkman"/></TD>
														<TD><s:property value="#cust.cust_phone"/></TD>
														<TD><s:property value="#cust.cust_mobile"/></TD>
														<TD>
														<s:if test="#parameters.select==null">
															<a href="${pageContext.request.contextPath }/CustomerAction_toEdit?cust_id=<s:property value="#cust.cust_id"/>">修改</a>
															&nbsp;&nbsp;
															<a href="${pageContext.request.contextPath }/customerServlet?method=delete&custId=${customer.cust_id}">删除</a>
														</s:if>
														<s:else>
															<input type="button" value="选择" onclick="selectCustomer(<s:property value="#cust.cust_id"/>, '<s:property value="#cust.cust_name"/>')">
														</s:else>
														</TD>
													</TR>
												
												</s:iterator>

											</TBODY>
										</TABLE>
									</TD>
								</TR>
								
								<TR>
									<TD><SPAN id=pagelink>
											<DIV
												style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B><s:property value="#PageBean.totalCount"/></B>]条记录,[<B><s:property value="#PageBean.totalPage"/></B>]页
												,每页显示
												<select id="pageSizeSelect"name="pageSize" onchange="changePageSize($('#pageSizeSelect option:selected').val())">
												
													<option value="1" <s:property value="#PageBean.pageSize==1?'selected':''"/>>1</option>
													<option value="3" <s:property value="#PageBean.pageSize==3?'selected':''"/>>3</option>
												</select>
												条
												[<A href="javascript:to_page(<s:property value='#PageBean.currentPage-1'/>)">前一页</A>]
												<B><s:property value="#PageBean.currentPage"/></B>
												[<A href="javascript:to_page(<s:property value='#PageBean.currentPage+1'/>)">后一页</A>] 
												到
												<input type="text" size="3" id="page" name="page" value="<s:property value='#PageBean.currentPage'/>"/>
												页
												
												<input type="button" value="Go" onclick="to_page($('#page').val())"/>
											</DIV>
									</SPAN></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<s:debug></s:debug>
</BODY>
</HTML>

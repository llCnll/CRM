﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>添加客户</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	//使用ajax加载数据字典, 生成select
	//参数1: 数据字典类型(dict_type_code)
	//参数2: 将下拉框放入的标签id
	//参数3: 生成下拉选时, select标签的属性值
	//参数4: 需要回显时, 选中那个option
	function loadSelect(typecode,positionId,selectname,selectedId){
		//1. 创建select对象, 将name属性指定
		var $select = $("<select name="+selectname+"></select>");
		//2. 添加提示选项
		$select.append($("<option value=''>--请选择--</option>"));
		//3. 使用ajax方法, 访问后台Action
		$.ajax({
			url:"${pageContext.request.contextPath}/BaseDictAction",
			data:{"dict_type_code":typecode},
			async:true,
			type:"POST",
			success:function(data){
				//4. 返回json数组对象, 对其遍历
				$.each(data, function(i, json){
					var $option = $("<option value='"+json['dict_id']+"'>"+json['dict_item_name']+"</option>");
					//alert($option);
					//判断回显
					if(json['dict_id'] == selectedId){
						$option.attr("selected", "selected");
					}
					
					$select.append($option);
				});
				
			},
			error:function(){
				alert("请求失败");
			},
			dataType:"json"
		});
		
		//5.将组装好的select对象放入页面指定位置
		$('#'+positionId).append($select);
	}
	$(function(){
		loadSelect("006", "level", "cust_level.dict_id", "");
		loadSelect("002", "source", "cust_source.dict_id", "");
		loadSelect("001", "industry", "cust_industry.dict_id", "");
	});

</script>


<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<!-- 
		图片上传页面的3个要求
			1.表单必须post提交
			2.表单提交类型enctype 必须多段式
			3.文件上传<input type="file" />组件
	
	 -->

	<FORM id=form1 name=form1 
			action="${pageContext.request.contextPath }/CustomerAction_add" method=post
			enctype="multipart/form-data">
		

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
								<TD class=manageHead>当前位置：客户管理 &gt; 添加客户</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						
						<TABLE cellSpacing=0 cellPadding=5  border=0>
						  
						    
							<TR>
								<td>客户名称：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_name">
								</td>
								<td>客户级别 ：</td>
								<td id="level">
								</td>
							</TR>
							
							<TR>
								
								<td>信息来源 ：</td>
								<td id="source">
								</td>
								<td>客户行业：</td>
								<td id="industry">
								</td>
							</TR>
							
							<TR>
								<td>固定电话 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_phone">
								</td>
								<td>移动电话 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_mobile">
								</td>
							</TR>
							
							<TR>
								<td>图片上传 ：</td>
								<td>
								<INPUT type="file"
														style="WIDTH: 180px" maxLength=50 name="photo">
								</td>
							</TR>
							
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value=" 保存 " name=sButton2>
								</td>
							</tr>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
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
	</FORM>
</BODY>
</HTML>

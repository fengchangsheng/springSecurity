<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resource/js/jquery-1.9.1.min.js"></script>
</head>
<body>
<script type="text/javascript">
<!--用于保存角色的函数-->
$(document).ready(function() {
    $('#add').click(function() {
    	var msg = $("#role").val();
    	if(msg==""){
    		alert("值为空");
    		return;
    	}else{
    		
    		  $.post("ajax!addRoles.action",
    				{
    				    name:msg,
    				},
    				function(data,status){
    				 	alert("角色添加成功");
    				 	$("#role").val("");
    	    			$(".loadroles").append("<option value="+data+">"+msg+"</option>");
    				}
    		  );
    		 
    	}
    });
 });  
 
 
 <!--加载用户-->
 $(document).ready(function() {
	      $.get("ajax!loadUsers.action",
	    				function(data){
	    					  $.each(data, function (index, value) { 
	    	    					$("#loadusers").append("<option value="+value.id+">"+value.account+"</option>");
	    	    			 });
	    				},
	    				"json"
	    		  );
<!--加载角色-->     
	      $.get("ajax!loadRoles.action",
  				function(data){
  					 	   $.each(data, function (index, value) { 
  	    					   $(".loadroles").append("<option value="+value.id+">"+value.name+"</option>");
  	    				 });
  				},
  				"json"
  		  );
}); 
 
 
</script>

 
<!-- 权限配置页面 -->
<table align="center" >
  <tr>
     <td>添加角色:</td>
     <td><input type="text" name="roles.name" id="role"></td>
      <td><input type="button" value="添加角色" id="add"></td>
  </tr>

<form name="limitonChange"  action="limiton!addConfig.action" method="post">
<tr>
	<td>配置角色-资源：</td>
<td>角色<select name="rid1" class="loadroles 1" id="rid1">
           <option>---请选择---</option>
      </select></td>   <!-- 异步刷新 -->
</tr>
<tr><td>资源</td>
<td><c:forEach items="${resourcesList}" var="resources">
		<input type="checkbox" name="ids" value="${resources.id}">${resources.name}<br>
    </c:forEach>
</td>
</tr>

 <tr>
<td>配置用户-角色:</td>
<td>用户<select name="users.id" id="loadusers"><option>---请选择---</option></select></td>
	<td> 角色<select name="rid2" class="loadroles 2" id="rid2"><option>---请选择---</option></select></td>
</tr>                           
 <tr>
 	<td></td>
    <td><input type="reset" value="重置"></td>
    <td><input type="submit" value="提交"></td>
 </tr>
</table>           
 </form>
</body>
</html>
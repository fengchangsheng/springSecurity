package com.huaxin.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huaxin.bean.Roles;
import com.huaxin.bean.Users;
import com.huaxin.dao.RolesDao;
import com.huaxin.dao.UsersDao;
import com.huaxin.exception.ApplyException;
/**
 * @author fcs
 * 异步处理请求
 * Nov 21, 2014
 */
public class AjaxAction {
	private RolesDao rolesDao;
	private UsersDao usersDao;
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;

	public void addRoles(){
		try {
			request = ServletActionContext.getRequest();
			response = ServletActionContext.getResponse();
			PrintWriter pw = null;
			try {
				pw = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String name = request.getParameter("name");
			Roles roles = new Roles();
			roles.setName(name);
			rolesDao.save(roles);
			//获取保存之后自动生成的id   用于向下拉框增加一个选项
			pw.print(roles.getId());
		} catch (ApplyException e) {
			e.printStackTrace();
		}
	}
	
	public void loadRoles(){
		response = ServletActionContext.getResponse();
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			List<Roles> roles = rolesDao.findAll();
			for (Roles roles2 : roles) {
				roles2.setResources(null);//涉及到一个无线循环的bug   gson结合hibernate使用时-注意关联对象属性
				roles2.setRoles(null);//还有其它解决方案   在这里切掉关联   避免拖衣带水
				System.out.println(roles2.getName());
			}
			Gson gson = new Gson();
			pw.print(gson.toJson(roles));
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadUsers(){
		response = ServletActionContext.getResponse();
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			List<Users> users = usersDao.findAll();
			for (Users user : users) {
				System.out.println(user.getAccount());
				user.setRoles(null);
			}
			Gson gson = new Gson();
			pw.print(gson.toJson(users));
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public UsersDao getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public RolesDao getRolesDao() {
		return rolesDao;
	}
	public void setRolesDao(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}
	
	
}

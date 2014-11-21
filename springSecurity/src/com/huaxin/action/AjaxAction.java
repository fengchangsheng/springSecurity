package com.huaxin.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huaxin.bean.Roles;
import com.huaxin.dao.RolesDao;
import com.huaxin.exception.ApplyException;
/**
 * @author fcs
 * 异步处理请求
 * Nov 21, 2014
 */
public class AjaxAction {
	private RolesDao rolesDao;
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private Roles roles;

	public void addRoles(){
		try {
			request = ServletActionContext.getRequest();
			String name = request.getParameter("name");
			Roles roles = new Roles();
			roles.setName(name);
			rolesDao.save(roles);
		} catch (ApplyException e) {
			e.printStackTrace();
		}
	}
	
	public void load(){
		response = ServletActionContext.getResponse();
		System.out.println("load invoke");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			List<Roles> roles = rolesDao.findAll();
			for (Roles roles2 : roles) {
				System.out.println(roles2.getName());
			}
			Gson gson = new Gson();
			pw.print(gson.toJson(roles));
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public RolesDao getRolesDao() {
		return rolesDao;
	}
	public void setRolesDao(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}
	
	
}

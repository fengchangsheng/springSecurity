package com.huaxin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.huaxin.bean.Resources;
import com.huaxin.bean.Roles;
import com.huaxin.dao.ResourcesDao;
import com.huaxin.dao.RolesDao;
import com.huaxin.exception.ApplyException;
/**
 * @author WH141006P
 *
 * Nov 20, 2014
 */
public class LimitonAction {
	private ResourcesDao resourcesDao;
	private RolesDao rolesDao;
	private List<Roles> rolesList;
	private List<Resources> resourcesList;
	private Roles roles;
	
	public String load(){
		resourcesList = resourcesDao.findAll();
		return "loadpage";
	}
	
	public String config(){
		return "success";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public List<Roles> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<Roles> rolesList) {
		this.rolesList = rolesList;
	}

	public List<Resources> getResourcesList() {
		return resourcesList;
	}

	public void setResourcesList(List<Resources> resourcesList) {
		this.resourcesList = resourcesList;
	}

	public ResourcesDao getResourcesDao() {
		return resourcesDao;
	}
	
	public void setResourcesDao(ResourcesDao resourcesDao) {
		this.resourcesDao = resourcesDao;
	}

	public RolesDao getRolesDao() {
		return rolesDao;
	}

	public void setRolesDao(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}
	
	
}

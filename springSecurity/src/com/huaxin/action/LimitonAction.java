package com.huaxin.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private Resources resources;
	private String[] ids;
	
	public String load(){
		resourcesList = resourcesDao.findAll();
		return "loadpage";
	}
	
	public String config(){
		return "success";
	}
	
	
	/**
	 * TODO 此方法有bug   会级联删除许多数据
	 * 如何才能有效的往第三张表中插入数据
	 */
	public String addConfig(){
		if(roles!=null && ids!=null){
			System.out.println(roles.getId()+"  "+roles.getName());
			Set<Resources> resSet = new HashSet<Resources>(); 
			for (String id : ids) {
				Resources res = new Resources();
				res.setId(Integer.parseInt(id));
				resSet.add(res);
				System.out.println(id);
			}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
			roles.setResources(resSet);
			try {
				rolesDao.update(roles);
			} catch (ApplyException e) {
				e.printStackTrace();
			}
		}
		return "success";
	}
	
	
	
	
	
	
	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public Resources getResources() {
		return resources;
	}

	public void setResources(Resources resources) {
		this.resources = resources;
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

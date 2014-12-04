package com.huaxin.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;




import com.huaxin.bean.Resources;
import com.huaxin.bean.Roles;
import com.huaxin.bean.Users;
import com.huaxin.dao.ResourcesDao;
import com.huaxin.dao.RolesDao;
import com.huaxin.dao.UsersDao;
import com.huaxin.exception.ApplyException;
/**
 * @author WH141006P
 *
 * Nov 20, 2014
 */
public class LimitonAction {
	private ResourcesDao resourcesDao;
	private RolesDao rolesDao;
	private UsersDao usersDao;
	private List<Roles> rolesList;
	private List<Resources> resourcesList;
	private Roles roles;
	private Users users;
	private Resources resources;
	private String[] ids;
	private String rid1,rid2;
	
	public String load(){
		resourcesList = resourcesDao.findAll();
		return "loadpage";
	}
	
	public String config(){
		return "success";
	}
	
	/**
	 * TODO 有没有更好的解决方案   或者更好的设计
	 * 如何才能有效的往第三张表中插入数据
	 * 取消级联也不行    目前我只能分别取出然后再在原有基础上set
	 */
	public String addConfig(){
		//配置角色-资源
		if(rid1!=null && ids!=null){
			roles = rolesDao.get(Integer.parseInt(rid1));//找到需要配置的角色
			resourcesList = resourcesDao.findAll();//找到所有的资源
			System.out.println(roles.getId()+"  "+roles.getName());
			for (int i = 0; i < ids.length; i++) {
				int id = Integer.parseInt(ids[i]);//取出已选择的rsid
				for(Resources res:resourcesList){//对比找出相应的resources  一开始可以用map来装
					if(res.getId()==id && roles.getResources()!=null){
						roles.getResources().add(res);//被选中则加入
						System.out.println(res.getName());
					}else if(res.getId()==id && roles.getResources()==null){
						Set<Resources> resSet = new HashSet<Resources>();
						resSet.add(res);
						roles.setResources(resSet);//加入配置好的资源
					}
				}
			}
			try {
				rolesDao.update(roles);
			} catch (ApplyException e) {
				e.printStackTrace();
			}
			
		}
		/**    
		 * 配置用户-角色
		 * 这里假设一个用户只有一种角色   也可进行多次配置来达到多个角色的目的
		 */
		if(users.getId()!=null && !rid2.equals("")){
			users = usersDao.get(users.getId());
			Roles role = null;
			if(rid2!=rid1){
				role = rolesDao.get(Integer.parseInt(rid2));
			}else
				role = roles;
			users.getRoles().add(role);
			try {
				usersDao.update(users);
			} catch (ApplyException e) {
				e.printStackTrace();
			}
		}
		return "success";
	}
	
	
	public UsersDao getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
	
	public String getRid1() {
		return rid1;
	}

	public void setRid1(String rid1) {
		this.rid1 = rid1;
	}

	public String getRid2() {
		return rid2;
	}

	public void setRid2(String rid2) {
		this.rid2 = rid2;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
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

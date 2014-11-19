package com.huaxin.dao.impl;

import com.huaxin.bean.Notice;
import com.huaxin.dao.NoticeDao;
import com.huaxin.dao.base.impl.HibernateBaseDao;
/**
 * 
 * @author WH141006P
 * 
 */
public class NoticeDaoImpl extends HibernateBaseDao<Notice> implements NoticeDao{
	
	@Override
	public NoticeDao detail(int id) {
		return (NoticeDao) this.get(id);
	}
}

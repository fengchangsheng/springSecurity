package com.huaxin.action;

import java.util.List;

import com.huaxin.bean.Notice;
import com.huaxin.dao.NoticeDao;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 
 * @author WH141006P
 *
 * Nov 19, 2014
 */
@SuppressWarnings("serial")
public class NoticeAction extends ActionSupport {
	private List<Notice> datas;
	private NoticeDao noticeDao;
	private Notice notice;
	private String nid;
	
	
	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public NoticeDao getNoticeDao() {
		return noticeDao;
	}

	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	public List<Notice> getDatas() {
		return datas;
	}

	public void setDatas(List<Notice> datas) {
		this.datas = datas;
	}

	@Override
	public String execute() throws Exception {
		System.out.println("execute invoke!");
		this.datas = this.noticeDao.findAll();
		return SUCCESS;
	}
	
	public String detail(){
		System.out.println("detail invoke!");
		if(nid!=null && !nid.equals("")){
			System.out.println("detail success!");
			this.notice = this.noticeDao.get(Integer.parseInt(nid));
			return "detail";
		}else
			return "default";
	}

}

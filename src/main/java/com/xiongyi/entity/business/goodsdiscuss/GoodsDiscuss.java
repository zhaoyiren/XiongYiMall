package com.xiongyi.entity.business.goodsdiscuss;

/** 
 * 说明： 商品评论表
 * 创建人：XY
 * 创建时间：2018-01-27
 * @version
 */
 public class GoodsDiscuss {
 
		private String GOODSID;	//与商品ID关联
		private String DISCUSSCONTENT;	//评论内容
		private String DISCUSSTIME;	//评论时间
		private String DISCUSSSTATUS;	//评论状态
		private String USERID;	//与用户ID关联
		private String DISCUSSSCORE;	//评价得分
	
	
	public String getGOODSID() {
		return GOODSID;
	}
	public void setGOODSID(String GOODSID) {
		this.GOODSID = GOODSID;
	}
	public String getDISCUSSCONTENT() {
		return DISCUSSCONTENT;
	}
	public void setDISCUSSCONTENT(String DISCUSSCONTENT) {
		this.DISCUSSCONTENT = DISCUSSCONTENT;
	}
	public String getDISCUSSTIME() {
		return DISCUSSTIME;
	}
	public void setDISCUSSTIME(String DISCUSSTIME) {
		this.DISCUSSTIME = DISCUSSTIME;
	}
	public String getDISCUSSSTATUS() {
		return DISCUSSSTATUS;
	}
	public void setDISCUSSSTATUS(String DISCUSSSTATUS) {
		this.DISCUSSSTATUS = DISCUSSSTATUS;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String USERID) {
		this.USERID = USERID;
	}
	public String getDISCUSSSCORE() {
		return DISCUSSSCORE;
	}
	public void setDISCUSSSCORE(String DISCUSSSCORE) {
		this.DISCUSSSCORE = DISCUSSSCORE;
	}
 
 }
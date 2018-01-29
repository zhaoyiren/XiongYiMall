package com.xiongyi.entity.business.order;

/** 
 * 说明： 订单表
 * 创建人：XY
 * 创建时间：2018-01-27
 * @version
 */
 public class Order {
 
		private String GOODSID;	//与商品ID关联
		private String ORDERNO;	//订单编号
		private String ORDERSTATUS;	//订单状态
		private String ORDERCREATETIME;	//订单创建时间
		private String USERID;	//与用户ID关联
		private String ORDERCANCELTIME;	//订单取消时间
		private String DISCUSSSID;	//关联评论ID
	
	
	public String getGOODSID() {
		return GOODSID;
	}
	public void setGOODSID(String GOODSID) {
		this.GOODSID = GOODSID;
	}
	public String getORDERNO() {
		return ORDERNO;
	}
	public void setORDERNO(String ORDERNO) {
		this.ORDERNO = ORDERNO;
	}
	public String getORDERSTATUS() {
		return ORDERSTATUS;
	}
	public void setORDERSTATUS(String ORDERSTATUS) {
		this.ORDERSTATUS = ORDERSTATUS;
	}
	public String getORDERCREATETIME() {
		return ORDERCREATETIME;
	}
	public void setORDERCREATETIME(String ORDERCREATETIME) {
		this.ORDERCREATETIME = ORDERCREATETIME;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String USERID) {
		this.USERID = USERID;
	}
	public String getORDERCANCELTIME() {
		return ORDERCANCELTIME;
	}
	public void setORDERCANCELTIME(String ORDERCANCELTIME) {
		this.ORDERCANCELTIME = ORDERCANCELTIME;
	}
	public String getDISCUSSSID() {
		return DISCUSSSID;
	}
	public void setDISCUSSSID(String DISCUSSSID) {
		this.DISCUSSSID = DISCUSSSID;
	}
 
 }
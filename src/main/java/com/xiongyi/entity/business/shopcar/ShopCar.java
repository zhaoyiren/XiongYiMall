package com.xiongyi.entity.business.shopcar;

/** 
 * 说明： 购物车表
 * 创建人：XY
 * 创建时间：2018-01-27
 * @version
 */
 public class ShopCar {
 
		private String GOODSID;	//与商品ID关联
		private String USERID;	//与用户ID关联
	
	
	public String getGOODSID() {
		return GOODSID;
	}
	public void setGOODSID(String GOODSID) {
		this.GOODSID = GOODSID;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String USERID) {
		this.USERID = USERID;
	}
 
 }
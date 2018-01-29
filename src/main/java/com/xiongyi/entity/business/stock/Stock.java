package com.xiongyi.entity.business.stock;

/** 
 * 说明： 库存
 * 创建人：XY
 * 创建时间：2018-01-27
 * @version
 */
 public class Stock {
 
		private String GOODSID;	//与商品ID关联
		private String INVENTORYNUM;	//库存量
	
	
	public String getGOODSID() {
		return GOODSID;
	}
	public void setGOODSID(String GOODSID) {
		this.GOODSID = GOODSID;
	}
	public String getINVENTORYNUM() {
		return INVENTORYNUM;
	}
	public void setINVENTORYNUM(String INVENTORYNUM) {
		this.INVENTORYNUM = INVENTORYNUM;
	}
 
 }
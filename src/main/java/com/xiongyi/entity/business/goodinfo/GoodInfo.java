package com.xiongyi.entity.business.goodinfo;

/** 
 * 说明： 商品信息表
 * 创建人：XY
 * 创建时间：2018-01-27
 * @version
 */
 public class GoodInfo {
 
		private String GOODSID;	//与商品ID关联
		private String GOODDESCRIBE;	//商品描述
	
	
	public String getGOODSID() {
		return GOODSID;
	}
	public void setGOODSID(String GOODSID) {
		this.GOODSID = GOODSID;
	}
	public String getGOODDESCRIBE() {
		return GOODDESCRIBE;
	}
	public void setGOODDESCRIBE(String GOODDESCRIBE) {
		this.GOODDESCRIBE = GOODDESCRIBE;
	}
 
 }
package com.xiongyi.entity.business.stockrecord;

/** 
 * 说明： 库存记录
 * 创建人：XY
 * 创建时间：2018-01-27
 * @version
 */
 public class StockRecord {
 
		private String GOODSID;	//与商品ID关联
		private String TYPE;	//库存方式
		private String TYPENUM;	//库存方式对应的数量
		private String OPREATOR;	//执行人
	
	
	public String getGOODSID() {
		return GOODSID;
	}
	public void setGOODSID(String GOODSID) {
		this.GOODSID = GOODSID;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String TYPE) {
		this.TYPE = TYPE;
	}
	public String getTYPENUM() {
		return TYPENUM;
	}
	public void setTYPENUM(String TYPENUM) {
		this.TYPENUM = TYPENUM;
	}
	public String getOPREATOR() {
		return OPREATOR;
	}
	public void setOPREATOR(String OPREATOR) {
		this.OPREATOR = OPREATOR;
	}
 
 }
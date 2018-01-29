package com.xiongyi.entity.business.goods;

/** 
 * 说明： 商品表
 * 创建人：XY
 * 创建时间：2018-01-27
 * @version
 */
 public class Goods {
 
		private String GOODSNAME;	//商品名称
		private String GOODSCODE;	//商品编号
		private String GOODSPICTURE;	//商品封面图
		private String PUBLISHDATE;	//上货时间
		private String DOWNPUBLISHDATE;	//下架时间
	
	
	public String getGOODSNAME() {
		return GOODSNAME;
	}
	public void setGOODSNAME(String GOODSNAME) {
		this.GOODSNAME = GOODSNAME;
	}
	public String getGOODSCODE() {
		return GOODSCODE;
	}
	public void setGOODSCODE(String GOODSCODE) {
		this.GOODSCODE = GOODSCODE;
	}
	public String getGOODSPICTURE() {
		return GOODSPICTURE;
	}
	public void setGOODSPICTURE(String GOODSPICTURE) {
		this.GOODSPICTURE = GOODSPICTURE;
	}
	public String getPUBLISHDATE() {
		return PUBLISHDATE;
	}
	public void setPUBLISHDATE(String PUBLISHDATE) {
		this.PUBLISHDATE = PUBLISHDATE;
	}
	public String getDOWNPUBLISHDATE() {
		return DOWNPUBLISHDATE;
	}
	public void setDOWNPUBLISHDATE(String DOWNPUBLISHDATE) {
		this.DOWNPUBLISHDATE = DOWNPUBLISHDATE;
	}
 
 }
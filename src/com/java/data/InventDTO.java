package com.java.data;

import java.sql.Date;

public class InventDTO {

	// 사원 테이블
	private int eNum;
	private String eName;
	private String ePass;
	private String eBranch;
	
	// 재고현황 테이블
	private int iNum;
	private String iOffName;
	private String iCode;
	private String iName;
	private String iDate;
	private int iAmount;
	private int iPrice;
	public int geteNum() {
		return eNum;
	}
	public void seteNum(int eNum) {
		this.eNum = eNum;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public String getePass() {
		return ePass;
	}
	public void setePass(String ePass) {
		this.ePass = ePass;
	}
	public String geteBranch() {
		return eBranch;
	}
	public void seteBranch(String eBranch) {
		this.eBranch = eBranch;
	}
	public int getiNum() {
		return iNum;
	}
	public void setiNum(int iNum) {
		this.iNum = iNum;
	}
	public String getiOffName() {
		return iOffName;
	}
	public void setiOffName(String iOffName) {
		this.iOffName = iOffName;
	}
	public String getiCode() {
		return iCode;
	}
	public void setiCode(String iCode) {
		this.iCode = iCode;
	}
	public String getiName() {
		return iName;
	}
	public void setiName(String iName) {
		this.iName = iName;
	}
	public String getiDate() {
		return iDate;
	}
	public void setiDate(String iDate) {
		this.iDate = iDate;
	}
	public int getiAmount() {
		return iAmount;
	}
	public void setiAmount(int iAmount) {
		this.iAmount = iAmount;
	}
	public int getiPrice() {
		return iPrice;
	}
	public void setiPrice(int iPrice) {
		this.iPrice = iPrice;
	}



}

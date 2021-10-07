package com.vt.demo16.model;

import java.math.BigDecimal;
import java.util.Date;

public class Join01{
    private String item;

    private Integer ctNo;

    private String proCtr;

    private String salCtr;

    private Integer custNo;

    private String workZip;

    private Date ctDate;

    private BigDecimal ctAmnt;

    private String bsItem;

    private BigDecimal bsNo;

    private Date bsCfDate;

    private String comp2;

    private String remark;

    private String salTntr;

    private String srvTntr;

    private BigDecimal salBegCost;

    private String recTntr;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    public Integer getCtNo() {
        return ctNo;
    }

    public void setCtNo(Integer ctNo) {
        this.ctNo = ctNo;
    }

    public String getProCtr() {
        return proCtr;
    }

    public void setProCtr(String proCtr) {
        this.proCtr = proCtr == null ? null : proCtr.trim();
    }

    public String getSalCtr() {
        return salCtr;
    }

    public void setSalCtr(String salCtr) {
        this.salCtr = salCtr == null ? null : salCtr.trim();
    }

    public Integer getCustNo() {
        return custNo;
    }

    public void setCustNo(Integer custNo) {
        this.custNo = custNo;
    }

    public String getWorkZip() {
        return workZip;
    }

    public void setWorkZip(String workZip) {
        this.workZip = workZip == null ? null : workZip.trim();
    }

    public Date getCtDate() {
        return ctDate;
    }

    public void setCtDate(Date ctDate) {
        this.ctDate = ctDate;
    }

    public BigDecimal getCtAmnt() {
        return ctAmnt;
    }

    public void setCtAmnt(BigDecimal ctAmnt) {
        this.ctAmnt = ctAmnt;
    }
    
    public String getBsItem() {
        return bsItem;
    }

    public void setBsItem(String bsItem) {
        this.bsItem = bsItem == null ? null : bsItem.trim();
    }

    public BigDecimal getBsNo() {
        return bsNo;
    }

    public void setBsNo(BigDecimal bsNo) {
        this.bsNo = bsNo;
    }

    public Date getBsCfDate() {
        return bsCfDate;
    }

    public void setBsCfDate(Date bsCfDate) {
        this.bsCfDate = bsCfDate;
    }

    public String getComp2() {
        return comp2;
    }

    public void setComp2(String comp2) {
        this.comp2 = comp2 == null ? null : comp2.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSalTntr() {
        return salTntr;
    }

    
    public void setSalTntr(String salTntr) {
        this.salTntr = salTntr == null ? null : salTntr.trim();
    }

    
    public String getSrvTntr() {
        return srvTntr;
    }

    
    public void setSrvTntr(String srvTntr) {
        this.srvTntr = srvTntr == null ? null : srvTntr.trim();
    }

    
    public BigDecimal getSalBegCost() {
        return salBegCost;
    }

    
    public void setSalBegCost(BigDecimal salBegCost) {
        this.salBegCost = salBegCost;
    }

    
    public String getRecTntr() {
        return recTntr;
    }

    
    public void setRecTntr(String recTntr) {
        this.recTntr = recTntr == null ? null : recTntr.trim();
    }

    
}

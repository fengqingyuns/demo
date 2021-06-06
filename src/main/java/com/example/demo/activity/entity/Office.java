/**
 * Copyright &copy; Edwin All rights reserved.
 */
package com.example.demo.activity.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 机构Entity
 * @author Edwin
 * @version 2016-05-15
 */
public class Office  {


	protected static final long serialVersionUID = 1L;
//	protected Area area;		// 归属区域
	protected String code; 	// 机构编码
	protected String type; 	// 机构类型（1：公司；2：部门；3：小组）
	protected String grade; 	// 机构等级（1：一级；2：二级；3：三级；4：四级）
	protected String address; // 联系地址
	protected String zipCode; // 邮政编码
	protected String master; 	// 负责人
	protected String phone; 	// 电话
    protected String legalOrgCode; // 法人组织编码
	protected String fax; 	// 传真
	protected String email; 	// 邮箱
	protected String useable;//是否可用
	protected RocIdUser primaryPerson;//主负责人
	protected RocIdUser deputyPerson;//副负责人
 //   protected BaseBusinessUnit unit;//事业群
  //  protected BaseDistrict district;//大区
	protected List<String> childDeptList;//快速添加子部门
    protected String calcTaxWay;    //计税方式
    protected Date calcTaxStartDate; //计税开始时间
    protected String isOpenedDataSyn;   //数据同步
    protected String isCanModifyDoc;   //允许补单

    protected String parentCode;    //父节点Code 仅导出使用
    protected String parentName;    //父节点名称  仅导出使用

    private String businessTypeId;  //业务类型Id

    private Office company; //部门查询带公司（自定义）


    public String getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(String businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public Office(){
		super();
		this.type = "2";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLegalOrgCode() {
		return legalOrgCode;
	}

	public void setLegalOrgCode(String legalOrgCode) {
		this.legalOrgCode = legalOrgCode;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public RocIdUser getPrimaryPerson() {
		return primaryPerson;
	}

	public void setPrimaryPerson(RocIdUser primaryPerson) {
		this.primaryPerson = primaryPerson;
	}

	public RocIdUser getDeputyPerson() {
		return deputyPerson;
	}

	public void setDeputyPerson(RocIdUser deputyPerson) {
		this.deputyPerson = deputyPerson;
	}

	public List<String> getChildDeptList() {
		return childDeptList;
	}

	public void setChildDeptList(List<String> childDeptList) {
		this.childDeptList = childDeptList;
	}

	public String getCalcTaxWay() {
		return calcTaxWay;
	}

	public void setCalcTaxWay(String calcTaxWay) {
		this.calcTaxWay = calcTaxWay;
	}

	public Date getCalcTaxStartDate() {
		return calcTaxStartDate;
	}

	public void setCalcTaxStartDate(Date calcTaxStartDate) {
		this.calcTaxStartDate = calcTaxStartDate;
	}

	public String getIsOpenedDataSyn() {
		return isOpenedDataSyn;
	}

	public void setIsOpenedDataSyn(String isOpenedDataSyn) {
		this.isOpenedDataSyn = isOpenedDataSyn;
	}

	public String getIsCanModifyDoc() {
		return isCanModifyDoc;
	}

	public void setIsCanModifyDoc(String isCanModifyDoc) {
		this.isCanModifyDoc = isCanModifyDoc;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Office getCompany() {
		return company;
	}

	public void setCompany(Office company) {
		this.company = company;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Office [code=" + code + ", type=" + type + ", grade=" + grade + ", address=" + address + ", zipCode="
				+ zipCode + ", master=" + master + ", phone=" + phone + ", legalOrgCode=" + legalOrgCode + ", fax="
				+ fax + ", email=" + email + ", useable=" + useable + ", primaryPerson=" + primaryPerson
				+ ", deputyPerson=" + deputyPerson + ", childDeptList=" + childDeptList + ", calcTaxWay=" + calcTaxWay
				+ ", calcTaxStartDate=" + calcTaxStartDate + ", isOpenedDataSyn=" + isOpenedDataSyn
				+ ", isCanModifyDoc=" + isCanModifyDoc + ", parentCode=" + parentCode + ", parentName=" + parentName
				+ ", businessTypeId=" + businessTypeId + ", company=" + company + "]";
	}
    
    
}
package com.olive.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Table(name="prod_tab")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue	
	@Column(name="pid")
	private Integer prodId;
	@Column(name="pname")
	private String prodName;
	@Column(name="pcost")
	private Double prodCost;
	@Column(name="pcode")
	private String prodCode;
	@Column(name="pvendor")
	private String prodVendor;
	@Column(name="gst")
	private double prodGst;
	@Column(name="gstdiscount")
	private double gstDiscount;

}

package com.olive.util;

import com.olive.model.Product;

public interface ProductUtil {
	
	public static void calculation(Product p) {
		  Double cost = p.getProdCost();
		  p.setGstDiscount(cost *  12/100);
		  p.setProdGst(cost * 18/100);
	}

}

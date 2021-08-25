package com.olive.service;

import java.util.List;

import com.olive.model.Product;

public interface IProductService {
	Integer	saveProduct(Product p);
	void updateProduct(Product p);
	void deleteProduct(Integer id);
	List<Product>	getAllProduct();
	Product getOneProduct(Integer id);
   void updateProductCodeById(Integer id,String code);

}

package com.olive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olive.exception.ProductNotFoundException;
import com.olive.model.Product;
import com.olive.repo.ProdRepositiory;
import com.olive.service.IProductService;
import com.olive.util.ProductUtil;
@Service
public class IProductServiceImp implements IProductService {

	@Autowired
	private ProdRepositiory repo;


	public Integer saveProduct(Product p) {
		ProductUtil.calculation(p);
		repo.save(p);
		return p.getProdId();
	}


	public void updateProduct(Product p) {
		//checking if exit then update
		if(p.getProdId()==null ||
				!repo.existsById(p.getProdId())){
			throw new ProductNotFoundException("Product Can Not be Updated !Id " + p.getProdId());
		}
		ProductUtil.calculation(p);
		repo.save(p);
	}


	public void deleteProduct(Integer id) {	
		boolean exit = repo.existsById(id);
		if(exit==true) {
			repo.deleteById(id);
		}
		else {
			throw new ProductNotFoundException("Record Not Exit With ID " + id +" Try with Other..");
		}
	}


	public List<Product> getAllProduct() {

		return repo.findAll();
	}


	public Product getOneProduct(Integer id) {

		return repo.findById(id).orElseThrow(
				()->new ProductNotFoundException("Product Not Exit With Id " + id));
	}

    @Transactional
	public void updateProductCodeById(Integer id, String code) {
		if(!repo.existsById(id)) {
			throw new ProductNotFoundException("Product Can Not be Updated !Id " + id);
		}
		repo.updateProductCodeById(id, code);
	}





}

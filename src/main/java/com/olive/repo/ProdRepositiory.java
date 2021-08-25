package com.olive.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.olive.model.Product;

public interface ProdRepositiory extends JpaRepository<Product, Integer>{
	@Modifying //non select operation
	@Query("update Product set prodCode=:code where prodId=:id")
	public void updateProductCodeById(Integer id,String code);

}

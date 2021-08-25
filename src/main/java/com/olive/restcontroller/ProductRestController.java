package com.olive.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olive.exception.ProductNotFoundException;
import com.olive.model.Product;
import com.olive.service.IProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/prod")
public class ProductRestController {
private static final  Logger logs=LoggerFactory.getLogger(ProductRestController.class);
      
	@Autowired
	IProductService service;

	
	
	@PostMapping("/save")
	@ApiOperation("TEST PRODUCT FOR SAVE OPERATION")
	public ResponseEntity<String> saveProd(@RequestBody Product prod) {
		logs.info("Enter into the SAVE METHOD");
		ResponseEntity<String> res=null;
		try {
			Integer id = service.saveProduct(prod);
			String msg="Product Id " + id + "Created";
			logs.debug(" DEBUG  :: {} ", msg);
			 res=new ResponseEntity<String>(msg, HttpStatus.CREATED);
		} catch (Exception e) {
			logs.error("Probem IS {} ",e.getMessage());
			e.printStackTrace();
			res=new ResponseEntity<String>("Unable To Process The Request "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logs.info("Come Out of Save Method");
		return res;
	}

	@GetMapping("/all")
	@ApiOperation("TEST  FOR ALL PRODUCT ")
	public ResponseEntity<?> getAllProd() {
		logs.info("Entered into GetAll Method  :: ");
		ResponseEntity<?> res=null;
		try {
			List<Product> list = service.getAllProduct();
			logs.debug("Getting All Product From DB {} ");
			res=new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} catch (Exception e) {
			logs.error("Probem IS {}  ",e.getMessage());
			e.printStackTrace();
			res=new ResponseEntity<String>("Request Not Process " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		   logs.info("Come Out From These Method");
		return res;
	}

	@GetMapping("/one/{id}")
	@ApiOperation("TEST FOR FIND ONE PRODUCT")
	public ResponseEntity<?> getOneProd(@PathVariable Integer id) {
		logs.info("Entered into GetOneProd Method :: {} ");
		ResponseEntity<?> resp=null;
                            try {
                            	Product prod = service.getOneProduct(id);  
                            	logs.debug("Find Product With ID {} " ,id);
                            	resp=new ResponseEntity<Product>(prod, HttpStatus.OK);
							} catch (ProductNotFoundException e) {
								logs.error("Problem IS {} ",e.getMessage());
								e.printStackTrace();
								throw e;
							}
                            logs.info("Came Out These Method :: ");
		 return resp;
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation("TEST FOR DELETE ONE PRODUCT")
	public ResponseEntity<String> deleteProd(@PathVariable Integer id){
		logs.info("Enterd into Delete Method  {} ");
		ResponseEntity<String> resp=null;
		try {
			service.deleteProduct(id);
			logs.debug("Delete The Product With ID :: {} ",id);
			resp=new ResponseEntity<String>("Product deleted Successfull", HttpStatus.OK);
		} catch (Exception e) {
			logs.error("Probem IS {}",e.getMessage());
	//	 e.printStackTrace();
		 throw e;
		}
			logs.info("Come Out These Method :: ");
		return resp; 
	}

	@PutMapping("/modifies")
	@ApiOperation("TEST FOR FULL UPDATE PRODUCT")
	public ResponseEntity<String> updateProd(@RequestBody Product p){
		logs.info("Entered into Update Method {} ");
		ResponseEntity<String> resp=null;
		try {
			service.updateProduct(p);
			logs.debug("Product Updated Successfully With Id {}",p.getProdId());
			resp=new ResponseEntity<String>("Product Updated With Id "+p.getProdId(), HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			logs.error("Problem Is {} ",e.getMessage());
			throw e;
		}
		logs.info("Come Out These Method {} :: ");
		return resp;
		
	}
	
	@PatchMapping("/update/{id}/{code}")
	@ApiOperation("TEST FOR PARTIAL UPDATE PRODUCT")
	public ResponseEntity<?> updateProdCode(
			@PathVariable Integer id,
			@PathVariable String code
			) {
		logs.info("Entered Into Update With Patch Type Method {} ");
		ResponseEntity<?> resp=null;
                            try {
                            	service.updateProductCodeById(id, code);
                            	logs.debug("Code Update With ID  {} ",code ,id);
                    			resp=new ResponseEntity<String>("Product Updated With Id "+ id, HttpStatus.OK);
							} catch (ProductNotFoundException e) {
								logs.error("Problem Is {} ", e.getMessage());
								e.printStackTrace();
								throw e;
							}
              logs.info("Came Out These Method :: ");              
		 return resp;
	}	

}

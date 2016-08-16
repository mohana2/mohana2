package com.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.ProductModel;
import com.service.ProductService;

@Controller
public class AdminController {
	

	@ModelAttribute("ob")
	public ProductModel construct(){
		return new ProductModel();
	}
	@Autowired
	private ProductService productservice;
	
	@RequestMapping(value="/product")
	public String prod()
	{
	return "product";	
	}
	
	@RequestMapping(value="/productmodel.do", method=RequestMethod.POST)
	public String doActions(@ModelAttribute("ob") ProductModel productModel, BindingResult result, @RequestParam String action, Map<String, Object>map){
	    ProductModel productmodelResult = new ProductModel();
	    switch(action.toLowerCase()){
	    case "add":
	    	productservice.add(productModel);
	    	productmodelResult = productModel;
	    	break;
	    	
	    case "edit":
	    	productservice.edit(productModel);
	    	productmodelResult = productModel;
	    	break;
	    	
	    case "delete":
	    	productservice.delete(productModel.getId());
	    	productmodelResult = new ProductModel();
	    	break;
	    	
	    case "search":
	    	ProductModel searchedProductModel = productservice.getProductModel(productModel.getId());
	    	productmodelResult = searchedProductModel!=null ? searchedProductModel : new ProductModel();
	    	break;
	    }
	    map.put("productModel",productmodelResult);
	    map.put("productmodelList", productservice.getAllProductModel());
	    
		return "product";
	}

}

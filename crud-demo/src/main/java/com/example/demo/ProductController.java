package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/")
	public String productHomePage(Model model) {
		List<Product> listofproduct = productService.listAll();
		model.addAttribute("listofproduct", listofproduct);
		return "home";

	}

	@RequestMapping("/addproduct")
	public String addProducts(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "addproduct";
	}

	@RequestMapping(value = "/saveproduct", method=RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product)
	{
		productService.save(product);
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView editProduct(@PathVariable(name="id") Long id) {
		ModelAndView modelAndView=new ModelAndView("editproduct");
		Product product=productService.get(id);
		modelAndView.addObject("product",product);
	
		return modelAndView;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name="id") Long id) {
		productService.delete(id);
	
		return "redirect:/";
	}

}

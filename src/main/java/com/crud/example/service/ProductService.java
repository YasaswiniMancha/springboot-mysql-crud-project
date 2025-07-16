package com.crud.example.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.example.entity.Product;
import com.crud.example.repo.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;

	public Product saveProduct(Product product) {
	    if (product.getId() != 0 && repository.existsById(product.getId())) {
	        throw new IllegalArgumentException("Product with ID " + product.getId() + " already exists.");
	    }
	    product.setId(0); // Ensure new ID is generated
	    return repository.save(product);
	}
	
	public List<Product> saveProducts(List<Product> products) {
	    // Check for duplicate IDs in the input list
	    Set<Integer> ids = new HashSet<>();
	    for (Product product : products) {
	        if (product.getId() != 0 && !ids.add(product.getId())) {
	            throw new IllegalArgumentException("Duplicate product ID found: " + product.getId());
	        }
	    }
	    // Reset IDs to ensure database generates new ones
	    products.forEach(product -> product.setId(0));
	    return repository.saveAll(products);
	}
	

	public List<Product> getProducts() {
		return repository.findAll();
	}

	public Product getProductById(int id) {
		return repository.findById(id).orElse(null);
	}

	public Product getProductByName(String name) {
		return repository.findByName(name);
	}

	public String deleteProduct(int id) {
		repository.deleteById(id);
		return "product removed !! " + id;
	}

	public Product updateProduct(Product product) {
		Product existingProduct = repository.findById(product.getId()).orElse(null);
		existingProduct.setName(product.getName());
		existingProduct.setQuantity(product.getQuantity());
		existingProduct.setPrice(product.getPrice());
		return repository.save(existingProduct);
	}

}

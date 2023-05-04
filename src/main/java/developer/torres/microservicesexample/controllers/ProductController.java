package developer.torres.microservicesexample.controllers;

import developer.torres.microservicesexample.model.Product;
import developer.torres.microservicesexample.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @PostMapping
    public Product createProduct(@RequestBody  Product product){
        return productService.createProduct(product);
    }
}

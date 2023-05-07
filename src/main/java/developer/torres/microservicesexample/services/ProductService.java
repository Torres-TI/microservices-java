package developer.torres.microservicesexample.services;

import developer.torres.microservicesexample.model.Product;
import developer.torres.microservicesexample.repository.ProductOldRepository;
import developer.torres.microservicesexample.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    /**
     * Method to get all products
     * @return products
     */
    public List<Product> getAllProducts(){
        return  productRepository.findAll();
    }

    /**
     * Method to get a product by id
     * @param id to get product
     * @return product
     */
    public Optional<Product> getProductById(Integer id){
       return productRepository.findById(id);
    }

    /**
     * Method to create new product
     * @param product to be added on list.
     * @return  product added on list.
     * */

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    /**
     * Method to delete a product
     * @param id to delete
     */
    public void deleteProduct(Integer id){
        productRepository.deleteById(id);
    }

    /**
     * Method to update product
     * @param id to filter products.
     * @param product to be updated.
     * @return product
     */
    public Product updateProduct(Integer id,Product product){
        product.setId(id);
       return productRepository.save(product);
    }


}

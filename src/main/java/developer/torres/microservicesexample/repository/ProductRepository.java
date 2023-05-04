package developer.torres.microservicesexample.repository;

import developer.torres.microservicesexample.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private Integer lastId = 0;
    private List<Product> products = new ArrayList<>();

    //#region Methods
    /**
     * Method to get all products
     * @return products
     */
    public List<Product>getAllProducts(){
        return products;
    }

    /**
     * Method to get a product by id
     * @param id to get product
     * @return product
     */
    public Optional<Product> getProductById(Integer id){
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst();
    }

    /**
     * Method to create new product
     * @param product to be added on list.
     * @return  product added on list.
     * */
    public Product createProduct(Product product){
        lastId++;
        product.setId(lastId);
        products.add(product);
        return product;
    }

    /**
     * Method to delete a product
     * @param id to delete
     */

    public void deleteProduct(Integer id){
        products.removeIf( product -> product.getId() == id);
    }

    /**
     * Method to update product
     * @param product to be updated.
     * @return product
     */
    public Product updateProduct(Product product){
       Optional<Product> hasProduct = getProductById(product.getId());
       if(hasProduct.isEmpty()){
           throw new InputMismatchException("Product not found");
       }
        deleteProduct(product.getId());
        products.add(product);
        return product;
    }
    //#endregion
}

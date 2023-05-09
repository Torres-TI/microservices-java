package developer.torres.microservicesexample.services;

import developer.torres.microservicesexample.model.Product;
import developer.torres.microservicesexample.model.exception.ResourceNotFoundException;
import developer.torres.microservicesexample.repository.ProductOldRepository;
import developer.torres.microservicesexample.repository.ProductRepository;
import developer.torres.microservicesexample.shared.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    /**
     * Method to get all products
     * @return products
     */
    public List<ProductDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> new ModelMapper().map(product, ProductDTO.class ))
                .collect(Collectors.toList());
    }

    /**
     * Method to get a product by id
     * @param id to get product
     * @return product
     */
    public Optional<ProductDTO> getProductById(Integer id){
        // get product by id
        Optional<Product> product =  productRepository.findById(id);
        // exception
        if(product.isEmpty()){
            throw new ResourceNotFoundException("Product not found");
        }
        // convert product to dto
        ProductDTO dto = new ModelMapper().map(product.get(), ProductDTO.class);
        // return dto
        return Optional.of(dto);
    }

    /**
     * Method to create new product
     * @param product to be added on list.
     * @return  product added on list.
     * */

    public ProductDTO createProduct(ProductDTO productDto){
        productDto.setId(null);
        ModelMapper mapper =  new ModelMapper();
        Product product = mapper.map(productDto, Product.class);
        product = productRepository.save(product);
        productDto.setId(product.getId());
        return productDto;
    }

    /**
     * Method to delete a product
     * @param id to delete
     */
    public void deleteProduct(Integer id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw  new ResourceNotFoundException("Product doesn't exist");
        }
        productRepository.deleteById(id);
    }

    /**
     * Method to update product
     * @param id to filter products.
     * @param product to be updated.
     * @return product
     */
    public ProductDTO updateProduct(Integer id,ProductDTO productDTO){
        productDTO.setId(id);
        ModelMapper mapper = new ModelMapper();
        Product product = mapper.map(productDTO, Product.class);
        productRepository.save(product);
        return productDTO;
    }


}

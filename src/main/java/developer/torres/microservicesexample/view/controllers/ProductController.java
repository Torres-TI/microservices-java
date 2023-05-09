package developer.torres.microservicesexample.view.controllers;

import developer.torres.microservicesexample.model.Product;
import developer.torres.microservicesexample.model.exception.ResourceNotFoundException;
import developer.torres.microservicesexample.services.ProductService;
import developer.torres.microservicesexample.shared.ProductDTO;
import developer.torres.microservicesexample.view.model.ProductRequest;
import developer.torres.microservicesexample.view.model.ProductResponse;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
         List< ProductDTO > products = productService.getAllProducts();
        ModelMapper mapper = new ModelMapper();
        List<ProductResponse> response = products.stream()
                .map(productDTO -> mapper.map(productDTO, ProductResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
   @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductResponse>> getProductById(@PathVariable Integer id){
        Optional<ProductDTO> productDTO =  productService.getProductById(id);
        if(productDTO.isEmpty()){
            throw new ResourceNotFoundException("Product not found");
        }
        ModelMapper mapper = new ModelMapper();
        ProductResponse response = mapper.map(productDTO.get(), ProductResponse.class);
        return new ResponseEntity<>(Optional.of(response), HttpStatus.OK );

    }
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody  ProductResponse productRes){
        ModelMapper mapper = new ModelMapper();
        ProductDTO productDTO = mapper.map(productRes, ProductDTO.class);
        productDTO = productService.createProduct(productDTO);
        return new ResponseEntity<>(mapper.map(productDTO, ProductResponse.class), HttpStatus.CREATED);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable Integer id){
        ModelMapper mapper = new ModelMapper();
        ProductDTO productDTO = mapper.map(productRequest, ProductDTO.class);
        productDTO = productService.updateProduct(id, productDTO);
        return new ResponseEntity<>(mapper.map(productDTO, ProductResponse.class), HttpStatus.CREATED);
    }
}

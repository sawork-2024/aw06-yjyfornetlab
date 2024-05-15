package com.example.products.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.products.mapper.ProductMapper;
import com.example.products.service.ProductService;
import com.example.webpos.rest.api.ProductsApi;
import com.example.webpos.rest.dto.ProductDto;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api")
public class ProductRestController implements ProductsApi{

    private final ProductService productService;
    private final ProductMapper productMapper;
    public ProductRestController(ProductService productService,ProductMapper productMapper){
        this.productService = productService;
        this.productMapper = productMapper;
    }
    
    @Override
    public ResponseEntity<List<ProductDto>> listProducts(){
        List<ProductDto> productsDto = new ArrayList<>(productMapper.toProductDtos(this.productService.products()));
        if(productsDto.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productsDto,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDto> showProductById(String productId){
        ProductDto product = this.productMapper.toProductDto(this.productService.findProductById(productId));
        if(product==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
}

package com.swati.ecom_proj.controller;

import com.swati.ecom_proj.model.Product;
import com.swati.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;


@GetMapping("/products")
public ResponseEntity<List<Product>>getAllProduct(){

    return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
}

@GetMapping("/product/{id}")
public ResponseEntity<Product> getProduct(@PathVariable int id) {


    Product product = service.getProductById(id);

    if (product != null) {
        return new ResponseEntity<>(product, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
    @PostMapping("/product")
    public ResponseEntity<?> addProduct (@RequestPart Product product, @RequestPart MultipartFile imageFile) {
        try {
            System.out.println(product);
            Product product1 = service.addproduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProdcutId(@PathVariable int productId){

    Product product=service.getProductById(productId);
    byte[] imageFile=product.getImageDate();

    return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
    }


    @PutMapping("/product/{id}")
    public ResponseEntity<String>updateProduct(@PathVariable int id,@RequestPart Product product, @RequestPart MultipartFile imageFile) {

        try {
            Product updatedProduct = service.updateProduct(id, product, imageFile);

            if (updatedProduct != null) {
                return new ResponseEntity<>("updated", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e)
            {
                return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
            }
        }


        @DeleteMapping("/product/{id}")
        public ResponseEntity<String> deleteProduct ( @PathVariable int id){
            Product product = service.getProductById(id);

            if (product != null) {
                service.deleteProduct(id);
                return new ResponseEntity<>("Deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("product not found", HttpStatus.NOT_FOUND);
            }


        }


    }



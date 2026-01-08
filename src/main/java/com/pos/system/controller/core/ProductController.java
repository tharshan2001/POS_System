package com.pos.system.controller.core;

import com.pos.system.entity.Core.Product;
import com.pos.system.repository.core.ProductRepository;
import com.pos.system.utils.ImageUploadUtil;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ImageUploadUtil imageUploadUtil;

    public ProductController(ProductRepository productRepository, ImageUploadUtil imageUploadUtil) {
        this.productRepository = productRepository;
        this.imageUploadUtil = imageUploadUtil;
    }

    // ---------------- CREATE PRODUCT ----------------
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product createProduct(
            @ModelAttribute Product product,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {

        if (file != null && !file.isEmpty()) {
            String filename = imageUploadUtil.uploadImage(file);
            product.setImage(filename);
        }

        return productRepository.save(product);
    }

    // ---------------- GET ALL PRODUCTS ----------------
    @GetMapping
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        products.forEach(p -> p.setImage(getImageUrl(p.getImage())));
        return products;
    }

    // ---------------- GET PRODUCT BY ID ----------------
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setImage(getImageUrl(product.getImage()));
        return product;
    }

    // ---------------- UPDATE PRODUCT ----------------
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product updateProduct(
            @PathVariable Long id,
            @ModelAttribute Product updatedProduct,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) throws IOException {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Update fields
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setCostPrice(updatedProduct.getCostPrice());
        product.setBrand(updatedProduct.getBrand());
        product.setReorderLevel(updatedProduct.getReorderLevel());
        product.setCategory(updatedProduct.getCategory());
        product.setStatus(updatedProduct.getStatus());

        // Handle image update
        if (image != null && !image.isEmpty()) {
            // Delete old image if exists
            if (product.getImage() != null) {
                imageUploadUtil.deleteImage(product.getImage());
            }
            String filename = imageUploadUtil.uploadImage(image);
            product.setImage(filename);
        }

        return productRepository.save(product);
    }

    // ---------------- DELETE PRODUCT ----------------
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) throws IOException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Delete image if exists
        if (product.getImage() != null) {
            imageUploadUtil.deleteImage(product.getImage());
        }

        productRepository.delete(product);
    }

    // ---------------- SERVE PRODUCT IMAGE ----------------
    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) throws IOException {
        byte[] imageBytes = imageUploadUtil.getImageBytes(filename);
        ByteArrayResource resource = new ByteArrayResource(imageBytes);

        // Auto-detect MIME type from file extension
        MediaType mediaType = getMediaTypeForFileName(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                .contentLength(imageBytes.length)
                .contentType(mediaType)
                .body(resource);
    }

    // ---------------- UTILITY METHOD ----------------
    private MediaType getMediaTypeForFileName(String filename) {
        String lower = filename.toLowerCase();
        if (lower.endsWith(".png")) return MediaType.IMAGE_PNG;
        if (lower.endsWith(".gif")) return MediaType.IMAGE_GIF;
        if (lower.endsWith(".jpg") || lower.endsWith(".jpeg")) return MediaType.IMAGE_JPEG;
        return MediaType.APPLICATION_OCTET_STREAM; // fallback
    }

    private String getImageUrl(String filename) {
        if (filename == null) return null;
        return ServletUriComponentsBuilder.fromCurrentContextPath() // uses current host + port
                .path("/images/") // matches your ImageController mapping
                .path(filename)
                .toUriString();
    }
}
package com.example.spring_boot_hazelcast_cache.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Cacheable(value = "productCache", key = "#id")
    public String getProductById(String id) {
        simulateSlowService();
        return "Product " + id;
    }

    // Simulate a slow database service
    private void simulateSlowService() {
        try {
            Thread.sleep(3000); // Simulating a 3 second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @CacheEvict(value = "productCache", key = "#id")
    public void removeProductFromCache(String id) {
        // Code to remove product data from a persistent store if needed
    }

    @CachePut(value = "productCache", key = "#id")
    public String updateProductInCache(String id, String newProductData) {
        return newProductData;
    }

}

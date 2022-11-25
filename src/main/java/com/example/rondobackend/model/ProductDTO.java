package com.example.rondobackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    private MultipartFile multipartImage;
    private String name;
    private int price;
    private boolean isInSortiment;
}

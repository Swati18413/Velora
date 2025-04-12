package com.swati.ecom_proj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private Date releaseDate;
    private boolean productAvailable;
    private int stockQuantity;


    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageDate;


    public void setImageType(String contentType) {
        this.imageType = contentType;
    }

    public void setImageName(String originalFilename) {
        this.imageName = originalFilename;
    }

    public void setImageDate(byte[] bytes) {
        this.imageDate = bytes;
    }
}

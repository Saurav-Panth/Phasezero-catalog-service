package com.phasezero.code.entities;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.phasezero.code.enums.Category;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity	
public class Product {

    @Id
    private String partNumber;

    private String partName;

    @Enumerated(EnumType.STRING)
    private Category category;

    private double price;
    private int stock;
    private String brand;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

}

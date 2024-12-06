package com.ecommerce.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "categories")
//@Table(name = "categories") // Only if we want a different table name from the class name
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @NotBlank
    private String categoryName;

    /* It is considered a good practise to have a default constructor but it is NOT a strictly required
       specification by JPA.

       It is also important to have the getters and setters for the fields so that we
       get the values in the GET request. These fields inside the database will also be Null if getters and
       setters are not added
     */

}

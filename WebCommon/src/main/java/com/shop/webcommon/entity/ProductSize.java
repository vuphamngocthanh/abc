package com.shop.webcommon.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sizes")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "size")
    private String size;

    @ManyToMany(mappedBy = "productSizes")
    private List<Product> products;


}

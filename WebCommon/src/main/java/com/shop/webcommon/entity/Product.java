package com.shop.webcommon.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String nameProduct;

    @Column(name = "fabric_material")
    private String fabricMaterial;
    @Column(name = "detailed_description")
    private String detailedDescription;
    @Column(name = "price")
    private Integer price;

    @Column(name = "photo")
    private String photo;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "products_sizes" ,
            joinColumns = @JoinColumn (name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ProductSize> productSizes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "products_colors" ,
            joinColumns = @JoinColumn (name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ProductColor> productColors;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}

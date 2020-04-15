package com.groceries.groceries.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "product_media")
@SQLDelete(sql =
        "UPDATE product_media SET deleted_date=now() " +
                "WHERE product_media_id = ?")
@Where(clause = "deleted_date IS NULL")
public class ProductMedia extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_media_id")
    private Long productMediaId;

    @Column(name = "product_media_path")
    private String productMediaPath;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Long productId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    public Long getProductMediaId() {
        return productMediaId;
    }

    public void setProductMediaId(Long productMediaId) {
        this.productMediaId = productMediaId;
    }

    public String getProductMediaPath() {
        return productMediaPath;
    }

    public void setProductMediaPath(String productMediaPath) {
        this.productMediaPath = productMediaPath;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
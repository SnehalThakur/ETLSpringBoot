package com.java.sparketl.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="bestbuy")
public class BestBuy {

    @Id
    @Column(name = "name")
    String name;

    @Column(name = "short_description", columnDefinition = "longtext")
    String shortDescription;

    @Column(name = "best_selling_rank")
    Long bestSellingRank;

    @Column(name = "thumbnail_image")
    String thumbnailImage;

    @Column(name = "sale_price")
    Double salePrice;

    @Column(name = "manufacturer")
    String manufacturer;

    @Column(name = "url")
    String url;

    @Column(name = "type")
    String type;

    @Column(name = "image")
    String image;

    @Column(name = "customer_review_count")
    Long customerReviewCount;

    @Column(name = "shipping")
    String shipping;

    @Column(name = "sale_price_range")
    String salePriceRange;

    @Column(name = "object_id")
    String objectID;

    @Column(name = "categories")
    String categories;

    public BestBuy() {
    }

    public BestBuy(Object[] singleResult) {
    }

    public BestBuy(String name, String shortDescription, Long bestSellingRank, String thumbnailImage, Double salePrice, String manufacturer, String url, String type, String image, Long customerReviewCount, String shipping, String salePriceRange, String objectID, String categories) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.bestSellingRank = bestSellingRank;
        this.thumbnailImage = thumbnailImage;
        this.salePrice = salePrice;
        this.manufacturer = manufacturer;
        this.url = url;
        this.type = type;
        this.image = image;
        this.customerReviewCount = customerReviewCount;
        this.shipping = shipping;
        this.salePriceRange = salePriceRange;
        this.objectID = objectID;
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BestBuy bestBuy = (BestBuy) o;
        return Objects.equals(name, bestBuy.name) && Objects.equals(shortDescription, bestBuy.shortDescription) && Objects.equals(bestSellingRank, bestBuy.bestSellingRank) && Objects.equals(thumbnailImage, bestBuy.thumbnailImage) && Objects.equals(salePrice, bestBuy.salePrice) && Objects.equals(manufacturer, bestBuy.manufacturer) && Objects.equals(url, bestBuy.url) && Objects.equals(type, bestBuy.type) && Objects.equals(image, bestBuy.image) && Objects.equals(customerReviewCount, bestBuy.customerReviewCount) && Objects.equals(shipping, bestBuy.shipping) && Objects.equals(salePriceRange, bestBuy.salePriceRange) && Objects.equals(objectID, bestBuy.objectID) && Objects.equals(categories, bestBuy.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, shortDescription, bestSellingRank, thumbnailImage, salePrice, manufacturer, url, type, image, customerReviewCount, shipping, salePriceRange, objectID, categories);
    }

    @Override
    public String toString() {
        return "BestBuy{" +
                "name='" + name + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", bestSellingRank='" + bestSellingRank + '\'' +
                ", thumbnailImage='" + thumbnailImage + '\'' +
                ", salePrice='" + salePrice + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", image='" + image + '\'' +
                ", customerReviewCount='" + customerReviewCount + '\'' +
                ", shipping='" + shipping + '\'' +
                ", salePriceRange='" + salePriceRange + '\'' +
                ", objectID='" + objectID + '\'' +
                ", categories='" + categories + '\'' +
                '}';
    }
}

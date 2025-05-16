package org.zerock.mallapi.domain;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "tbl_product")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long pno;

    private String pname;
    
    private int price;

    private String pdesc;

    private boolean delFlag;


    @ElementCollection
    @Builder.Default
    private List<ProductImage> imageList = new ArrayList<>();

    public void changePrice(int price) {
        this.price = price;
    }

    public void changeDesc(String desc) {
        this.pdesc = desc;
    }

    public void changeName(String name) {
        this.pname = name;
    }

    public void addimage(ProductImage image) {
        image.setOrd(this.imageList.size());
        imageList.add(image);
    }


    public void addImageString(String fileName){

        ProductImage productImage = ProductImage.builder()
        .fileName(fileName)
        .build();
        addimage(productImage);

    }

    public void clearList(){
        this.imageList.clear();
    }
}

package dd.projects.ddshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product_attribute")
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "product_attributes")
    private List<Product> product;

    @OneToMany(mappedBy = "product_attributes",cascade = CascadeType.ALL)
    private List<AttributeValue> attributeValues;

    public ProductAttribute(String name) {
        this.name = name;
        this.product = new ArrayList<Product>();
        this.attributeValues = new ArrayList<AttributeValue>();
    }
}
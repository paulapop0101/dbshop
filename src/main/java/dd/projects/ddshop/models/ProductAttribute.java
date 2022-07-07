package dd.projects.ddshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product_attribute")
public class ProductAttribute {

    @Id
    private int id;

    private String name;

    @ManyToMany(mappedBy = "product_attributes")
    private List<Product> product;

    @OneToMany(mappedBy = "product_attributes")
    private List<AttributeValue> attributeValues;
}
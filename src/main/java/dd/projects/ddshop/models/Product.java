package dd.projects.ddshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.descriptor.sql.LongVarcharTypeDescriptor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product {

    @Id
    private int id;

    private String name;

    private String description;

    private float price;

    private int available_quantity;

    private Timestamp added_date;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_product_attribute",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_attribute_id",
                    referencedColumnName = "id"))
    private List<ProductAttribute> product_attributes;

    @ManyToMany(mappedBy = "products")
    List<Category> categories;

    @OneToMany(mappedBy = "product")
    private List<Variant> variants;
}

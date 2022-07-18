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
@Table(name="assigned_value")
public class AssignedValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="product_attribute_id", referencedColumnName = "id")
    ProductAttribute productAttribute;

    @OneToOne
    @JoinColumn(name="attribute_value_id", referencedColumnName = "id")
    AttributeValue attributeValue;

    @ManyToMany(mappedBy = "assignedValues",cascade = CascadeType.ALL)
    private List<Variant> variants;

    public AssignedValue(ProductAttribute attribute, AttributeValue attributeValue) {
        this.productAttribute = attribute;
        this.attributeValue = attributeValue;
        this.variants = new ArrayList<>();
    }
}
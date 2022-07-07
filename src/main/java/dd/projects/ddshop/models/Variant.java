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
@Table(name="variant")
public class Variant {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "variant_assigned_value",
            joinColumns = @JoinColumn(name = "assigned_value_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "variant_id",
                    referencedColumnName = "id"))
    private List<AssignedValue> assignedValues;

}
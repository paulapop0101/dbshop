package dd.projects.ddshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="variant")
public class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    private float price;

    private Timestamp added_date;

    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;

    @ManyToMany
    @JoinTable(name = "variant_assigned_value",
            joinColumns = @JoinColumn(name = "variant_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "assigned_value_id",
                    referencedColumnName = "id"))
    private List<AssignedValue> assignedValues;

    public Variant(int quantity, float price, Product product) {
        this.quantity = quantity;
        this.price=price;
        this.product=product;
        this.assignedValues = new ArrayList<>();
        long datetime = System.currentTimeMillis();
        this.added_date = new Timestamp(datetime);
    }


}
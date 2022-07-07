package dd.projects.ddshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cart_entry")
public class Cart_entry {

    @Id
    private int id;

    private int quantity;

    private float price_per_piece;

    private float total_price_per_entity;

    @OneToOne
    @JoinColumn(name="cart_id", referencedColumnName = "id")
    private Cart cart_id;

    @OneToOne
    @JoinColumn(name="variant_id", referencedColumnName = "id")
    private Variant variant_id;
}
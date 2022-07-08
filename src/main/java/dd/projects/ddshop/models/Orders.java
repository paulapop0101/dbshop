package dd.projects.ddshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user_id;

    @OneToOne
    @JoinColumn(name="cart_id", referencedColumnName = "id")
    private Cart cart_id;

    private float total_price;

    @Enumerated(EnumType.ORDINAL)
    private PaymentType payment_type;

    private Timestamp order_date;

    @OneToOne
    @JoinColumn(name="delivery_address", referencedColumnName = "id")
    private Address delivery_address;

    @OneToOne
    @JoinColumn(name="invoice_address", referencedColumnName = "id")
    private Address invoice_address;
}
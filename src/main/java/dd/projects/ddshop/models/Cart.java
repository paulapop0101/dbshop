package dd.projects.ddshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cart")
public class Cart {

    @Id
    private int id;

    @OneToOne
    @JoinColumn(name="user", referencedColumnName = "id")
    private User user;

    private float total_price;
}

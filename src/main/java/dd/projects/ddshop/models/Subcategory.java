package dd.projects.ddshop.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "subcategory")
public class Subcategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  @ManyToOne
  @JoinColumn(name="category_id", referencedColumnName = "id")
  private Category category;

  @ManyToMany
  @JoinTable(name = "subcategory_product_attribute",
          joinColumns = @JoinColumn(name = "product_attribute_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "subcategory_id",
                  referencedColumnName = "id"))
  private List<ProductAttribute> productAttributes;


  public Subcategory(String name, Category category) {
    this.name = name;
    this.category=category;
    this.productAttributes = new ArrayList<>();
  }


}

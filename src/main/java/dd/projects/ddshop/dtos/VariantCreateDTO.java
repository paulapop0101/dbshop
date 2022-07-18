package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data@AllArgsConstructor
public class VariantCreateDTO {
    private int quantity;

    private float price;

    private int product_id;

  // private List<AssignedValueDTO> assignedValues;
    private List<Integer> assignedValues;
}


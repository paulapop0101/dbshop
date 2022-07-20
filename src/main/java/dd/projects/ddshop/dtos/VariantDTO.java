package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VariantDTO {
    private String name;
    private float price;
    private int quantity;
    private String added_date;
    private List<AssignedValueDTO> assignedValues;
}

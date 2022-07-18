package dd.projects.ddshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VariantDTO {
    String name;
    float price;
    int quantity;
    String added_date;
    List<AssignedValueDTO> assignedValues;
}

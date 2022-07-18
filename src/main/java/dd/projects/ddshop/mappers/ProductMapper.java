package dd.projects.ddshop.mappers;

import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.dtos.seeProductDTO;
import dd.projects.ddshop.models.Category;
import dd.projects.ddshop.models.Product;
import dd.projects.ddshop.models.ProductAttribute;
import dd.projects.ddshop.models.Subcategory;
import dd.projects.ddshop.repositories.SubcategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ProductMapper {


    public Product toProduct(ProductDTO productDTO, Subcategory subcategory){
        return new Product(productDTO.getName(), productDTO.getDescription(), subcategory);
    }

    public seeProductDTO toSeeProductDTO(Product product){
        return new seeProductDTO(product.getId(),product.getName(), product.getDescription(), CategoryMapper.toSubcategoryDTO(product.getSubcategory()));
    }
}

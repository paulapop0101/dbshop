package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.dtos.seeProductDTO;
import dd.projects.ddshop.mappers.ProductMapper;
import dd.projects.ddshop.models.Product;
import dd.projects.ddshop.repositories.ProductRepository;
import dd.projects.ddshop.repositories.SubcategoryRepository;
import dd.projects.ddshop.validations.ProductValidation;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final ProductMapper productMapper = new ProductMapper();

    private final ProductValidation productValidation = new ProductValidation();

    public ProductService(ProductRepository productRepository, SubcategoryRepository subcategoryRepository){
        this.productRepository=productRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    public void addProduct(ProductDTO productDTO){
        productValidation.productValidation(productDTO);
        Product product = productMapper.toProduct(productDTO,subcategoryRepository.getReferenceById(productDTO.getSubcategory().getId()));
        productRepository.save(product);
    }
    public List<seeProductDTO> getProducts(){
        return productRepository.findAll()
                .stream()
                .map(productMapper::toSeeProductDTO)
                .collect(toList());
    }

    public void deleteProduct(final int id){
        productRepository.deleteById(id);
    }
}

package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.ProductDTO;
import dd.projects.ddshop.dtos.seeProductDTO;
import dd.projects.ddshop.mappers.ProductMapper;
import dd.projects.ddshop.models.Product;
import dd.projects.ddshop.repositories.ProductRepository;
import dd.projects.ddshop.repositories.SubcategoryRepository;
import dd.projects.ddshop.validations.ProductValidation;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    private final ProductValidation productValidation;

    @Autowired
    public ProductService(final ProductRepository productRepository, final SubcategoryRepository subcategoryRepository){
        this.productRepository=productRepository;
        productValidation = new ProductValidation(productRepository);
    }

    public ProductDTO addProduct(final ProductDTO productDTO){
        productValidation.productValidation(productDTO);
        final Product product = productMapper.toModel(productDTO);
        product.setVariants(new ArrayList<>());
        productRepository.save(product);
        return productDTO;
    }
    public List<seeProductDTO> getProducts(){
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(toList());
    }

    public boolean deleteProduct(final int id){
        productRepository.deleteById(id);
        return true;
    }
}

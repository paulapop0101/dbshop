package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.mappers.CategoryMapper;
import dd.projects.ddshop.models.Category;
import dd.projects.ddshop.models.Subcategory;
import dd.projects.ddshop.repositories.CategoryRepository;
import dd.projects.ddshop.repositories.SubcategoryRepository;
import dd.projects.ddshop.validations.CategoryValidation;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final SubcategoryRepository subcategoryRepository;

    private final CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
    private final CategoryValidation categoryValidation;

    public CategoryService(final CategoryRepository categoryRepository, final SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
        this.categoryValidation = new CategoryValidation(categoryRepository, subcategoryRepository);
    }

    public void addCategory(final String name){
        categoryValidation.categoryValidation(name);
        final Category category = new Category(name);
        categoryRepository.save(category);

    }

    public void addSubcategory(final String name, final int id){
        categoryValidation.subcategoryValidation(name);
        final Category category = categoryRepository.getReferenceById(id);
        final Subcategory subcategory = new Subcategory(name,category);
        subcategoryRepository.save(subcategory);
    }

    public boolean deleteCategory(final int id){
        categoryRepository.deleteById(id);
        return true;
    }
    public List<CategoryDTO> getCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .collect(toList());
    }

    public boolean deleteSubcategory(final int id){
        subcategoryRepository.deleteById(id);
        return true;
    }

}

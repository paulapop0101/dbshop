package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.mappers.CategoryMapper;
import dd.projects.ddshop.models.Category;
import dd.projects.ddshop.models.Subcategory;
import dd.projects.ddshop.repositories.CategoryRepository;
import dd.projects.ddshop.repositories.SubcategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final SubcategoryRepository subcategoryRepository;

    private final CategoryMapper categoryMapper = new CategoryMapper();

    public CategoryService(CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
    }
    public void addCategory(String name){

        Category category = new Category(name);

        categoryRepository.save(category);

    }

    public void addSubcategory(String name, int id){
        Category category = categoryRepository.getReferenceById(id);
        Subcategory subcategory = new Subcategory(name,category);
        subcategoryRepository.save(subcategory);
    }
    public void deleteCategory(int id){
        categoryRepository.deleteById(id);
    }
    public List<CategoryDTO> getCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDTO)
                .collect(toList());
    }
}

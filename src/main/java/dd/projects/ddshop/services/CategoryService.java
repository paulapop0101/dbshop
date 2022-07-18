package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.exceptions.EntityAlreadyExists;
import dd.projects.ddshop.exceptions.IncorrectInput;
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
        checkEmpty(name);
        checkExists(name);
        Category category = new Category(name);

        categoryRepository.save(category);

    }

    private void checkEmpty(String name) {
        if(name.isEmpty())
            throw new IncorrectInput("Field should not be empty");
    }

    private void checkExists(String name) {
        for(Category c : categoryRepository.findAll())
            if(c.getName().equals(name))
                throw new EntityAlreadyExists("Category with this name already exists");
    }

    public void addSubcategory(final String name, final int id){
        checkEmpty(name);
        Category category = categoryRepository.getReferenceById(id);
        checkSubcategoryExists(name,category);
        Subcategory subcategory = new Subcategory(name,category);
        subcategoryRepository.save(subcategory);
    }

    private void checkSubcategoryExists(String name, Category category) {
        for(Subcategory subcategory:category.getSubcategories())
            if(subcategory.getName().equals(name))
                throw new EntityAlreadyExists("Subcategory with this name already exists");
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

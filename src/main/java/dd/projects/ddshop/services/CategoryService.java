package dd.projects.ddshop.services;

import dd.projects.ddshop.dtos.CategoryDTO;
import dd.projects.ddshop.mappers.CategoryMapper;
import dd.projects.ddshop.models.Category;
import dd.projects.ddshop.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper = new CategoryMapper();

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public void addCategory(CategoryDTO category){
        Category category1 = categoryMapper.toCategory(category);
        categoryRepository.save(category1);
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

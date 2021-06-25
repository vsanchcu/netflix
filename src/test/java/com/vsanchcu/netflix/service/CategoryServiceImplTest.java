package com.vsanchcu.netflix.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.vsanchcu.netflix.entity.Category;
import com.vsanchcu.netflix.entity.TvShow;
import com.vsanchcu.netflix.exception.NetflixException;
import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.model.CategoryRestModel;
import com.vsanchcu.netflix.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CategoryServiceImplTest {
	
	@InjectMocks
	private CategoryServiceImpl categoryService;

	@Mock
	private CategoryRepository categoryRepository;

	@Test
	void testGetCategories() {
		Mockito.when(categoryRepository.findAll())
				.thenReturn(Lists.list(new Category(1L, "Categoría 1", new HashSet<>(Sets.newSet(new TvShow(1L)))), 
							new Category(2L, "Categoría 2", new HashSet<>(Sets.newSet(new TvShow(1L))))));
		
		List<CategoryRestModel> categories = categoryService.getCategories();
		
		assertEquals(1L, categories.get(0).getId());
		assertEquals("Categoría 2", categories.get(1).getName());
	}

	@Test
	void testGetCategoryById() throws NetflixException {
		Mockito.when(categoryRepository.findById(1L))
				.thenReturn(Optional.ofNullable(
						new Category(1L, "Categoría 1", new HashSet<>(Sets.newSet(new TvShow(1L))))));
		
		CategoryRestModel category = categoryService.getCategoryById(1L);
		
		assertEquals(1L, category.getId());
	}
	
	@Test
	void testGetCategoryByIdNotFound() throws NetflixNotFoundException {
//		Mockito.when(categoryRepository.getById(1L)).thenThrow(new NetflixNotFoundException(ConstException.MSG_NON_EXIST_CATEGORY));
		
//		categoryService.getCategoryById(1L);
		
//		assertThrows(NetflixNotFoundException.class, () -> categoryService.getCategoryById(1L));
		
//		Exception exception = assertThrows(NetflixNotFoundException.class, null);
//		assertTrue(exception.getMessage().contains(ConstException.MSG_NON_EXIST_CATEGORY));
	}

}

package com.vsanchcu.netflix.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.vsanchcu.netflix.exception.NetflixNotFoundException;
import com.vsanchcu.netflix.model.CategoryRestModel;
import com.vsanchcu.netflix.service.CategoryServiceI;
import com.vsanchcu.netflix.util.ConstException;
import com.vsanchcu.netflix.util.ConstRest;
import com.vsanchcu.netflix.util.ConstTest;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class CategoryControllerTest {

	@MockBean
	private CategoryServiceI categoryService;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetCategories() throws Exception {
		Mockito.when(categoryService.getCategories())
				.thenReturn(Lists.list(new CategoryRestModel(1L, "Categoría 1"), 
						new CategoryRestModel(2L, "Categoría 2")));
		
		final RequestBuilder request = MockMvcRequestBuilders
				.get(ConstRest.RES_CATEGORY);
		
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json(
						"{"
						+ ConstTest.REQ_SUCCESS
						+ "\"data\": ["
							+ "{"
							+ "\"id\": 1,"
							+ "\"name\": \"Categoría 1\""
							+ "},"
							+ "{"
							+ "\"id\": 2,"
							+ "\"name\": \"Categoría 2\""
							+ "}"
						+ "]}"))
				.andReturn();
	}

	@Test
	void testGetCategoryById() throws Exception {
		Mockito.when(categoryService.getCategoryById(1L))
				.thenReturn(new CategoryRestModel(1L, "Categoría 1"));
		
		final RequestBuilder request = MockMvcRequestBuilders
				.get(ConstRest.RES_CATEGORY + "/1");
		
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json(
						"{"
						+ ConstTest.REQ_SUCCESS
						+ "\"data\":"
							+ "{"
							+ "\"id\": 1,"
							+ "\"name\": \"Categoría 1\""
							+ "}"
						+ "}"))
				.andReturn();
	}

	@Test
	void testGetCategoryByIdNotFound() throws Exception {
		Mockito.when(categoryService.getCategoryById(1L))
				.thenThrow(new NetflixNotFoundException(ConstException.MSG_NON_EXIST_CATEGORY));
		
		final RequestBuilder request = MockMvcRequestBuilders
				.get(ConstRest.RES_CATEGORY + "/1");
		
		mockMvc.perform(request)
				.andExpect(status().isNotFound())
				.andExpect(content().json(
						"{"
						+ ConstTest.REQ_NOT_FOUND_CATEGORY
						+ "\"data\": null"
						+ "}"))
				.andReturn();
	}

}

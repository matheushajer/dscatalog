package br.com.matheushajer.dscatalog.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheushajer.dscatalog.entities.Category;
import br.com.matheushajer.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public Collection<Category> findall(){
		return repository.findAll();
		
	}
	
}

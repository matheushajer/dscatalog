package br.com.matheushajer.dscatalog.services;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.matheushajer.dscatalog.dto.CategoryDTO;
import br.com.matheushajer.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public Collection<CategoryDTO> findall() {
		return repository.findAll().stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
	}

}

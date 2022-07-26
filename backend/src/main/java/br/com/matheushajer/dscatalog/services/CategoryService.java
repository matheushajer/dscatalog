package br.com.matheushajer.dscatalog.services;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.matheushajer.dscatalog.dto.CategoryDTO;
import br.com.matheushajer.dscatalog.entities.Category;
import br.com.matheushajer.dscatalog.repositories.CategoryRepository;
import br.com.matheushajer.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public Collection<CategoryDTO> findall() {
		return repository.findAll().stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> optional = repository.findById(id);
		return new CategoryDTO(optional.orElseThrow(() -> new EntityNotFoundException("ID não encontrado")));
	}

}

package br.com.matheushajer.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.matheushajer.dscatalog.dto.CategoryDTO;
import br.com.matheushajer.dscatalog.entities.Category;
import br.com.matheushajer.dscatalog.repositories.CategoryRepository;
import br.com.matheushajer.dscatalog.services.exceptions.DataBaseException;
import br.com.matheushajer.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {
		return repository.findAll(pageRequest).map(x -> new CategoryDTO(x));
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> optional = repository.findById(id);
		return new CategoryDTO(optional.orElseThrow(() -> new ResourceNotFoundException("ID não encontrado")));
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		return new CategoryDTO(repository.save(entity));
	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
			Category entity = repository.getOne(id);
			entity.setName(dto.getName());
			return new CategoryDTO(repository.save(entity));
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("ID não encontrado: " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("ID não encontrado: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Erro de violação de integridade");
		}

	}

}

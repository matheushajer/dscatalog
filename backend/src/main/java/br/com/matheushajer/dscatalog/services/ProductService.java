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
import br.com.matheushajer.dscatalog.dto.ProductDTO;
import br.com.matheushajer.dscatalog.entities.Product;
import br.com.matheushajer.dscatalog.repositories.CategoryRepository;
import br.com.matheushajer.dscatalog.repositories.ProductRepository;
import br.com.matheushajer.dscatalog.services.exceptions.DataBaseException;
import br.com.matheushajer.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		return repository.findAll(pageRequest).map(x -> new ProductDTO(x));
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> optional = repository.findById(id);
		return new ProductDTO(optional.orElseThrow(() -> new ResourceNotFoundException("ID não encontrado")), optional.get().getCategories());
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		return new ProductDTO(repository.save(entity));
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			return new ProductDTO(repository.save(entity));
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
	
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setDate(dto.getDate());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPrice(dto.getPrice());
		
		entity.getCategories().clear();
		
		for(CategoryDTO cat : dto.getCategories()) {
			entity.getCategories().add(categoryRepository.getOne(cat.getId()));
		}
		
	}

	
}

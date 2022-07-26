package br.com.matheushajer.dscatalog.resources;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheushajer.dscatalog.entities.Category;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@GetMapping
	public ResponseEntity<Collection<Category>> findAll(){
		Collection<Category> list = new ArrayList<>();
		list.add(new Category(1L, "Livros"));
		list.add(new Category(2L, "Eletrônicos"));
		
		return ResponseEntity.ok().body(list);
	}
	
}

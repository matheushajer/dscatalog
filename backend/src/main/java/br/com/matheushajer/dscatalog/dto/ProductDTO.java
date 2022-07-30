package br.com.matheushajer.dscatalog.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import br.com.matheushajer.dscatalog.entities.Category;
import br.com.matheushajer.dscatalog.entities.Product;

public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	private Instant date;

	private Collection<CategoryDTO> categories = new ArrayList<>();

	public ProductDTO() {
	}

	public ProductDTO(Integer id, String name, String description, Double price, String imgUrl, Instant date) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
		this.date = date;
	}

	public ProductDTO(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.imgUrl = entity.getImgUrl();
		this.date = entity.getDate();
	}

	public ProductDTO(Product entity, Set<Category> categories) {
		this(entity);
		categories.forEach(category -> this.categories.add(new CategoryDTO(category)));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public Collection<CategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(Collection<CategoryDTO> categories) {
		this.categories = categories;
	}

}

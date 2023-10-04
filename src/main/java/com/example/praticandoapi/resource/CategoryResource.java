package com.example.praticandoapi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.praticandoapi.model.Category;
import com.example.praticandoapi.repository.CategoryRepository;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/categorias")
public class CategoryResource {
	
	@Autowired
	private CategoryRepository categoryRepository; 
	
	@GetMapping
	public ResponseEntity<?> listar() { 
		List<Category> categorias = categoryRepository.findAll();
		return !categorias.isEmpty() ? ResponseEntity.ok(categorias) : ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<Category> criar(@Validated @RequestBody Category category, HttpServletResponse response ) {
		Category categorySalva = categoryRepository.save(category);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
			.buildAndExpand(categorySalva.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(categorySalva);
	}
	
	@GetMapping("/{id}")
		public ResponseEntity<Optional<Category>> buscarPeloId(@PathVariable Long id) {
			Optional<Category> category = categoryRepository.findById(id);
			return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
	}
	
}
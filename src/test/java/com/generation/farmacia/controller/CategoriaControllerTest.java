package com.generation.farmacia.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.farmacia.model.Categoria;
import com.generation.farmacia.repository.CategoriaRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoriaControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private CategoriaController categoriaController;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@BeforeEach
	void start() {
		
		categoriaController.post(new Categoria(0L, "Ortopedia", "Produtos ortopédicos e de suporte físico"));
		
	}

	@Test
	@DisplayName("Cadastrar uma categoria")
	public void deveCadastrarUmaCategoria() {

		HttpEntity<Categoria> corpoRequisicao = new HttpEntity<Categoria>(
				new Categoria(1L, "Alimentos", "Produtos alimentícios e bebidas"));

		ResponseEntity<Categoria> corpoResposta = testRestTemplate.exchange("/categorias", HttpMethod.POST,
				corpoRequisicao, Categoria.class);

		assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
	}
	
	@Test
	@DisplayName("Mostrar categoria por nome")
	public void deveMostrarCategoriaPorNome() {
		
		categoriaRepository.save(new Categoria(0L, "Alimentos", "Produtos alimentícios e bebidas"));
		
		ResponseEntity<Categoria[]> corpoResposta = testRestTemplate
				.exchange("/categorias/nome/alimentos", HttpMethod.GET, null, Categoria[].class);
		
		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
	}
	
	@Test
	@DisplayName("Listar todas as categorias")
	public void deveMostrarTodasCategorias() {

		categoriaController.post(
				new Categoria(0L, "Dermocosméticos", "Produtos dermatológicos para cuidados com a pele"));

		categoriaController.post(
				new Categoria(0L, "Fitoterápicos", "Produtos naturais e à base de plantas para tratamentos de saúde"));

		ResponseEntity<String> resposta = testRestTemplate.exchange("/categorias", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	@DisplayName("Deletar uma categoria")
	public void deveDeletarUmaCategoria() {

		Categoria categoria = categoriaRepository.save(new Categoria(0L, "Fitness", "Equipamentos e acessórios para atividades físicas e exercícios"));
		
		ResponseEntity<Categoria> resposta = testRestTemplate.exchange("/categorias/" + categoria.getId(), HttpMethod.DELETE, null, Categoria.class);

		assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
	}

}

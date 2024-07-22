package com.generation.farmacia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome do produto é obrigatório!")
	@Size(min = 4, max = 100, message = "O nome da categoria deve conter no mínimo 4 e no máximo 100 caracteres!")
	private String nome;

	@NotBlank(message = "O campo de descrição é obrigatório")
	@Size(min = 10, max = 500, message = "A descrição deve conter no mínimo 10 caracteres e no máximo 500 caracteres")
	private String descricao;

	@NotNull(message = "O preço do produto é obrigatório!")
	private float preco;

	@NotNull(message = "Informar a quantidade dos produtos em estoque é obrigatório!")
	private int estoque;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}

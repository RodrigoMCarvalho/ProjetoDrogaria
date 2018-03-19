package br.com.drogaria.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_produtos")
public class Produto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pro_codigo")
	private Long codigo;
	
	@Column(name="pro_descricao",length=50, nullable=false)
	private String descricao;
	
	@Column(name="pro_preco",scale = 2,precision=7, nullable=false ) //decimal(7,2)
	private BigDecimal preco;
	
	@Column(name="pro_quantidade",nullable=false)
	private Integer quantidade;
	
	//muitos produtos para um fabricante
	@ManyToOne(fetch=FetchType.EAGER) //EAGER - quando carregar os produtos, carrega também o fabricante
	@JoinColumn(name="tbl_fabricante_fab_codigo", referencedColumnName="fab_codigo")
	private Fabricante fabricante; //chave estrangeira

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	
}

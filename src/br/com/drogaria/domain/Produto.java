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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="tbl_produtos")
@NamedQueries({
	@NamedQuery(name="Produto.listar",query="SELECT produto FROM Produto produto"),
	@NamedQuery(name="Produto.buscarPorCod",query="SELECT produto FROM Produto produto WHERE codigo=:codigo")
})
public class Produto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pro_codigo")
	private Long codigo;
	
	@NotEmpty(message="O campo Descri��o do PRODUTO � obrigat�rio")
	@Column(name="pro_descricao",length=50, nullable=false)
	private String descricao;
	
	@NotNull(message="O campo Pre�o � obrigat�rio")
	@DecimalMin(value="0.00", message="Informe um valor maior que 0.")
	@DecimalMax(value="99999.99",message="Informe um valor menor que 100.00.")
	@Column(name="pro_preco",scale = 2,precision=7, nullable=false ) //decimal(7,2)
	private BigDecimal preco;
	
	@NotNull(message="O campo Quantidade � obrigat�rio.")
	@Min(value=0 , message="Informe um valor maior que 0 para a quantidade.")  // para Inteiros
	@Max(value=9999 , message="Informe um valor menor que 10.000 para a quantidade.")
	@Column(name="pro_quantidade",nullable=false)
	private Integer quantidade;
	
	@NotNull(message="O campo Fabricante � obrigat�rio.") //nNtnull - para objetos, NotEmpty para String
	@ManyToOne(fetch=FetchType.EAGER) //muitos produtos para um fabricante //EAGER - quando carregar os produtos, carrega tamb�m o fabricante
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

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao + ", preco=" + preco + ", quantidade="
				+ quantidade + ", fabricante=" + fabricante + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}

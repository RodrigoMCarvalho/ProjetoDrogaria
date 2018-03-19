package br.com.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_fabricantes")
public class Fabricante {
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.AUTO) // auto incremento
	@Column(name = "fab_codigo")
	private Long codigo;

	@Column(name = "fab_descricao", length = 50, nullable = false)
	private String string;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

}

package nosi.webapps.igrp.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import nosi.base.ActiveRecord.BaseActiveRecord;

/**
 * Isaias.Nunes
 * Oct 11, 2017
 */
@Entity
@Table(name="tbl_ImportExport")
public class ImportExportDAO extends BaseActiveRecord<ImportExportDAO> implements Serializable{

	private static final long serialVersionUID = 9030716918029909217L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Application aplicacao;
	private User usuario;
	private String data;
	private String tipo;
	private String dowload;
	
	public ImportExportDAO() {
		
	}
	
	public ImportExportDAO(Application aplicacao, User usuario, String data, String tipo, String dowload) {
		super();
		this.aplicacao = aplicacao;
		this.usuario = usuario;
		this.data = data;
		this.tipo = tipo;
		this.dowload = dowload;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Application getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(Application aplicacao) {
		this.aplicacao = aplicacao;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDowload() {
		return dowload;
	}

	public void setDowload(String dowload) {
		this.dowload = dowload;
	}
	
	
}

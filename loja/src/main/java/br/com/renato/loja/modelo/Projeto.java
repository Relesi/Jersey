package br.com.renato.loja.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

public class Projeto {
	private List<Projeto> projetos = new ArrayList<Projeto>();
	private long id;
	private String nome;
	private int anoDeInicio;

	public Projeto(long id, String nome, int anoDeInicio) {
		super();
		this.id = id;
		this.nome = nome;
		this.anoDeInicio = anoDeInicio;
	}

	public void remove(long id) {
		for (Iterator iterator = projetos.iterator(); iterator.hasNext();) {
			Projeto projeto = (Projeto) iterator.next();
			if (projeto.getId() == id) {
				iterator.remove();
			}

		}
	}

	public Projeto() {
		super();
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getAnoDeInicio() {
		return anoDeInicio;
	}

	public String toXML() {
		return new XStream().toXML(this);
	}

	public String toJson() {
		return new Gson().toJson(this);
	}
}
package br.com.renato.ws;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.renato.ws.Servidor;
import br.com.renato.ws.modelo.Projeto;

public class ProjetoTest {
	private HttpServer server;
	private Client client;

	@Before
	public void startaServidor() {
		this.server = Servidor.inicializaServidor();
		client = ClientBuilder.newClient();
	}

	@After
	public void mataServidor() {
		server.stop();
	}

	@Test
	public void testaQueAConexaoComOServidorFuncionaNoPathDeProjetos() {
		WebTarget target = client.target("http://localhost:8080");
		Projeto projeto = target.path("/projetos/1").request().get(Projeto.class);
		Assert.assertEquals(1L, projeto.getId(), 0);
	}

	@Test
	public void testaQueBuscarUmProjetoTrasUmProjeto() {
		WebTarget target = client.target("http://localhost:8080");
		Projeto projeto = target.path("/projetos/1").request().get(Projeto.class);
		Assert.assertEquals("Minha loja", projeto.getNome());
	}
}
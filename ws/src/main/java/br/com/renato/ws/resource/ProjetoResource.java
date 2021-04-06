package br.com.renato.ws.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.renato.ws.dao.ProjetoDAO;
import br.com.renato.ws.modelo.Projeto;

@Path("projetos")
public class ProjetoResource {

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca(@PathParam("id") long id) {
		Projeto projeto = new ProjetoDAO().busca(id);
		return projeto.toXML();
	}

//	@Path("{id}")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public String busca(@PathParam("id") long id) {
//		Projeto projeto = new ProjetoDAO().busca(id);
//		return projeto.toJson();
//	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo) {
		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		new ProjetoDAO().adiciona(projeto);
		URI uri = URI.create("/projetos/" + projeto.getId());
		return Response.created(uri).build();
	}
	
	
	
	@Path("{id}/projetos/{projetoId}")
	@DELETE
	public Response removeProjeto(@PathParam("id") long id,
			@PathParam("projetoId") long projetosId){
		Projeto projeto = new ProjetoDAO().busca(id);
		projeto.remove(projetosId);
		return Response.ok().build();
	}
	
	
	
	

}
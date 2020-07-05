package br.edu.ifms.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import br.edu.ifms.entity.Paciente;
import br.edu.ifms.entity.Pacientes;

public class ServiceClient {

	private Client client;
	
	
	private WebTarget webTarget;
	
	private final String URL_SERIVCE = "http://localhost:8080/WebServiceRest/rest/service/";
	
	public ServiceClient() {
		this.client = ClientBuilder.newClient();
	}
	
	public String CadastrarPaciente(Paciente paciente) {
		
		this.webTarget = this.client.target(URL_SERIVCE).path("cadastrar");
		Invocation.Builder invocationBuilder = this.webTarget.request("application/json;charset=UTF-8");
		Response response = invocationBuilder.post(Entity.entity(paciente,"application/json;charset=UTF-8"));
	
		return response.readEntity(String.class);
	}
	
	
	public String AlterarPaciente(Paciente paciente) {
		
		this.webTarget = this.client.target(URL_SERIVCE).path("alterar");
		Invocation.Builder invocationBuilder = this.webTarget.request("application/json;charset=UTF-8");
		Response response = invocationBuilder.put(Entity.entity(paciente,"application/json;charset=UTF-8"));
	
		return response.readEntity(String.class);
	}
	
	public Pacientes ConsultaTodosPacientes() {
		
		this.webTarget = this.client.target(URL_SERIVCE).path("todosPacientes");
		Invocation.Builder invocationBuilder = this.webTarget.request("application/json;charset=UTF-8");
		Response response = invocationBuilder.get();
	
		return response.readEntity(Pacientes.class);
	}
	
	public Paciente ConsultarPacientePorCodigo(int codigo) {
		
		this.webTarget = this.client.target(URL_SERIVCE).path("getPaciente").path(String.valueOf(codigo));
		Invocation.Builder invocationBuilder = this.webTarget.request("application/json;charset=UTF-8");
		Response response = invocationBuilder.get();
	
		return response.readEntity(Paciente.class);
	}
	
	public String ExcluirPacientePorCodigo(int codigo) {
		this.webTarget = this.client.target(URL_SERIVCE).path("excluir").path(String.valueOf(codigo));
		Invocation.Builder invocationBuilder = this.webTarget.request("application/json;charset=UTF-8");
		Response response = invocationBuilder.delete();
	
		return response.readEntity(String.class);
	}
	
	
	
	
}

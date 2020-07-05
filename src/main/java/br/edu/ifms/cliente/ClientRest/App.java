package br.edu.ifms.cliente.ClientRest;

import javax.swing.JOptionPane;

import br.edu.ifms.entity.Paciente;
import br.edu.ifms.entity.Pacientes;
import br.edu.ifms.service.ServiceClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String opcao = JOptionPane.showInputDialog(null," Escolha uma opção: \n "+" 1-CADASTRAR | 2-CONSULTAR | 3-EXCLUIR | 4-ALTERAR | 5-LISTAR TODOS","OPÇÕES",JOptionPane.PLAIN_MESSAGE);
        
        if(opcao == null)
        	System.exit(0);
        
        switch(Integer.parseInt(opcao)) {
        case 1:
        	Cadastrar();
        	break;
        case 2:
        	Consultar();
        	break;
        case 3:
        	Excluir();
        	break;
        case 4:
        	Alterar();
        	break;
        case 5:
        	ListarTodos();
        	break;
        default:
        	JOptionPane.showMessageDialog(null, "Opção invalida! ");
        	main(null);
        	break;
        }
        
    }

	private static void Consultar() {
		//DECLARANDO O OBT DA NOSSA CLASSE QUE ACESSA O SERVIÕ REST
				ServiceClient client = new ServiceClient();
				
				String codigo = JOptionPane.showInputDialog(null,"Informe o código para connsultar ","Consultar",JOptionPane.PLAIN_MESSAGE);
				
				
				//SETA VALORES NO NOSSO JAVABENS
				Paciente paciente = client.ConsultarPacientePorCodigo(Integer.parseInt(codigo));

				if(paciente == null) {
					JOptionPane.showMessageDialog(null, "Paciente não encontrado");
					main(null);
				}else{
					String resultado = null;
					
					resultado = "Código: "+String.valueOf(paciente.getCodigo())+"\n";
					resultado += " Nome: "+paciente.getNome()+"\n";
					resultado += " Sexo: "+paciente.getSexo()+"\n";
					resultado += " Patologias: "+paciente.getPatologias()+"\n";
					JOptionPane.showMessageDialog(null, resultado);

					main(null);
				}
				
	}

	private static void Cadastrar() {
		//DECLARANDO O OBT DA NOSSA CLASSE QUE ACESSA O SERVIÕ REST
		ServiceClient client = new ServiceClient();
		
		String nome = JOptionPane.showInputDialog(null,"Novo Cadastro",JOptionPane.PLAIN_MESSAGE);
		String sexo = JOptionPane.showInputDialog(null,"Sexo (M ou F)","Novo cadastro",JOptionPane.PLAIN_MESSAGE);
		String patologias = JOptionPane.showInputDialog(null,"Quais patologias ele possui","Novo cadastro",JOptionPane.PLAIN_MESSAGE);
		//SETA VALORES NO NOSSO JAVABENS
		Paciente paciente = new Paciente();
		paciente.setNome(nome);
		paciente.setSexo(sexo);
		paciente.setPatologias(patologias);
		
		
		//cadastra um novo paciente
		String resultado = client.CadastrarPaciente(paciente);
		
		JOptionPane.showMessageDialog(null,resultado);
		
		main(null);
	}

	private static void Excluir() {
		ServiceClient client = new ServiceClient();
		
		String codigo = JOptionPane.showInputDialog(null,"Informe o código para excluir: ","Excluir",JOptionPane.PLAIN_MESSAGE);
		
		String resultado = client.ExcluirPacientePorCodigo(Integer.parseInt(codigo));
		
		JOptionPane.showMessageDialog(null,resultado);
		
		main(null);
	}

	private static void Alterar() {
		ServiceClient client = new ServiceClient();
		
		String codigo = (String) JOptionPane.showInputDialog(null,"Informe o código para alteração: ","Alterar",JOptionPane.PLAIN_MESSAGE);
		
		Paciente paciente = client.ConsultarPacientePorCodigo(Integer.parseInt(codigo));
		
		if(paciente==null) {
			JOptionPane.showMessageDialog(null, "Paciente não encontrado !");
			main(null);
		}else {
			String nome = (String) JOptionPane.showInputDialog(null," NOME: ","Alterar registro - codigo: "+paciente.getCodigo(),JOptionPane.PLAIN_MESSAGE,null,null,
					paciente.getSexo());	
			String sexo = (String) JOptionPane.showInputDialog(null," SEXO: ","Alterar registro - codigo: "+paciente.getCodigo(),JOptionPane.PLAIN_MESSAGE,null,null,
					paciente.getSexo());	
			String patologias = (String) JOptionPane.showInputDialog(null," Patologias: ","Alterar registro - codigo: "+paciente.getCodigo(),JOptionPane.PLAIN_MESSAGE,null,null,
					paciente.getPatologias());	
			paciente.setNome(nome);
			paciente.setPatologias(patologias);
			paciente.setSexo(sexo);
			String resultado = client.AlterarPaciente(paciente);
			
			JOptionPane.showMessageDialog(null, resultado);
			
			main(null);
		}
	}
		
	

	private static void ListarTodos() {
		ServiceClient client = new ServiceClient();
		
		Pacientes pacientes = client.ConsultaTodosPacientes();
		
		StringBuilder stringBuilderDetalhesPaciente = new StringBuilder();
		
		for(Paciente paciente: pacientes) {
			stringBuilderDetalhesPaciente.append("Código:");
			stringBuilderDetalhesPaciente.append(paciente.getCodigo());
			stringBuilderDetalhesPaciente.append("Nome:");
			stringBuilderDetalhesPaciente.append(paciente.getNome());
			stringBuilderDetalhesPaciente.append("Sexo:");
			stringBuilderDetalhesPaciente.append(paciente.getSexo());
			stringBuilderDetalhesPaciente.append("Patologias:");
			stringBuilderDetalhesPaciente.append(paciente.getPatologias());
			stringBuilderDetalhesPaciente.append("\n:");
		}
		
		//lista pessoas encontradas
		JOptionPane.showMessageDialog(null, stringBuilderDetalhesPaciente.toString());
		
		main(null);
		
	}
}

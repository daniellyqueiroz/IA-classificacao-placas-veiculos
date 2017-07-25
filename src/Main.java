import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

	private static final String diretorio = "/Users/air/Documents/workspace/IA-classificacao-placas-veiculos/imagem";
	
	public static String lerArquivo(){
		File entrada = new File(diretorio);
		File listaImagem[] = entrada.listFiles();
		int i = 0;
		
		String temporaria = "";
		
		for (int j = listaImagem.length; i < j; i++) {
			File arquivos = listaImagem[i];
			//System.out.println(arquivos.getName());
			 temporaria+= arquivos.getName() +"\n";
			
		}
		return temporaria;
	}
	
	public static void escreveArquivo() throws IOException{
		File arquivo = new File("arquivo.txt");
		FileWriter salva = new FileWriter(arquivo);
        PrintWriter escreve = new PrintWriter(salva);
        escreve.println(lerArquivo());
        escreve.close();
        salva.close();
	}
	public static void main(String[] args) throws IOException {
		
		lerArquivo();
		escreveArquivo();


	}
	
	

}

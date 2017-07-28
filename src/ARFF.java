import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;





public class ARFF {
	
	private static String diretorio = "/Users/air/Documents/workspace/IA-classificacao-placas-veiculos/hist.arff";
	
	/*public static String gravaARFF() throws IOException{
		//ArrayList<String> teste = new ArrayList<String>();
		String teste = "";
		ArrayList<Imagem> listaImagem= new ArrayList<Imagem>();
		HashMap <String, Integer> aux= new HashMap <String, Integer>();
		String arff = "% Classificacao de Placas Veiculares\n" +
					  "% Inteligencia Artificial -2017.01\n" +
					  "% Danielly Queiroz\n"+
					  "% Tamires Pereira\n" +
					  "% Relabeled values in attribute 'class'\n"+
					  "% From: 0						To: placa_nao\n"+
					  "% From: 1						To: placa_sim\n"+
					  "%\n"+
					  "@ relation classificacaoDePlacasVeiculares\n";
					  for (int i = 0; i <= 255; i++) {
						  arff += "@ attribute 'cor-"+i+"' numeric\n";
					  }
		
					 arff+= "@ attribute 'class' {placa_nao, placa_sim}\n"+
					  "@ data\n";
					 
					 listaImagem = gerarImagem(diretorio, lerArquivo(diretorio));
					 
					 for (int i = 0; i < listaImagem.size(); i++) {
						teste += ( gravaHistograma(getHistograma(listaImagem.get(i))))+ "\n"; 	
					}
					 System.out.println(teste);
					 System.out.println(gravaHistograma(aux));
					 
					  arff+= teste;
		return arff;
	}*/
	
	
	public static String gravaARFF() throws IOException{
		//ArrayList<String> teste = new ArrayList<String>();
		String teste = "";
		ArrayList<String> oi = new ArrayList<String>();
		ArrayList<Imagem> listaImagem= new ArrayList<Imagem>();
		HashMap <String, Integer> aux= new HashMap <String, Integer>();
		String arff = "% Classificacao de Placas Veiculares\n" +
					  "% Inteligencia Artificial -2017.01\n" +
					  "% Danielly Queiroz\n"+
					  "% Tamires Pereira\n" +
					  "% Relabeled values in attribute 'class'\n"+
					  "% From: 0						To: placa_nao\n"+
					  "% From: 1						To: placa_sim\n"+
					  "%\n"+
					  "@ relation classificacaoDePlacasVeiculares\n";
					  for (int i = 0; i <= 255; i++) {
						  arff += "@ attribute 'cor-"+i+"' numeric\n";
					  }
		
					 arff+= "@ attribute 'class' {placa_nao, placa_sim}\n"+
					  "@ data\n";
					 
					 listaImagem = gerarImagem(diretorio, lerArquivo(diretorio));
					 
					 
					 
					 for(int i= 0; i < listaImagem.size(); i++){
						 oi.add(verificaPlaca(listaImagem.get(i).getNome()));
						 teste += (gravaHistograma(getHistograma(listaImagem.get(i)))+oi.get(i) + "\n");
					 }
						
						 
					
					// System.out.println(teste);
					 //System.out.println(gravaHistograma(aux));
					 
					  arff+= teste;
		return arff;
	}
	
	//Histograma
	public static HashMap<String, Integer>  getHistograma(Imagem img) throws IOException {
		System.out.println(img.imprime());
		BufferedImage imagem=ImageIO.read(new File(img.imprime()));  
		HashMap<String, Integer> hist = new HashMap<String, Integer>();
		int valor;
		String chave;
		int[] array = new int[3];
		int[] pixel;
		
		for (int y = 0; y < imagem.getHeight(); y++) {
		for (int x = 0; x < imagem.getWidth(); x++) {
		    pixel = imagem.getRaster().getPixel(x, y, array);
		    chave = pixel[0] +"-" + pixel[1] + "-" + pixel[2] ;
		    valor = hist.getOrDefault(chave, 0);
		    hist.put(chave, ++valor);
		    }
		}
		
	 return hist;
	}
	// Imprime histogramN
	public static String gravaHistograma(HashMap<String, Integer> hist){
	
		String file ="";
		
		for(String k : hist.keySet()){
			file+= k + ":" + hist.getOrDefault(k, 0) + ",";
		}
		return file + "\n";	
	}
	//Retorna Array de Arquivos
	public static ArrayList<String> lerArquivo(String diretorio){
		File entrada = new File(diretorio);
		File listaImagem[] = entrada.listFiles();
		int i = 0;
		
		ArrayList<String> temporaria = new ArrayList<String>();
		File arquivos;
		
		for (int j = listaImagem.length; i < j; i++) {
			arquivos = listaImagem[i];
			 temporaria.add(arquivos.getName());	
		}
		return temporaria;
	}
	
	//Verifica se eh ou nao placa, se for escreve 1, se nao for escreve 0
	public static String verificaPlaca(String nomeLista){
		String nome;
		String resultado="";
		
	
			nome = nomeLista.toString().substring(0, 1);
			 if(nome.equals("-")){
					resultado+="1";
					
				}else{
					resultado+="0";
				}
		
		
		return resultado;
	}
	/*public static ArrayList<String> verificaPlaca(ArrayList<String> listaImagens){
		String nome;
		ArrayList<String> resultado = new ArrayList<String>();
		
		for (String string : listaImagens) {
			nome = string.toString().substring(0, 1);
			 if(nome.equals("-")){
					resultado.add("1");
					
				}else{
					resultado.add("0");
				}
		}
		
		return resultado;
	}*/
	public static ArrayList<Imagem> gerarImagem(String diretorio, ArrayList<String> nome){
		ArrayList<Imagem> listaImg = new ArrayList<Imagem>();
		
		for (String nomeImagem : nome) {
			
			listaImg.add(new Imagem(diretorio,nomeImagem));
		}
		
		//for (int i = 0; i < listaImg.size(); i++) {
			//teste.add(listaImg.get(i).imprime());
		//}
		
		return listaImg;
	}
	
	
	
}
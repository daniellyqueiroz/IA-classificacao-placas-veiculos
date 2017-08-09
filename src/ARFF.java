import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;





public class ARFF {
	
	private static String diretorio = "/Users/air/Documents/workspace/IA-classificacao-placas-veiculos/imagem";
	
	
	
	
	public static String gravaARFF() throws IOException{
		
		String resultadoHistograma = "";
		ArrayList<String> classe = new ArrayList<String>();
		ArrayList<Imagem> listaImagem= new ArrayList<Imagem>();
		
		String arff = "% Classificacao de Placas Veiculares\n" +
					  "% Inteligencia Artificial -2017.01\n" +
					  "% Danielly Queiroz\n"+
					  "% Tamires Pereira\n" +
					  "% Relabeled values in attribute 'class'\n"+
					  "% From: placa_nao				To: 0\n"+
					  "% From: placa_sim				To: 1\n"+
					  "%\n"+
					  "@ relation classificacaoDePlacasVeiculares\n"+
					  "@ attribute 'class' {0, 1}\n";
					  for (int i = 0; i <= 255; i++) {
						  arff += "@ attribute 'R-"+i+"' numeric\n";
					  }
					  for (int i = 0; i <= 255; i++) {
						  arff += "@ attribute 'G-"+i+"' numeric\n";
					  }
					  for (int i = 0; i <= 255; i++) {
						  arff += "@ attribute 'B-"+i+"' numeric\n";
					  }
		
					 arff+="@ data\n";
					 
					 listaImagem = gerarImagem(diretorio, lerArquivo(diretorio));
					 
					 
					 
					 for(int i= 0; i < listaImagem.size(); i++){
						 classe.add(verificaPlaca(listaImagem.get(i).getNome()));
						 resultadoHistograma += classe.get(i)+(gravaHistograma(getHistograma(listaImagem.get(i))) + "\n");
					 }
						
						 
					 
					  arff+= resultadoHistograma;
		return arff;
	}
	
	//Histograma
	public static List<HashMap<Integer, Integer>>  getHistograma(Imagem img) throws IOException {
		System.out.println(img.imprime());
		BufferedImage imagem=ImageIO.read(new File(img.imprime()));
		List<HashMap<Integer, Integer>> ret = new ArrayList<>(3);
		HashMap<Integer, Integer> histR = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> histG = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> histB = new HashMap<Integer, Integer>();
		
		int valor;
		int chave;
		int[] array = new int[3];
		int[] canal;
		
		for(int i=0;i<256;i++){
			histR.put(i, 0);
			histG.put(i, 0);
			histB.put(i, 0);
		}
		
		for (int y = 0; y < imagem.getHeight(); y++) {
		for (int x = 0; x < imagem.getWidth(); x++) {
		    canal = imagem.getRaster().getPixel(x, y, array);
		    chave = canal[0];
		    valor = histR.get(chave);
		    histR.put(chave, ++valor);
		    
		    chave = canal[1];
		    valor = histR.get(chave);
		    histG.put(chave, ++valor);
		    
		    chave = canal[2];
		    valor = histR.get(chave);
		    histB.put(chave, ++valor);
		    }
		}
		ret.add(histR);
		ret.add(histG);
		ret.add(histB);
		return ret;
	}
	// Imprime histogramN
	public static String gravaHistograma(List<HashMap<Integer, Integer>> histRGB){
	
		String file ="";
		
		for (HashMap<Integer, Integer> hist : histRGB) {
			for(int k : hist.keySet()){
				file += "," + hist.getOrDefault(k, 0);
			}
			
		}
		
		return file ;	
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
			if(arquivos.isFile() && arquivos.getName().contains(".jpg")){
			 temporaria.add(arquivos.getName());
			}
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
		
		
		
		return listaImg;
	}
	
	
	
}
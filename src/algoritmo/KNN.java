package algoritmo;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class KNN{
	
	private static List<Imagem> LeArq(String arq) throws IOException {
		List<Imagem> imagens = new ArrayList<Imagem>();
		
		BufferedReader leitor = new BufferedReader(new FileReader(arq));		
		try {
			String linha = leitor.readLine(); 
						
			while((linha = leitor.readLine()) != null) {
				if(!(linha.startsWith("%"))){
					if(!(linha.startsWith("@"))){
						if(!linha.trim().equals("")){
							String[] tokens = linha.split(",");
							
							Imagem imagem = new Imagem();
							imagem.classe = Integer.parseInt(tokens[0]);
							//Classes OK
							//System.out.println(tokens[0]);
											
							imagem.dist = new double[tokens.length - 1];
							//Quantidade de atributos OK
							//System.out.println(tokens.length);
																			
							for(int i = 1; i < tokens.length; i++) {
								imagem.dist[i-1] = Double.parseDouble(tokens[i]);
								//System.out.println(tokens[i]);
							}
							
								imagens.add(imagem);
						}
						
						}
				
					}	
				
				}
			} finally {
			leitor.close();
		}
		
		return imagens;
	}
	
	
	public static double distEuclidiana(double x[], double y[]){
		double soma = 0;
		for(int i = 0; i < x.length; i++) {
			soma += (x[i] - y[i]) * (x[i] - y[i]);
		}
		return Math.sqrt(soma);
	}
	
	public static double distHamming(double[]x, double[]y){
		double soma = 0;
		for(int i = 0; i < x.length; i++) {
			soma += (x[i] - y[i]);
		}
		return Math.abs(soma);
	}
	
	public static double distCosseno(double x[],double y[]) { 
		double soma1 = 0;
		double soma2 = 0;
	 
	    for (int i = 0; i < x.length; i++) {
	    	soma1 += (x[i] * y[i]);
	    	soma2 += (x[i] * y[i]) * (x[i] * y[i]);
	    	System.out.println(x[i] + " - " + y[i]);
	    } 
	   
	    
	    return Math.abs(soma1/soma2);
	  } 
	
	public static double menorDist(List<Imagem> imagens, double[] ent){
		int classe = 0;
		double melhorDist = Integer.MAX_VALUE;
		double dist = 0;
		try{
			for(Imagem lista: imagens){
				dist = distCosseno(lista.dist, ent);
				System.out.println(dist + " # Classe: " + lista.classe);//distancias certas
				if(dist < melhorDist){
					melhorDist = dist;
					classe = lista.classe;
					//System.out.println(classe + " dist: " + lista.dist);

				}
			}
		}catch (IndexOutOfBoundsException e) {
	           return 0;
	       }
		
		return classe;
	}
	
	public static void classificacao() throws IOException{
		List<Imagem> conjunto = LeArq("classificacaoDePlacas-0-455.arff");
		List<Imagem> conjunto2 = LeArq("classificacaoDePlacas-0-455-2.arff");
		
		int qtdClasseCerta = 0;
		
	
		for(Imagem imagens:conjunto) {
			if(menorDist(conjunto, imagens.dist) == imagens.classe){
				qtdClasseCerta++;
			}
							
							
		}
		System.out.println("Qtd classes certas: " + qtdClasseCerta);
	}
	
	static class Imagem{
		int classe;
		double dist[];
	}
		
	
	public static void main(String[] argv) throws IOException{
		classificacao();		
		
	}
}

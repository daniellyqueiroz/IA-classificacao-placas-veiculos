package algoritmo;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class KNN{
	
	private static List<Imagem> LeArq(String arq) throws IOException {
		List<Imagem> imagens = new ArrayList<Imagem>();
		
		BufferedReader reader = new BufferedReader(new FileReader(arq));		
		try {
			String line = reader.readLine(); 
						
			while((line = reader.readLine()) != null) {
				if(!(line.startsWith("%"))){
					if(!(line.startsWith("@"))){
						if(!line.trim().equals("")){
							String[] tokens = line.split(",");
							
							Imagem imagem = new Imagem();
							imagem.classe = Integer.parseInt(tokens[0]);
							//Classes OK
							System.out.println(tokens[0]);
											
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
			reader.close();
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
	    } 
	   
	    
	    return Math.abs(soma1/soma2);
	  } 
	
	public static double classificador(List<Imagem> imagens, double[] ent){
		int classe = 0;
		double melhorDist = Integer.MAX_VALUE;
		double dist;
		try{
			for(Imagem lista: imagens){
				dist = distCosseno(lista.dist, ent);
				if(dist < melhorDist){
					melhorDist = dist;
					classe = lista.classe;			

				}
			}
		}catch (IndexOutOfBoundsException e) {
	           return 0;
	       }
		
		return melhorDist;
	}
	
	static class Imagem{
		int classe;
		double dist[];
	}
		
	
	public static void main(String[] argv) throws IOException  {
		List<Imagem> conjunto = LeArq("classificacaoDePlacas-0-455.arff");
		
		int classeCerta = 0;
		
	
		for(Imagem imagens:conjunto) {
			if(classificador(conjunto, imagens.dist) == imagens.classe){
				classeCerta++;
				System.out.println(imagens.classe);
			}
							
							
		}
		System.out.println(classeCerta);
		
		
	}
}

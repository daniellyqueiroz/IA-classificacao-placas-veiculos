package algoritmo;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
							
							imagem.dist = new double[tokens.length - 1];
										
							for(int i = 1; i < tokens.length; i++) {
								imagem.dist[i-1] = Double.parseDouble(tokens[i]);
								
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
			soma += Math.pow(x[i] - y[i], 2);
		}
		return Math.sqrt(soma);
	}
	
	public static double distManhattan(double[]x, double[]y){
		double soma = 0;
		for(int i = 0; i < x.length; i++) {
			soma += Math.abs(x[i] - y[i]);
			
		}
		return soma;
	}
	
	//Distancia: calculando errado!!!!!!!!
	public static double distCosseno(double x[],double y[]) { 
		double soma1 = 0;
		double soma2 = 0;
	 
	    for (int i = 0; i < x.length; i++) {
	    		soma1 += (x[i] * y[i]);
		    	soma2 += (Math.pow(x[i], 2) * Math.pow(y[i], 2));
	    } 
	   
	    
	    return (soma1/soma2);
	  } 
	
	
	public static int distancias(List<Imagem> imagens, double[] ent, int k) throws IOException{
		double dist = 0;
		List<Imagem2> knn = new ArrayList<Imagem2>();
		
		int cont1 = 0;
		int cont2 = 0;
	
		
		for(Imagem lista: imagens){
			Imagem2 im = new Imagem2();
			dist = distCosseno(lista.dist, ent);	
			im.setDist(dist);
			im.setClasse(lista.classe);
			knn.add(im);
			Collections.sort(knn);
			
			
	}	
	knn = knn.subList(0, k);
	for(int i = 0; i < knn.size(); i++){
		if(knn.get(i).classe == 1){
			cont1++;
		}else if(knn.get(i).classe == 0){
			cont2++;
		}
	
	}
	if(cont1 > cont2)
		return 1;
	else 		
		return 0;

}
	
	
	public static void classificacao() throws IOException{
		List<Imagem> Treinamento = LeArq("classificacaoDePlacas-Treinamento.arff");
		List<Imagem> Teste = LeArq("classificacaoDePlacas-Teste.arff");
		
		int qtdClasseCerta = 0;		
		for(Imagem imagens:Treinamento) {
				if(distancias(Teste, imagens.dist,15) == imagens.classe){
					qtdClasseCerta++;
				}									
							
		}
		System.err.println("Usando k = 15 com Distancia Cosseno: \n");
	
		System.out.println("Quantidade classes certas: " + qtdClasseCerta);
		System.out.println("Porcentagem de acerto: " + (double)qtdClasseCerta / Treinamento.size() * 100 + "%");
		
	}
	
	static class Imagem{
		int classe;
		double dist[];
		
		
	}
	static class Imagem2 implements Comparable<Imagem2>{
		int classe;
		double dist;
		
		public int getClasse() {
			return classe;
		}
		public void setClasse(int classe) {
			this.classe = classe;
		}
		public double getDist() {
			return dist;
		}
		public void setDist(double dist) {
			this.dist = dist;
		}
		
		public int compareTo(Imagem2 imagem) {
		     if (this.dist < imagem.getDist()) {
		          return -1;
		     }
		     if (this.dist > imagem.getDist()) {
		          return 1;
		     }
		     return 0;
		}
		@Override
		public String toString() {
			return "Imagem2 [classe=" + classe + ", dist=" + dist + "]";
		}
		
		
	}
		
	
	public static void main(String[] argv) throws IOException{
		
		classificacao();		
		
	}

	
}

package algoritmo;

import java.util.List;

public class KNN{
	
	//Ler arquivo, colocar em lista
	
	public static double distEuclidiana(int[]x, int[]y){
		double soma = 0;
		for(int i = 0; i < x.length; i++) {
			soma += (x[i] - y[i]) * (x[i] - y[i]);
		}
		return Math.sqrt(soma);
	}
	
	public static double distHamming(int[]x, int[]y){
		double soma = 0;
		for(int i = 0; i < x.length; i++) {
			soma += (x[i] - y[i]);
		}
		return Math.abs(soma);
	}
	/* Não funciona
	public static double pondera(int[]x,int[]y){
		double w = 0;
		for(int i = 0; i < x.length; i++){
			w = 1 / (x[i] - y[i] * x[i] - y[i]);
		}
		return w;
	}
	
	public static double distEuclidianaPonderada(int[]x,int[]y) {
		double soma = 0;
		for(int i = 0; i < x.length; i++) {
			soma += (x[i] - y[i]) * (x[i] - y[i]) * pondera(x, y);
		}
		return Math.sqrt(soma);
	}
	*/
	public static double distCosseno(int[]x,int[]y) { 
		double soma1 = 0;
		double soma2 = 0;
	 
	    for (int i = 0; i < x.length; i++) {
	    	soma1 += (x[i] * y[i]);
	    	soma2 += (x[i] * y[i]) * (x[i] * y[i]);
	    } 
	   
	    
	    return Math.abs(soma1/soma2);
	  } 
	/*Classificar menor distancia
	 * 
	 * 
	public static int classificador(List<Imagem> imagens, int[] ent){
		int leg = 0;
		int melhorDist = Integer.MAX_VALUE;
		int dist;
		for(Imagem lista: imagens){
			dist = distEuclidiana(lista.ent, ent);
			if(dist < melhorDist){
				melhorDist = dist;
				leg = lista.leg;

			}
		}
		return leg;
	}
	*/
	public static void main(String[] argv)  {
		int v[] = {2,1};
		int s[] = {4,3};
		for(int i = 0; i< v.length;i++){
			System.out.println(distEuclidiana(v,s));
		}
		
	}
}

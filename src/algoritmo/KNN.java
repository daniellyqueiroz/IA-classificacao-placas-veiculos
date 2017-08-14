package algoritmo;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


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
			soma += Math.pow(x[i] - y[i], 2);
		}
		return Math.sqrt(soma);
	}
	
	public static double distManhattan(double[]x, double[]y){
		double soma = 0;
		for(int i = 0; i < x.length; i++) {
			soma += Math.abs(x[i] - y[i]);
			//System.out.println(x[i] + " - " + y[i]);
		}
		return soma;
	}
	
	//Distancia: calculando errado!!!!!!!!
	public static double distCosseno(double x[],double y[]) { 
		double soma1 = 0;
		double soma2 = 0;
	 
	    for (int i = 0; i < x.length; i++) {
	    	/*
	    		soma1 += (x[i] * y[i]);
		    	soma2 += (Math.pow(x[i], 2) * Math.pow(y[i], 2));
		    	//System.out.println(x[i] + " - " + y[i]);
	    	*/
	    	//soma1 = Math.max(Math.abs(x[i] - y[i]));
	    } 
	   
	    
	    return (soma1/soma2);
	  } 
	
	public static int distanciaK1(List<Imagem> imagens, double[] ent){
		int classe = 0;
		double melhorDist = Integer.MAX_VALUE;
		double dist = 0;
		try{
			for(Imagem lista: imagens){
				dist = distEuclidiana(lista.dist, ent);
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
	
	public static int distancias(List<Imagem> imagens, double[] ent){
		double dist = 0;
		List<Double> distancias = new ArrayList<Double>();
		List<Double> melhorDist = new ArrayList<Double>();
		TreeSet<Double> set = new TreeSet<>();
		
		List<Integer> classes = new ArrayList<Integer>();
		int index = 0;
		int cont1 = 0;
		int cont2= 0;
		
		
		for(Imagem lista: imagens){
			dist = distManhattan(lista.dist, ent);			
			distancias.add(dist);
			classes.add(lista.classe);
			set.addAll(distancias);
			melhorDist.addAll(distancias);
			//Collections.sort(melhorDist);
			//System.out.println(melhorDist);
			
			System.err.println(set);
			System.err.println(distancias+ "\n");
			for(int i = 0; i < distancias.size(); i++){
				//Meu problema está aqui!!!!
				if(set.contains(distancias.get(i))){
					System.out.println(distancias.size());
					index = distancias.indexOf(i);
					if(index == classes.indexOf(i)){
					//System.out.println(classes);
						if(classes.contains(0)){
							//System.out.println(classes);
							cont1++;
							//System.out.println(cont1);
							
						}else if(classes.contains(1)){
							cont2++;
							//System.err.println(cont2);
					}
				}
					
			}
				
		}
	}				
				
	//Collections.sort(set);
	//System.out.println(melhorDist+ "\n");
	//System.err.println(melhorDist);
	if(cont1 < cont2)
		return 1;
	else 
		return 0;
			
}
	
	
	public static void classificacao() throws IOException{
		List<Imagem> conjunto = LeArq("classificacaoDePlacas-0-455.arff");
		List<Imagem> conjunto2 = LeArq("classificacaoDePlacas-0-455-2.arff");
		
		int qtdClasseCerta = 0;		
		for(Imagem imagens:conjunto) {
			//System.out.println(distancias(conjunto, imagens.dist));
			//for(int i = 0; i < conjunto.size(); i++){
				if(distancias(conjunto, imagens.dist) == imagens.classe){
					qtdClasseCerta++;
				}
			//}
									
							
		}
		
		System.out.println("Porcentagem classes certas: " + qtdClasseCerta);
		
	}
	
	static class Imagem{
		int classe;
		double dist[];
	}
		
	
	public static void main(String[] argv) throws IOException{
		classificacao();		
		
	}
}


public class Imagem {
	private String nome;
	private String caminho;
	
	Imagem(String caminho, String nome){
		this.nome = nome;
		this.caminho =caminho;	
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCaminho() {
		return caminho;
	}
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
	
	public String imprime() {
		String resultado = this.caminho + "/" + this.nome;
		return resultado;
	}

}

package otica;

public class oculos extends produto {
	private String modelo, cor;
	private int tamanho;

	public oculos(String nome, boolean emEstoque, int codigo, double valor,
			String modelo, String cor, int tamanho) {
		super(nome, emEstoque, codigo, valor);
		this.modelo = modelo;
		this.cor = cor;
		this.tamanho = tamanho;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

}

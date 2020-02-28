package otica;

public class lentesDeContato extends produto {
	private String cor;
	private double grauOlhoEsquerdo, grauOlhoDireito;

	public lentesDeContato(String nome, boolean emEstoque, int codigo,
			double valor, String cor, double grauOlhoEsquerdo,
			double grauOlhoDireito) {
		super(nome, emEstoque, codigo, valor);
		this.cor = cor;
		this.grauOlhoEsquerdo = grauOlhoEsquerdo;
		this.grauOlhoDireito = grauOlhoDireito;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public double getGrauOlhoEsquerdo() {
		return grauOlhoEsquerdo;
	}

	public void setGrauOlhoEsquerdo(double grauOlhoEsquerdo) {
		this.grauOlhoEsquerdo = grauOlhoEsquerdo;
	}

	public double getGrauOlhoDireito() {
		return grauOlhoDireito;
	}

	public void setGrauOlhoDireito(double grauOlhoDireito) {
		this.grauOlhoDireito = grauOlhoDireito;
	}

}

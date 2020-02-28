package otica;

public class lentes extends produto {
	private double grauOlhoEsquerdo, grauOlhoDireito;
	private boolean transitions, videoFilter, antirreflexo, antirisco, crizal;

	public lentes(String nome, boolean emEstoque, int codigo, double valor,
			double grauOlhoEsquerdo, double grauOlhoDireito,
			boolean transitions, boolean videoFilter, boolean antirreflexo,
			boolean antirisco, boolean crizal) {
		super(nome, emEstoque, codigo, valor);
		this.grauOlhoEsquerdo = grauOlhoEsquerdo;
		this.grauOlhoDireito = grauOlhoDireito;
		this.transitions = transitions;
		this.videoFilter = videoFilter;
		this.antirreflexo = antirreflexo;
		this.antirisco = antirisco;
		this.crizal = crizal;
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

	public boolean isTransitions() {
		return transitions;
	}

	public void setTransitions(boolean transitions) {
		this.transitions = transitions;
	}

	public boolean isVideoFilter() {
		return videoFilter;
	}

	public void setVideoFilter(boolean videoFilter) {
		this.videoFilter = videoFilter;
	}

	public boolean isAntirreflexo() {
		return antirreflexo;
	}

	public void setAntirreflexo(boolean antirreflexo) {
		this.antirreflexo = antirreflexo;
	}

	public boolean isAntirisco() {
		return antirisco;
	}

	public void setAntirisco(boolean antirisco) {
		this.antirisco = antirisco;
	}

	public boolean isCrizal() {
		return crizal;
	}

	public void setCrizal(boolean crizal) {
		this.crizal = crizal;
	}

}

package otica;

public class produto {
	private String nome;
	private boolean emEstoque;
	private int codigo;
	private double valor;

	public produto(String nome, boolean emEstoque, int codigo, double valor) {
		this.nome = nome;
		this.emEstoque = emEstoque;
		this.codigo = codigo;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isEmEstoque() {
		return emEstoque;
	}

	public void setEmEstoque(boolean emEstoque) {
		this.emEstoque = emEstoque;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void remove(otica.produto produto) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public String toString() {
        return String.valueOf(codigo);
    }
        
        

}

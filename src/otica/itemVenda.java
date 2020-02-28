package otica;

public class itemVenda{
	private produto produto;
	private int quantidade;
        private int venda;

    public itemVenda(produto produto, int quantidade, int venda) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.venda = venda;
    }

	public produto getProduto() {
		return produto;
	}

	public void setProduto(produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

    /**
     * @return the venda
     */
    public int getVenda() {
        return venda;
    }

    /**
     * @param venda the venda to set
     */
    public void setVenda(int venda) {
        this.venda = venda;
    }


}

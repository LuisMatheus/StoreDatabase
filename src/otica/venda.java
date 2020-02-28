package otica;

import java.util.ArrayList;
import java.util.Date;

public class venda {
	private int notaFiscal;
	private Date data;
	private cliente cliente;
	

    public venda(int notaFiscal, Date data, cliente cliente) {
        this.notaFiscal = notaFiscal;
        this.data = data;
        this.cliente = cliente;
    }


	public int getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(int notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public cliente getCliente() {
		return cliente;
	}

	public void setCliente(cliente cliente) {
		this.cliente = cliente;
	}

}

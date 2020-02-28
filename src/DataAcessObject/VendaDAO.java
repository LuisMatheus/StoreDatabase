package DataAcessObject;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import otica.cliente;
import otica.itemVenda;
import otica.produto;

import otica.venda;

public class VendaDAO {

    private Connection con = null;
    private PreparedStatement stmt = null;

    public void inserir(venda v, ArrayList<itemVenda> iv) {
        try {
            con = Conexao.getConexao();
            stmt = con.prepareStatement("INSERT INTO venda VALUES (?,?,?,?)");

            stmt.setInt(1, v.getNotaFiscal());
            java.sql.Date d = new java.sql.Date(v.getData().getTime());
            stmt.setDate(2, d);
            stmt.setString(3, v.getCliente().getNome());
            stmt.setInt(4, Integer.parseInt(v.getCliente().getCpf()));
            

            stmt.executeUpdate();
            
            for(itemVenda f : iv){
                
                stmt = con.prepareStatement("INSERT INTO itemvenda (Venda_Codigo,Produto,Quantidade,Produto_Codigo) VALUES (?,?,?,?)");

                stmt.setInt(1, f.getVenda() );
                stmt.setString(2, f.getProduto().getNome());
                stmt.setDouble(3, f.getQuantidade());
                stmt.setDouble(4, f.getProduto().getCodigo());
                
                stmt.executeUpdate();
                
                stmt = con.prepareStatement("INSERT INTO itemvenda_venda VALUES (?,?)");
                stmt.setInt(1, f.getVenda());
                stmt.setInt(2, v.getNotaFiscal() );
                
                stmt.executeUpdate();
            }
            

        } catch (NumberFormatException e) {
            System.err.print("Venda não Encontrada");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void excluir(int notaFiscal) {
        try {
            con = Conexao.getConexao();
            stmt = con.prepareStatement("DELETE FROM venda WHERE notaFiscal=?");
            
            stmt.setInt(1, notaFiscal);
            
            stmt.executeUpdate();
            
            stmt = con.prepareStatement("DELETE FROM itemVenda_Venda WHERE Venda_notaFiscal=?");
            
            stmt.setInt(1, notaFiscal);
            
            stmt.executeUpdate(); 
            
        } catch (SQLException e) {
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public venda buscar(String notaFiscal) {
        try{
            con = Conexao.getConexao();
            stmt = con.prepareStatement("SELECT * FROM venda WHERE notaFiscal=?");
            
            stmt.setString(1, notaFiscal);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
              String n = rs.getString("notaFiscal");
              Date d = rs.getDate("data");
              cliente c = (cliente) rs.getObject("cliente");
              boolean b = rs.getBoolean("emitirRecibo");
              venda v = new venda(Integer.parseInt(n), d, c);
              return v;
            }
            
            }catch(SQLException e){
            }finally{
                try {
                    con.close();
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
		return null;
    }
    
    public itemVenda buscar(int Venda_Codigo){
        try {
            con = Conexao.getConexao();
            stmt = con.prepareStatement("SELECT * FROM itemvenda WHERE Venda_Codigo=?");
            
            stmt.setInt(1, Venda_Codigo);
            
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                produto p = new ProdutoDAO().buscar(rs.getInt("Produto_Codigo"));
                int q = rs.getInt("Quantidade");
                int vc = Venda_Codigo;
                itemVenda iv = new itemVenda(p, q, vc);
                return iv;
            }
        } catch (SQLException e) {
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return null;
    }
    
    public ArrayList<itemVenda> listar(int notafiscal){
        try {
            ArrayList<itemVenda> temp = new ArrayList<>();
            con = Conexao.getConexao();
            stmt = con.prepareStatement("SELECT * FROM itemvenda_venda WHERE Venda_notaFiscal=?");
            
            stmt.setInt(1,notafiscal);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                itemVenda iv = buscar(rs.getInt("ItemVenda_Venda"));
                temp.add(iv);
            }
            return temp;
        } catch (SQLException e) {
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return null;
    }

}

package DataAcessObject;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import otica.cliente;

public class ClienteDAO {

    private PreparedStatement stmt = null;
    private Connection con = null;
    public void inserir(cliente c) {
       
        try {
            con = Conexao.getConexao();
            stmt = con.prepareStatement("INSERT INTO cliente VALUES (?,?,?,?,?)");
            
            stmt.setInt(1, Integer.parseInt(c.getCpf()));
            stmt.setString(2, c.getNome());
            stmt.setString(3, c.getEmail());
            java.sql.Date d = new Date(c.getDataDeNascimento().getTime());
            stmt.setDate(4, d);
            stmt.setInt(5, c.getTelefone());
            
            stmt.executeUpdate();
            
        } catch (NumberFormatException e) {
            System.err.print("Cliente não Encontrado");
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
    
    public void alterar(cliente c) {
        
        try {
            con = Conexao.getConexao();
            stmt = con.prepareStatement("UPDATE cliente SET Nome=?, Email=?, Telefone=?, dataDeNascimento=? WHERE Cpf=? ");
            
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getEmail());
            stmt.setInt(3, c.getTelefone());
            java.sql.Date d = new Date(c.getDataDeNascimento().getTime());
            stmt.setDate(4, d);
            
            stmt.setInt(5, Integer.parseInt(c.getCpf()));
            
            stmt.executeUpdate();
            
        } catch (Exception e) {
            System.err.print("Cliente não Encontrado");
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
    
    public void excluir(int cpf) {
        
        try {
            con = Conexao.getConexao();
            stmt = con.prepareStatement("DELETE FROM  cliente WHERE Cpf=?");
            
            stmt.setInt(1, cpf);
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
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
    
    public cliente buscar(int cpf) {
        
        try {
            con = Conexao.getConexao();
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE Cpf=?");
            stmt.setInt(1, cpf);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String n = rs.getString("Nome");
                Date da = rs.getDate("dataDeNascimento");
                String e = rs.getString("Email");
                int t = rs.getInt("Telefone");
                cliente p = new cliente(n, e, da, String.valueOf(cpf), t);
                return p;
            }
        } catch (Exception e) {
            System.err.println("Cliente não encontrado");
        } finally {
            try {
                if(con!=null){
                con.close();
                }
                if(stmt!=null){
                stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    /**
     *
     * @param nome
     * @return
     */
    public cliente buscar(String nome) {
        
        try {
            con = Conexao.getConexao();
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE Nome=?");
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Date da = rs.getDate("dataDeNascimento");
                String e = rs.getString("Email");
                int t = rs.getInt("Telefone");
                int c = rs.getInt("Cpf");
                cliente p = new cliente(nome, e, da, String.valueOf(c), t);
                return p;
            } else {
                return null;
            }
            
        } catch (Exception e) {
            System.out.println("Cliente não encontrado");
            return null;
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ArrayList<cliente> listar(String nome) {
        PreparedStatement stmt = null;
        Connection con = null;
        try {
            con = Conexao.getConexao();
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE Nome LIKE ?");
            stmt.setString(1, "%" + nome + "%");
            
            ResultSet rs = stmt.executeQuery();
            
            ArrayList<cliente> temp = new ArrayList<>();
            
            while (rs.next()) {
                
                String c = String.valueOf(rs.getInt("Cpf"));
                Date d = rs.getDate("dataDeNascimento");
                String e = rs.getString("Email");
                int t = rs.getInt("Telefone");
                
                temp.add(new cliente(nome, e, d, c, t));
                
            }
            
            return temp;
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public ArrayList<cliente> listar() {
        PreparedStatement stmt = null;
        Connection con = null;
        try {
            con = Conexao.getConexao();
            stmt = con.prepareStatement("SELECT * FROM cliente");
            
            ResultSet rs = stmt.executeQuery();
            
            ArrayList<cliente> temp = new ArrayList<>();
            while (rs.next()) {
                
                String n = rs.getString("Nome");
                String e = rs.getString("Email");
                Date d = rs.getDate("dataDeNascimento");
                String c = String.valueOf(rs.getInt("Cpf"));
                int t = rs.getInt("Telefone");
                
                temp.add(new cliente(n, e, d , c, t));
            }
            
            return temp;
            
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
}

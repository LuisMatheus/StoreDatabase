package DataAcessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import otica.lentes;
import otica.lentesDeContato;
import otica.oculos;
import otica.produto;

public class ProdutoDAO {

    private Connection con = null;
    private PreparedStatement stmt = null;

   
    public void inserir(oculos c) {
        try {
            con = Conexao.getConexao();

            //-----------------------------------------------------------\\
            stmt = con.prepareStatement("INSERT INTO oculos VALUES (Modelo,Cor,Tamanho) (?,?,?)");

            stmt.setString(1, c.getModelo());
            stmt.setString(2, c.getCor());
            stmt.setDouble(3, c.getTamanho());

            stmt.executeUpdate();

            //------------------------------------------------------------\\
            stmt = con.prepareStatement("INSERT INTO produto (Codigo,Nome,EmEstoque,Valor,Oculos_Codigo) VALUES (?,?,?,?,?)");

            stmt.setInt(1, c.getCodigo());
            stmt.setString(2, c.getNome());
            stmt.setBoolean(3, c.isEmEstoque());
            stmt.setDouble(4, c.getValor());
            stmt.setInt(5, 0);
            
            stmt.executeUpdate();
        } catch (SQLException e) {

        } finally {
            try {
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void inserir(lentes l) {
        try {
            con = Conexao.getConexao();

            //------------------------------------------------------------//
            stmt = con.prepareStatement("INSERT INTO lentes (grauOlhoEsquerdo,grauOlhoDireito,Transitions,VideoFilter,Antirreflexo,Lentescol,Crizal) VALUES (?,?,?,?,?,?,?)");

            stmt.setDouble(1, l.getGrauOlhoEsquerdo());
            stmt.setDouble(2, l.getGrauOlhoDireito());
            stmt.setBoolean(3, l.isTransitions());
            stmt.setBoolean(4, l.isVideoFilter());
            stmt.setBoolean(5, l.isAntirreflexo());
            stmt.setBoolean(6, l.isAntirisco());
            stmt.setBoolean(7, l.isCrizal());

            stmt.executeUpdate();

            //------------------------------------------------------------//
            stmt = con.prepareStatement("INSERT INTO Produto (Codigo,Nome,EmEstoque,Valor,Lentes_Codigo) VALUES(?,?,?,?,?)");
            stmt.setInt(1, l.getCodigo());
            stmt.setString(2, l.getNome());
            stmt.setBoolean(3, l.isEmEstoque());
            stmt.setDouble(4, l.getValor());
            stmt.setInt(5, 1);

            stmt.executeUpdate();


        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }


    public void inserir(lentesDeContato lc) {
        try {
            con = Conexao.getConexao();

            //-----------------------------------------------------------\\
            stmt = con.prepareStatement("INSERT INTO (GrauOlhoDireito,GrauOlhoEsquerdo,Cor)lentesdecontato VALUES (?,?,?,?)");

            stmt.setDouble(1, lc.getGrauOlhoDireito());
            stmt.setDouble(2, lc.getGrauOlhoEsquerdo());
            stmt.setString(3, lc.getCor());

            stmt.executeUpdate();

            //-----------------------------------------------------------\\
            stmt = con.prepareStatement("INSERT INTO produto (Codigo,Nome,EmEstoque,Valor,LentesDeContato_Codigo)VALUES (?,?,?,?,?,?,?)");

            stmt.setInt(1, lc.getCodigo());
            stmt.setString(2, lc.getNome());
            stmt.setBoolean(3, lc.isEmEstoque());
            stmt.setDouble(4, lc.getValor());
            stmt.setInt(5,0);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void alterar(produto p) {
        try {
            con = Conexao.getConexao();
            stmt = con.prepareStatement("UPDATE produto SET Nome=?, EmEstoque=?, Valor=? WHERE Codigo=? ");

            stmt.setString(1, p.getNome());
            stmt.setBoolean(2, true);
            stmt.setDouble(3, p.getValor());
            stmt.setInt(4, p.getCodigo());

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
            }
        }
    }

    public void excluir(int codigo) {
        try {
            con = Conexao.getConexao();
            stmt = con.prepareStatement("DELETE FROM  produto WHERE Codigo=?");

            stmt.setInt(1, codigo);

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

    public produto buscar(String nome) {
        try {
            con = Conexao.getConexao();
            stmt = con.prepareStatement("SELECT * FROM produto WHERE Nome like ?");

            stmt.setString(1, "%" + nome + "%");

            ResultSet rs = stmt.executeQuery();
            ArrayList<produto> temp = new ArrayList<>();
            if (rs.next()) {
                int c = rs.getInt("Codigo");
                boolean b = rs.getBoolean("EmEstoque");
                double v = rs.getDouble("Valor");
                produto p = new produto(nome, b, c, v);
                return p;
            }
        } catch (SQLException e) {
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public produto add(String nome) {
        try {
            con = Conexao.getConexao();
            stmt = con.prepareStatement("SELECT * FROM produto WHERE Nome = ?");

            stmt.setString(1,nome);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int c = rs.getInt("Codigo");
                boolean b = rs.getBoolean("EmEstoque");
                double v = rs.getDouble("Valor");
                produto p = new produto(nome, b, c, v);
                return p;  
            }
        } catch (SQLException e) {
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public produto buscar(int codigo) {
        try {
            con = Conexao.getConexao();
            stmt = con.prepareStatement("SELECT * FROM produto WHERE Codigo=?");

            stmt.setInt(1, codigo);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String n = rs.getString("Nome");
                boolean b = rs.getBoolean("EmEstoque");
                double v = rs.getDouble("Valor");
                produto p = new produto(n, b, codigo, v);
                return p;

            }

        } catch (SQLException e) {
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ArrayList<produto> listar(String nome) {
        try {
            con = Conexao.getConexao();
            stmt = con.prepareStatement("SELECT * FROM produto WHERE Nome LIKE ? ");

            stmt.setString(1, "%"+nome+"%");

            ResultSet rs = stmt.executeQuery();
            
            ArrayList<produto> temp = new ArrayList<>();
            while (rs.next()) {
                String n = rs.getString("Nome");
                boolean b = rs.getBoolean("EmEstoque");
                double v = rs.getDouble("Valor");
                int c = rs.getInt("Codigo");
                produto f = new produto(n, b, c, v);
                temp.add(f);
            }

            return temp;

        } catch (SQLException e) {
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    

    public ArrayList<produto> listar() {
        try {
            con = Conexao.getConexao();
            stmt = con.prepareStatement("SELECT * FROM produto");

            ResultSet rs = stmt.executeQuery();
            ArrayList<produto> temp = new ArrayList<>();
            while (rs.next()) {
                String n = rs.getString("Nome");
                boolean b = rs.getBoolean("EmEstoque");
                double v = rs.getDouble("Valor");
                int c = rs.getInt("Codigo");
                temp.add(new produto(n, b, c, v));
            }

            return temp;

        } catch (SQLException e) {
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;

    }
    
    public ArrayList<produto> listar(int codigo){
         try {
            con = Conexao.getConexao();
            stmt = con.prepareStatement("SELECT * FROM produto WHERE codigo=?");

            stmt.setInt(1, codigo);

            ResultSet rs = stmt.executeQuery();
            ArrayList<produto> temp = new ArrayList<>();
            while (rs.next()) {
                
                String n = rs.getString("Nome");
                boolean b = rs.getBoolean("EmEstoque");
                double v = rs.getDouble("Valor");
                temp.add(new produto(n, b, codigo, v));
            }

            return temp;

        } catch (SQLException e) {
        } finally {
            try {
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

}

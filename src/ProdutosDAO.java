/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection con;
    PreparedStatement st;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produtos){
        con = new conectaDAO().connectDB();
        
        int status;

        try{
            
            st = con.prepareStatement(
                "INSERT INTO produtos (nome, valor, status) VALUES(?,?,?)");
            st.setString(1, produtos.getNome());
            st.setString(2, produtos.getValor().toString());
            st.setString(3, produtos.getStatus());
            
            status = st.executeUpdate();

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao Conectar: " + ex.getMessage());
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        con = new conectaDAO().connectDB();
        try{
            ArrayList<ProdutosDTO> produtos = new ArrayList<>();
            st = con.prepareStatement("Select * from produtos");
            rs = st.executeQuery();
            
            while(rs.next()){
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(Integer.parseInt(rs.getString("id")));
                produto.setNome(rs.getString("nome"));
                produto.setValor(Integer.parseInt(rs.getString("valor")));
                produto.setStatus(rs.getString("status"));
                produtos.add(produto);
                
            }

            return produtos;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de conexão " + ex.getMessage());
            return null;
        }
    }
    
    public int venderProduto(ProdutosDTO produto){
        con = new conectaDAO().connectDB();

        int status;
        try{
            System.out.println("aqui");
            st = con.prepareStatement("update produtos set nome = ?, valor = ?, status = ? where id = ?");
            st.setString(1, produto.getNome());
            st.setString(2, produto.getValor().toString());
            st.setString(3, produto.getStatus());
            st.setString(4, produto.getId().toString());
            System.out.println(produto.getStatus() + 3 + con);
            status = st.executeUpdate();
            System.out.println(produto.getStatus() + 4);
            return status;
        }catch(SQLException ex) {
            System.out.println(ex.getErrorCode());
            return ex.getErrorCode();
        }
    }
    
    public ArrayList<ProdutosDTO> consultaStatus(String status){
        con = new conectaDAO().connectDB();
        try{
            ArrayList<ProdutosDTO> produtos = new ArrayList<>();
            st = con.prepareStatement("select * from produtos where status = ?");
            st.setString(1, status);
            System.out.println(status);
            rs = st.executeQuery();
            
            while(rs.next()){
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                produtos.add(produto);
            }
            System.out.println(status);
            return produtos;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de conexão " + ex.getMessage());
            return null;
        }
    }
}


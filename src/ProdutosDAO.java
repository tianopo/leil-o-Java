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
    
    public int venderProduto(int id){
        con = new conectaDAO().connectDB();
        ProdutosDTO produtos = new ProdutosDTO();
        int status;
        try{
            produtos.setId(id);
            st = con.prepareStatement("update filmes set status = ? where id = ?");
            st.setString(1, produtos.getStatus());
            st.setString(2, produtos.getId().toString());
            status = st.executeUpdate();
            
            produtos.setStatus("Vendido");
            
            return status;
        }catch(SQLException ex) {
            System.out.println(ex.getErrorCode());
            return ex.getErrorCode();
        }
    }  
}


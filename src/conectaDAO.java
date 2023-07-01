
import com.mysql.cj.x.protobuf.MysqlxNotice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class conectaDAO {
    Connection con;
    PreparedStatement st;
    ResultSet rs;
    
    private final String url = "jdbc:mysql://localhost:3306/leilão";
    private final String user = "root";
    private final String password = "4queijos.";
    
    public Connection connectDB(){ 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);     
        }catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Conexão: " + ex.getMessage());      
        }
        return con;
    }
    
    public void desconectar(){
        try{
            if(con != null && !con.isClosed()){
                con.close();
                System.out.println("Desconectou do Banco");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Não foi possível se desconectar");
        }
    }
    
    public int inserir(ProdutosDTO produtos){
        int status;

        try{
            
            st = con.prepareStatement("INSERT INTO produtos (nome, datalancamento, categoria) VALUES(?,?,?)");
            st.setString(1, produtos.getId().toString());
            st.setString(2, produtos.getNome());
            st.setString(3, produtos.getValor().toString());
            st.setString(4, produtos.getStatus());
            
            status = st.executeUpdate();
            return status;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao Conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
}

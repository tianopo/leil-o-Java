
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            con = DriverManager.getConnection(url, user, password);     
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
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
}

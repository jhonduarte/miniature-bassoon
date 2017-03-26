import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class database {
  private static Connection Conexion;
  public void MySQLConnection(String user, String pass, String db_name) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_name, user, pass);
            JOptionPane.showMessageDialog(null, "Se ha iniciado la conexión con el servidor de forma exitosa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
  //Metodo para crear la Base de datos
   public void createDB(String name) {
        try {
            String Query = "CREAR BASE DE DATOS" + name;
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            closeConnection();
            MySQLConnection("admin", "", name);
JOptionPane.showMessageDialog(null, "Se ha creado la base de datos " + name + " de forma exitosa");
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
  //Crea la lista de la db
   public void crearTabla(String name) {
        try {
            String Query = "Crear Tabla" + name + ""
                    + " (ID VARCHAR(25),Producto VARCHAR(100),Cantidad VARCHAR(50),"
                    + " PrecioUnitario VARCHAR(50), Precio Total VARCHAR(50))";

            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
JOptionPane.showMessageDialog(null, "Se ha creado la tabla " + name + " de forma exitosa");
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
   public void crearTabla(String table_name, String ID, String Producto, String Cantidad, String PrecioUnitario, String PrecioTotal r) {
        try {
            String Query = "Insertar" + table_name + " Valores"
                    + "\"" + ID + "\", "
                    + "\"" + Producto + "\", "
                    + "\"" + Cantidad + "\", "
                    + "\"" + PrecioUnitario + "\", "    
                    + "\"" + PrecioTotal + "\", "
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }
    }
  
   //Método para consultar los valores de la Base de Datos
   public void getValues(String table_name) {
        try {
            String Query = "Seleccionar * de " + table_name;
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getString("ID") + " "
                        + "Producto: " + resultSet.getString("Producto") + " "
                        + "Cantidad: " + resultSet.getString("Edad") + " "
                        + "Precio Unitario: " + resultSet.getString("PrecioUnitario) + " "
                        + "Precio Total: " + resultSet.getString("PrecioTotal"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
        }
    }
    
    //Método para borrar el registro de la Base de datos. 
     public void deleteRecord(String table_name, String ID) {
        try {
            String Query = "DELETE FROM " + table_name + " WHERE ID = \"" + ID + "\"";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
        }
    }                               
  //Metodo que finaliza la conexión con el servidor
   public static void main(String[] args) {
        MySQL db = new MySQL();
        db.MySQLConnection("admin", "", "");
    }
}

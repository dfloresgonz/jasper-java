package reportenuevo;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteNuevo {

    public static Connection conectar() {
        Connection con = null;
        try {
            String url = "jdbc:mysql://localhost:3306/tienda?user=root&password=mysqladmin";
            con = DriverManager.getConnection(url);
            if (con != null) {
                System.out.println("Conexion Satisfactoria");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
    
    public static void main(String[] args) {
        try {
            JasperReport jr = (JasperReport) JRLoader.loadObject(ReporteNuevo.class.getResource("./reporte.jasper"));
            Map parametros = new HashMap<String, Object>();
            parametros.put("PRECIO", 1200);
            parametros.put("PRECIO2", 3300);
            JasperPrint jp = JasperFillManager.fillReport(jr, parametros, conectar());
            JasperViewer jv = new JasperViewer(jp);
            jv.show();

//            JFrame f;
//            Statement statement = conectar().createStatement();
//            String query = "SELECT * FROM producto";
//            ResultSet resultSet = statement.executeQuery(query);
//            
//            DefaultTableModel model = new DefaultTableModel(new String[]{"Código", "Producto", "Precio"}, 0);
//            while(resultSet.next()) {
//                int idProd = resultSet.getInt("id_producto");
//                String descProd = resultSet.getString("desc_producto");
//                int precio = resultSet.getInt("precio");
//                model.addRow(new Object[]{idProd, descProd, precio});
//            }
//            JTable table = new JTable(model);
//            
//            f = new JFrame();
//            f.setTitle("JTable Example");
//            
//            JTextField txtNombre = new JTextField("");
//            JTextField txtPrecio = new JTextField("");
//            JButton button3 = new JButton("Registrar");
//            
//            table.setBounds(10, 40, 200, 300);
//            //JScrollPane sp = new JScrollPane(table);
//            //sp.add(button);
//            f.setLayout(new GridLayout(3,1));
//            Panel p1 = new Panel();
//            p1.setSize(new Dimension(700, 80));
//            p1.setLayout(new GridLayout(1,3));
//            p1.add(txtNombre);p1.add(txtPrecio);p1.add(button3);
//            
//            f.add(p1);
//            f.add(table);
//            f.setSize(700, 400);
//            f.setVisible(true);
            
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
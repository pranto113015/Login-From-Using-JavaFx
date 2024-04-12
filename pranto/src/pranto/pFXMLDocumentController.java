package pranto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/*
  @author Pranto Saha
 */
public class pFXMLDocumentController implements Initializable {

    @FXML
    private Button loginbtn;

    @FXML
    private PasswordField textpass;

    Connection con;        //import java.sql.Connection;
    PreparedStatement pst; //import java.sql.PreparedStatement;
    ResultSet rs;          //import java.sql.ResultSet;

    @FXML
    private TextField textuname;

    @FXML
    void login(ActionEvent event) {

        String uname = textuname.getText();
        String pass = textpass.getText();

        if (uname.equals("") && pass.equals("")) {

            JOptionPane.showMessageDialog(null, "Username or Password Blank");
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/java", "root", ""); //import java.sql.DriverManager;
                pst = con.prepareStatement("select *from logn where username=? and password=?");

                pst.setString(1, uname);
                pst.setString(2, pass);
                rs = pst.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login Successfully");//import javax.swing.JOptionPane;
                } else {
                    JOptionPane.showMessageDialog(null, "Login Failed Please Try Again");
                    textuname.setText("");
                    textpass.setText("");
                    textuname.requestFocus();
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(pFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } //import java.sql.SQLException;
            catch (SQLException ex) {
                Logger.getLogger(pFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}

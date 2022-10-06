package Candidate;

import Database.SQLDatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddCandidateController {

        @FXML
        private TextField Agefield;

        @FXML
        private Button Cancel;

        @FXML
        private TextField Eidfield;

        @FXML
        private Text Errormessage;

        @FXML
        private TextField Idfield;

        @FXML
        private TextField Mailfield;

        @FXML
        private TextField Namefield;

        @FXML
        private TextField Oidfield;

        @FXML
        private TextField Passwordfield;

        @FXML
        private Button addnewv;


    @FXML
    void initialize(){

        addnewv.setOnMouseClicked(event -> {

            if(Eidfield.getText().isEmpty() || Namefield.getText().isEmpty() || Passwordfield.getText().isEmpty()|| Idfield.getText().isEmpty()|| Mailfield.getText().isEmpty() || Agefield.getText().isEmpty() || Oidfield.getText().isEmpty()){
                Errormessage.setText("Please Enter All informations");
            }
            else{
                Errormessage.setText("");
                SQLDatabaseConnection conectnow=new SQLDatabaseConnection();
                Connection connectDB=conectnow.getConnection();


                try {
                    Statement statment = connectDB.createStatement();
                    ResultSet resultSet = null;
                    String Statment="exec newcandidate '"+Idfield.getText()+"','"+Oidfield.getText()+"','"+Eidfield.getText()+"','"+Namefield.getText()+"','"+Mailfield.getText()+"','"+Passwordfield.getText()+"','"+Agefield.getText()+"','0'";

                    statment.executeQuery(Statment);

                    System.out.println("done");
                } catch (SQLException e) {
                }
                Stage stage= (Stage) addnewv.getScene().getWindow();
                stage.close();
            }
        });

        Cancel.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 ) {

                Stage stage= (Stage) Cancel.getScene().getWindow();
                stage.close();
            }
        });
    }

}

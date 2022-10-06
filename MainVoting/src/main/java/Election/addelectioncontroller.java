package Election;

import Database.SQLDatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.DataFormat;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class addelectioncontroller {
    @FXML
    private Button Cancel;

    @FXML
    private Text Errormessage;

    @FXML
    private TextField Oidfield;

    @FXML
    private Button addnewv;

    @FXML
    private DatePicker edate;

    @FXML
    private TextField eidfield;

    @FXML
    private TextField namefield;

    @FXML
    private DatePicker sdate;
    private LocalDateTime Date;

    @FXML
    void initialize(){

        Date = LocalDateTime.now();
        DateTimeFormatter dataFormat= DateTimeFormatter.ofPattern("MM-dd-YYYY");
               /// System.out.println(dataFormat.format(Date));
        addnewv.setOnMouseClicked(event -> {
            System.out.println(eidfield.getText()+ Oidfield.getText() + namefield.getText()+edate.getValue()+sdate.getValue());

            if(eidfield.getText().isEmpty() || Oidfield.getText().isEmpty() || namefield.getText().isEmpty()|| edate.getValue()==null|| sdate.getValue()==null){
                Errormessage.setText("Please Enter All informations");
            }
            else{
                Errormessage.setText("");
                SQLDatabaseConnection conectnow=new SQLDatabaseConnection();
                Connection connectDB=conectnow.getConnection();


                try {
                    Statement statment = connectDB.createStatement();
                    ResultSet resultSet = null;
                  ///  exec newElection '111','1','MangerElection','2020-12-2 10:00:00','03:00:00';
                    String Statment="exec newElection '"+eidfield.getText()+"','"+Oidfield.getText()+"','"+namefield.getText()+"','"+sdate.getValue()+"','"+edate.getValue()+"'";

                    statment.executeQuery(Statment);

                 ///   System.out.println("done");
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

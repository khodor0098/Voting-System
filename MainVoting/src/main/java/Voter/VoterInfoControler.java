package Voter;

import Database.SQLDatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VoterInfoControler {
    @FXML
    private Button Cancel;

    @FXML
    private Button Removev;

    @FXML
    private Text tage;

    @FXML
    private Text teid;

    @FXML
    private Text temail;

    @FXML
    private Text tid;

    @FXML
    private Text tname;



    @FXML
    private Text tpassword;

    private Votermodel v;
    public VoterInfoControler(Votermodel data) {
        v=data;
    }

    @FXML
    void initialize(){
        tage.setText(String.valueOf(v.getVage()));
        tname.setText(v.getVname());
        temail.setText(v.getVEmail());
        teid.setText(String.valueOf(v.getEid()));
        tid.setText(String.valueOf(v.getId()));

        Removev.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 ) {

                SQLDatabaseConnection conectnow=new SQLDatabaseConnection();
                Connection connectDB=conectnow.getConnection();


                try {
                    Statement statment = connectDB.createStatement();
                    ResultSet resultSet = null;
                    String Statment="exec deleteVoter '"+v.getId()+"'";
                    statment.executeQuery(Statment);

                    System.out.println("done");
                } catch (SQLException e) {
                }
                Stage stage= (Stage) Removev.getScene().getWindow();
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

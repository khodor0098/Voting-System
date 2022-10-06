package Candidate;

import Database.SQLDatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Candidateinfocontroller {


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


    CandidateModule CM;

    public Candidateinfocontroller(CandidateModule rowData) {
        CM = rowData;
    }

    @FXML
    void initialize(){
        tage.setText(String.valueOf(CM.getCage()));
        tname.setText(CM.getCname());
        temail.setText(CM.getCEmail());
        teid.setText(String.valueOf(CM.getEid()));
        tid.setText(String.valueOf(CM.getId()));

        Removev.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 ) {

                SQLDatabaseConnection conectnow=new SQLDatabaseConnection();
                Connection connectDB=conectnow.getConnection();


                try {
                    Statement statment = connectDB.createStatement();
                    ResultSet resultSet = null;
                    String Statment="exec deleteCandidate '"+CM.getId()+"'";
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

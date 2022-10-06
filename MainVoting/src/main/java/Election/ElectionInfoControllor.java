package Election;


import Candidate.CandidateModule;
import Database.SQLDatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ElectionInfoControllor {
    @FXML
    private Button Cancel;

    @FXML
    private Button Removev;

    @FXML
    private TableColumn<CandidateModule,String> cname;

    @FXML
    private TableColumn<CandidateModule, Integer> cnbofvotes;

    @FXML
    private Text eid;

    @FXML
    private Text ename;

    @FXML
    private Text estime;

    @FXML
    private TableView<CandidateModule> restable;

    @FXML
    private Text tename;

    ObservableList<CandidateModule> ElectionInfoObservableList= FXCollections.observableArrayList();



    private ElectionModel E;
    public ElectionInfoControllor(ElectionModel rowData) {
        E=rowData;
    }
    @FXML
    void initialize(){
        ///System.out.println(E);
        eid.setText(String.valueOf(E.getEid()));
        ename.setText(E.getEname());
        String newdate = new String();
        for(int i=0;i<E.getSdate().length();i++){
            if(E.getSdate().charAt(i)==' '){
                break;
            }
            newdate+= E.getSdate().charAt(i);
        }
        System.out.println(newdate);
        estime.setText(newdate);
        String newdate2 = new String();
        for(int i=0;i<E.getEdate().length();i++){
            if(E.getEdate().charAt(i)==' '){
                break;
            }
            newdate2+= E.getEdate().charAt(i);
        }
        tename.setText(newdate2);


        Removev.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 ) {

                SQLDatabaseConnection conectnow=new SQLDatabaseConnection();
                Connection connectDB=conectnow.getConnection();


                try {
                    Statement statment = connectDB.createStatement();
                    ResultSet resultSet = null;
                    String Statment="exec deleteElection '"+E.getEid()+"'";
                    statment.executeQuery(Statment);

                    System.out.println("done");
                } catch (SQLException e) {
                }
                Stage stage= (Stage) Removev.getScene().getWindow();
                stage.close();
            }
        });

        SQLDatabaseConnection conectnow=new SQLDatabaseConnection();
        Connection connectDB=conectnow.getConnection();
        String Statment="select CNAME,NBOFVOTES From CANDIDATE where CELECTION = '"+E.getEid()+"'";
        try {
            Statement statment2 = connectDB.createStatement();
            ResultSet queryoutput = statment2.executeQuery(Statment);
            while (queryoutput.next()) {
                String EName = queryoutput.getString("CNAME");
                Integer nbofvotes = queryoutput.getInt("NBOFVOTES");
              ///Integer id, String Cname, String CEmail, Integer Cage, Integer eid
                ElectionInfoObservableList.add(new CandidateModule(null, EName,null, null, null,nbofvotes));
            }
            cname.setCellValueFactory(new PropertyValueFactory<>("Cname"));
            cnbofvotes.setCellValueFactory(new PropertyValueFactory<>("numberOfVotes"));


            restable.setItems(ElectionInfoObservableList);


            } catch (SQLException e) {
            e.printStackTrace();
        }

        Cancel.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 ) {

                Stage stage= (Stage) Cancel.getScene().getWindow();
                stage.close();
            }
        });


    }
}

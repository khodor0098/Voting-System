package Vote;

import Candidate.CandidateModule;
import Database.SQLDatabaseConnection;
import Election.ElectionInfoControllor;
import Election.ElectionModel;
import Voter.Votermodel;
import com.example.mainvoting.Main;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class VotingController {
    Votermodel vm;


    @FXML
    private Button Cancel;

    @FXML
    private Button Removev;

    @FXML
    private TableColumn<CandidateModule, Integer> VotingAge;

    @FXML
    private TableColumn<CandidateModule, String> VotingName;

    @FXML
    private TableView<CandidateModule> VotingTable;
    @FXML
    private TableColumn<Button, Button> votebtn;

    @FXML
    private Text tage;

    @FXML
    private Text teid;

    @FXML
    private Text tid;

    @FXML
    private Text tname;

    ObservableList<CandidateModule> Candiatemodelobservable=FXCollections.observableArrayList();
    public VotingController(Votermodel v) {
        vm = v ;
    }
    @FXML
    void initialize(){

        tname.setText(vm.getVname());
        tage.setText(String.valueOf(vm.getVage()));
        tid.setText(String.valueOf(vm.getId()));
        teid.setText(String.valueOf(vm.getEid()));
        getcandidate(vm.getEid());
    }

    private void getcandidate(Integer eid) {
        SQLDatabaseConnection connection=new SQLDatabaseConnection();
        Connection conectnow=connection.getConnection();
        Integer Cid;
        String statment= "select CID,CNAME,CAGE from CANDIDATE where CELECTION='"+eid+"'";
        try{
            Statement statment12=conectnow.createStatement();
            ResultSet queryoutput=statment12.executeQuery(statment);

            while (queryoutput.next()) {
                 Cid = queryoutput.getInt("CID");
                String name = queryoutput.getString("CNAME");
                Integer age = queryoutput.getInt("CAGE");
                ///Integer id, String Cname, String CEmail, Integer Cage, Integer eid
                Candiatemodelobservable.add(new CandidateModule(Cid, name, null, age, eid,null));
            }
            VotingAge.setCellValueFactory(new PropertyValueFactory<>("Cage"));
            VotingName.setCellValueFactory(new PropertyValueFactory<>("Cname"));
            VotingTable.setItems(Candiatemodelobservable);

        } catch (Exception e) {
            e.printStackTrace();
        }

        VotingTable.setRowFactory(Election->{
            TableRow<CandidateModule> row = new TableRow<>();
            row.setOnMouseClicked(event -> {

                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    CandidateModule rowData = row.getItem();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Alert!");
                    alert.setContentText("Are you sure you want to Vote for "+rowData.getCname()+"");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isEmpty()) {
                        System.out.println("Alert closed");
                    } else if (result.get() == ButtonType.OK) {

                        //System.out.println(rowData.getId());

                        String statment1= "exec newcastvote '"+vm.getId()+"','"+rowData.getId()+"','"+eid+"'";
                        try{
                            Statement statment12=conectnow.createStatement();
                            ResultSet resultSet = null;
                            statment12.executeQuery(statment1);


                        } catch (Exception e) {
                        }
                        Stage stage= (Stage) row.getScene().getWindow();
                        stage.close();

                    } else if (result.get() == ButtonType.CANCEL) {
                    }
                }

            });
            return row;
        });

        Cancel.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 ) {

                Stage stage= (Stage) Cancel.getScene().getWindow();
                stage.close();
            }
        });
    }


}

package Candidate;

import Database.SQLDatabaseConnection;
import Voter.VoterInfoControler;
import Voter.Votermodel;
import com.example.mainvoting.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CandidateController implements Initializable {


    @FXML
    private TableView<CandidateModule> CandidateTable;

    @FXML
    private Button Refresh;

    @FXML
    private Button addCandidate;

    @FXML
    private TableColumn<CandidateModule, Integer> cage;

    @FXML
    private TableColumn<CandidateModule, String> cemail;

    @FXML
    private TableColumn<CandidateModule, Integer> cid;

    @FXML
    private TableColumn<CandidateModule, String> cname;

    @FXML
    private TableColumn<CandidateModule, Integer> eid;

    @FXML
    private TextField search;



    ObservableList<CandidateModule> CandidateModulesobservablelist= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource){
        SQLDatabaseConnection conectnow=new SQLDatabaseConnection();
        Connection connectDB=conectnow.getConnection();
        String Statment="exec getallCandidates";
        try {
            Statement statment=connectDB.createStatement();
            ResultSet queryoutput=statment.executeQuery(Statment);
            while(queryoutput.next()) {
                ///CID,CNAME,CEMAIL,CAGE,CELECTION
                /// VID,Vname,VEMAIL,VAGE,VELECTION
                Integer CandidateId=queryoutput.getInt("CID");
                String CandidateName=queryoutput.getString("CNAME");
                String CandidateEmail=queryoutput.getString("CEMAIL");
                Integer Candidateage=queryoutput.getInt("CAGE");
                Integer CELECTION=queryoutput.getInt("CELECTION");
                /// System.out.println(VoterId+" +"+VoterName);
                CandidateModulesobservablelist.add(new CandidateModule(CandidateId,CandidateName,CandidateEmail,Candidateage,CELECTION,null));
            }
            cid.setCellValueFactory(new PropertyValueFactory<>("id"));
            cname.setCellValueFactory(new PropertyValueFactory<>("Cname"));
            cage.setCellValueFactory(new PropertyValueFactory<>("Cage"));
            eid.setCellValueFactory(new PropertyValueFactory<>("eid"));
            cemail.setCellValueFactory(new PropertyValueFactory<>("CEmail"));

            CandidateTable.setItems(CandidateModulesobservablelist);

          FilteredList<CandidateModule> filterdata=new FilteredList<>(CandidateModulesobservablelist, b-> true);

            search.textProperty().addListener((observable, oldValue, newValue) -> {
                filterdata.setPredicate(Candidatemodel->{

                    if(newValue.isEmpty() ||newValue.isBlank() || newValue==null){
                        return true;
                    }
                    String sreachKey=newValue.toLowerCase();
                    if(Candidatemodel.getCname().toLowerCase().indexOf(sreachKey) > -1){
                        return true;
                    }
                    if(Candidatemodel.getId().toString().indexOf(sreachKey) > -1){
                        return true;
                    }
                    if(Candidatemodel .getEid().toString().indexOf(sreachKey) > -1){
                        return true;
                    }
                    else {
                        return false;
                    }
                });
            });
            SortedList<CandidateModule> soretdata=new SortedList<>(filterdata);
            soretdata.comparatorProperty().bind(CandidateTable.comparatorProperty());
            CandidateTable.setItems(soretdata);
            CandidateTable.setRowFactory(invoices->{
                TableRow<CandidateModule> row = new TableRow<>();
                row.setOnMouseClicked(event -> {

                    if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                      CandidateModule rowData = row.getItem();
                        try {
                            Stage window =new Stage();
                            window.initModality(Modality.APPLICATION_MODAL);
                            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CandidateInfo.fxml"));
                            Candidateinfocontroller ordercontrol = new Candidateinfocontroller (rowData);
                            fxmlLoader.setController(ordercontrol);
                            Scene scene = new Scene(fxmlLoader.load(), 600, 350);
                            window.initStyle(StageStyle.UNDECORATED);
                            window.setScene(scene);
                            window.showAndWait();
                            ///System.out.println(rowData);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return row ;
            });

            Refresh.setOnMouseClicked(event ->{
                if (event.getClickCount() == 1 ){
                    CandidateModulesobservablelist=FXCollections.observableArrayList();
                    initialize(url,resource);
                }

            } );

            addCandidate.setOnMouseClicked(event -> {

                if (event.getClickCount() == 1 ) {

                    try {
                        Stage window =new Stage();
                        window.initModality(Modality.APPLICATION_MODAL);
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addCandidate.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 350);
                        window.initStyle(StageStyle.UNDECORATED);
                        window.setScene(scene);
                        window.showAndWait();
                        ///System.out.println(rowData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });


        }
        catch (Exception e) {
            Logger.getLogger(ModuleLayer.Controller.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }

    }


}



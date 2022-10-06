package Election;

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


public class ElectionController implements Initializable {

    @FXML
    private TableColumn<ElectionModel, Integer> Eid;

    @FXML
    private TableView<ElectionModel> ElectionTable;

    @FXML
    private TableColumn<ElectionModel, String> Ename;

    @FXML
    private
    TableColumn<ElectionModel, String> Etime;

    @FXML
    private Button Refresh;

    @FXML
    private TableColumn<ElectionModel, String> Stime;

    @FXML
    private Button addElection;

    @FXML
    private TableColumn<ElectionModel, String> oid;

    @FXML
    private TextField search;

    ObservableList<ElectionModel> Electionobservablelist= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource){
        SQLDatabaseConnection conectnow=new SQLDatabaseConnection();
        Connection connectDB=conectnow.getConnection();
        String Statment="exec getallElection";
        try {
            Statement statment=connectDB.createStatement();
            ResultSet queryoutput=statment.executeQuery(Statment);
            while(queryoutput.next()) {
                Integer EId=queryoutput.getInt("EID");
                Integer Oid=queryoutput.getInt("EOWNER");
                String EName=queryoutput.getString("ENAME");
                String Etime=queryoutput.getString("EETIME");
                String Stime=queryoutput.getString("ESTIME");



               Electionobservablelist.add(new ElectionModel(EId,Oid,EName,Stime,Etime));
            }
            Eid.setCellValueFactory(new PropertyValueFactory<>("Eid"));
            oid.setCellValueFactory(new PropertyValueFactory<>("Oid"));
            Ename.setCellValueFactory(new PropertyValueFactory<>("ename"));
            Etime.setCellValueFactory(new PropertyValueFactory<>("edate"));
            Stime.setCellValueFactory(new PropertyValueFactory<>("sdate"));

            ElectionTable.setItems(Electionobservablelist);

            FilteredList<ElectionModel> filterdata=new FilteredList<>(Electionobservablelist, b-> true);

            search.textProperty().addListener((observable, oldValue, newValue) -> {
                filterdata.setPredicate(Votermodel ->{

                    if(newValue.isEmpty() ||newValue.isBlank() || newValue==null){
                        return true;
                    }
                    String sreachKey=newValue.toLowerCase();
                    if(Votermodel.getEname().toLowerCase().indexOf(sreachKey) > -1){
                        return true;
                    }
                    if(Votermodel.getEid().toString().indexOf(sreachKey) > -1){
                        return true;
                    }
                    if(Votermodel.getOid().toString().indexOf(sreachKey) > -1){
                        return true;
                    }
                    else {
                        return false;
                    }
                });
            });
            SortedList<ElectionModel> soretdata=new SortedList<>(filterdata);
            soretdata.comparatorProperty().bind(ElectionTable.comparatorProperty());
            ElectionTable.setItems(soretdata);
            ElectionTable.setRowFactory(Election->{
                TableRow<ElectionModel> row = new TableRow<>();
                row.setOnMouseClicked(event -> {

                    if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                        ElectionModel rowData = row.getItem();
                        try {
                            Stage window =new Stage();
                            window.initModality(Modality.APPLICATION_MODAL);
                            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ElectionInfo.fxml"));
                            ElectionInfoControllor infoc = new ElectionInfoControllor(rowData);
                            fxmlLoader.setController(infoc);
                            Scene scene = new Scene(fxmlLoader.load(), 600,635 );
                            window.initStyle(StageStyle.UNDECORATED);
                            window.setScene(scene);
                            //window.show();
                           window.showAndWait();
                            //System.out.println(rowData);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return row;
            });

            Refresh.setOnMouseClicked(event ->{
                if (event.getClickCount() == 1 ){
                    Electionobservablelist=FXCollections.observableArrayList();
                    initialize(url,resource);
                }

            } );

          addElection.setOnMouseClicked(event -> {

                if (event.getClickCount() == 1 ) {

                    try {
                        Stage window =new Stage();
                        window.initModality(Modality.APPLICATION_MODAL);
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newElection.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 600, 350);
                        window.initStyle(StageStyle.UNDECORATED);
                        window.setScene(scene);
                        window.showAndWait();
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

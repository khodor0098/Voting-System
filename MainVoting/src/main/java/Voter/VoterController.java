package Voter;

import Database.SQLDatabaseConnection;
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

public class VoterController implements Initializable {

    @FXML
    private TableView<Votermodel> VoterTable;

    @FXML
    private Button addvoter;

    @FXML
    private TextField search;

    @FXML
    private TableColumn<Votermodel,Integer> eid;

    @FXML
    private TableColumn<Votermodel, Integer> vage;

    @FXML
    private TableColumn<Votermodel, String> vemail;

    @FXML
    private TableColumn<Votermodel, Integer> vid;

    @FXML
    private TableColumn<Votermodel, String> vname;

    @FXML
    private Button Refresh;

    ObservableList<Votermodel> VoterModelsobservablelist= FXCollections.observableArrayList();

     @Override
    public void initialize(URL url, ResourceBundle resource){
        SQLDatabaseConnection conectnow=new SQLDatabaseConnection();
        Connection connectDB=conectnow.getConnection();
        String Statment="exec getallvoters";
        try {
            Statement statment=connectDB.createStatement();
            ResultSet queryoutput=statment.executeQuery(Statment);
            while(queryoutput.next()) {
               /// VID,Vname,VEMAIL,VAGE,VELECTION
                Integer VoterId=queryoutput.getInt("VID");
                String VoterName=queryoutput.getString("Vname");
                String voterEmail=queryoutput.getString("VEMAIL");
                Integer Voterage=queryoutput.getInt("VAGE");
                Integer VELECTION=queryoutput.getInt("VELECTION");
               /// System.out.println(VoterId+" +"+VoterName);
                VoterModelsobservablelist.add(new Votermodel(VoterId,VoterName,voterEmail,Voterage,VELECTION));
            }
            vid.setCellValueFactory(new PropertyValueFactory<>("id"));
            vname.setCellValueFactory(new PropertyValueFactory<>("vname"));
            vage.setCellValueFactory(new PropertyValueFactory<>("vage"));
            eid.setCellValueFactory(new PropertyValueFactory<>("eid"));
            vemail.setCellValueFactory(new PropertyValueFactory<>("vEmail"));

            VoterTable.setItems(VoterModelsobservablelist);

            FilteredList<Votermodel> filterdata=new FilteredList<>(VoterModelsobservablelist, b-> true);

            search.textProperty().addListener((observable, oldValue, newValue) -> {
                filterdata.setPredicate(Votermodel ->{

                    if(newValue.isEmpty() ||newValue.isBlank() || newValue==null){
                        return true;
                    }
                    String sreachKey=newValue.toLowerCase();
                    if(Votermodel.getVname().toLowerCase().indexOf(sreachKey) > -1){
                        return true;
                    }
                    if(Votermodel .getId().toString().indexOf(sreachKey) > -1){
                        return true;
                    }
                    if(Votermodel .getEid().toString().indexOf(sreachKey) > -1){
                        return true;
                    }
                    else {
                        return false;
                    }
                });
            });
            SortedList<Votermodel> soretdata=new SortedList<>(filterdata);
            soretdata.comparatorProperty().bind(VoterTable.comparatorProperty());
            VoterTable.setItems(soretdata);
           VoterTable.setRowFactory(invoices->{
            TableRow<Votermodel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {

                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                   Votermodel rowData = row.getItem();
                   try {
                        Stage window =new Stage();
                        window.initModality(Modality.APPLICATION_MODAL);
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("VoterInfo.fxml"));
                        VoterInfoControler ordercontrol = new VoterInfoControler (rowData);
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
                    VoterModelsobservablelist=FXCollections.observableArrayList();
                    initialize(url,resource);
                }

            } );

                addvoter.setOnMouseClicked(event -> {

                    if (event.getClickCount() == 1 ) {

                        try {
                            Stage window =new Stage();
                            window.initModality(Modality.APPLICATION_MODAL);
                            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addVoter.fxml"));
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

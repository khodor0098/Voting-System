package Vote;

import Database.SQLDatabaseConnection;
import Voter.VoterInfoControler;
import Voter.Votermodel;
import com.example.mainvoting.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VoteLogin {

    @FXML
    private TextField emailfield;

    @FXML
    private Button enter;

    @FXML
    private PasswordField passwordfield;
    @FXML
    private Text loginmessagelable;
    private LocalDateTime Date;


    public void loginbuttonaction(ActionEvent e) {
        String email = emailfield.getText();
        String pass = passwordfield.getText();
        if (emailfield.getText().isEmpty()) {
            loginmessagelable.setText("Please Enter A Email");
        } else if (passwordfield.getText().isEmpty()) {
            loginmessagelable.setText("Please Enter Password");
        } else {
            validlogin(email, pass);
        }
    }

    private void validlogin(String email, String pass) {
        Votermodel v = null;
        Integer castcheck = null;
        SQLDatabaseConnection conectnow = new SQLDatabaseConnection();
        Connection connectDB = conectnow.getConnection();
        String sql = "SELECT VID,VNAME,VEMAIL,VAGE,VELECTION,VPASSWORD FROM VOTER WHERE VEMAIL=? AND VPASSWORD=?";
        try {

            Statement stmt = connectDB.createStatement();

            PreparedStatement preparedStatement = connectDB.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, pass);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                v = new Votermodel(resultSet.getInt("VID"), resultSet.getString("VNAME"), resultSet.getString("VEMAIL"), resultSet.getInt("VAGE"), resultSet.getInt("VELECTION"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (v != null) {
            String sql2 = "Select VOTER from CASTVOTEFOR where VOTER='" + v.getId() + "'";
            try {

                Statement stmt = connectDB.createStatement();

                PreparedStatement preparedStatement = connectDB.prepareStatement(sql2);
                ResultSet resultSet1 = preparedStatement.executeQuery();

                if (resultSet1.next()) {

                    castcheck = resultSet1.getInt("VOTER");
                }
                stmt.close();
                connectDB.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (v == null) {
                loginmessagelable.setText("wrong email or Password");
            }

            if (castcheck != null) {
                loginmessagelable.setText("You have been Voted before");
            }

            if (v != null && castcheck == null) {
                loginmessagelable.setText("");
                String end = null;
                String start = null;
                SQLDatabaseConnection conectnow1 = new SQLDatabaseConnection();
                Connection connectDB1 = conectnow1.getConnection();
                String sql1 = "select ESTIME,EETIME from ELECTION where EID ='" + v.getEid() + "'";
                try {

                    Statement stmt = connectDB1.createStatement();
                    PreparedStatement preparedStatement = connectDB1.prepareStatement(sql1);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {

                        start = resultSet.getString("ESTIME");
                        end = resultSet.getString("EETIME");
                    }
                    stmt.close();
                    connectDB.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String newstartdate = new String();
                for (int i = 0; i < start.length(); i++) {
                    if (start.charAt(i) == ' ') {
                        break;
                    }
                    newstartdate += start.charAt(i);
                }

                System.out.println(newstartdate);
                String newEnddate = new String();
                for (int i = 0; i < end.length(); i++) {
                    if (end.charAt(i) == ' ') {
                        break;
                    }
                    newEnddate += end.charAt(i);
                }

                System.out.println(newEnddate);

                Date = LocalDateTime.now();
                DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("YYYY-MM-dd");
                System.out.println(dataFormat.format(Date));
                if (newstartdate.equals(dataFormat.format(Date))) {
                    try {
                        Stage window = new Stage();
                        window.initModality(Modality.APPLICATION_MODAL);
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("TheVotingpage.fxml"));
                        VotingController ordercontrol = new VotingController(v);
                        fxmlLoader.setController(ordercontrol);
                        Scene scene = new Scene(fxmlLoader.load(), 790, 644);
                        window.initStyle(StageStyle.UNDECORATED);
                        window.setScene(scene);
                        window.showAndWait();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    loginmessagelable.setText("The Election is Not Available Now");
                }
            }
            return;
        }

    }
}

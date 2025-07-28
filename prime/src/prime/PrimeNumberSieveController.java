package prime;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrimeNumberSieveController implements Initializable {

    @FXML
    private TableView<numberdata> table;

    @FXML
    private TableColumn<numberdata, String> IDColumn;

    @FXML
    private TableColumn<numberdata, String> NUMBERColumn;

    @FXML
    private TableColumn<numberdata, String> PRIME_NUMBERColumn;

    @FXML
    private Button btnadd;


    @FXML
    private Button calculateButton;

    @FXML
    private TextField inputField;

    @FXML
    private TextField outputArea;

    @FXML
    private TextField txtPRIME_NUMBER;

    @FXML
    private AnchorPane mainPanel;



    private Connection con;
    private PreparedStatement pst;
    private int myIndex;
    private int ID;

    @FXML
    void add(ActionEvent event) {
        String NUMBER = inputField.getText();
        String PRIME_NUMBER = outputArea.getText();

        try {
            pst = con.prepareStatement("INSERT INTO numberlog(NUMBER, PRIME_NUMBER) VALUES(?, ?)");
            pst.setString(1, NUMBER);
            pst.setString(2, PRIME_NUMBER);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SIEVE OF ERATOSTHENES");
            alert.setHeaderText("");
            alert.setContentText("Record Added!");
            alert.showAndWait();

            table();
            txtPRIME_NUMBER.setText("");
            outputArea.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(PrimeNumberSieveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void table() {
        ObservableList<numberdata> numberdataList = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("SELECT ID, NUMBER, PRIME_NUMBER FROM numberlog");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                numberdata data = new numberdata();
                data.setID(rs.getString("ID"));
                data.setNUMBER(rs.getString("NUMBER"));
                data.setPRIME_NUMBER(rs.getString("PRIME_NUMBER"));
                numberdataList.add(data);
            }
            table.setItems(numberdataList);
            IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            NUMBERColumn.setCellValueFactory(new PropertyValueFactory<>("NUMBER"));
            PRIME_NUMBERColumn.setCellValueFactory(new PropertyValueFactory<>("PRIME_NUMBER"));
        } catch (SQLException ex) {
            Logger.getLogger(PrimeNumberSieveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/primedatabase?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Error connecting to database: " + ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connect();
        table();
    }

    @FXML
    void handleCalculateButton(ActionEvent event) {
        String inputText = inputField.getText();
        try {
            int n = Integer.parseInt(inputText);
            SieveOfEratosthenes sieve = new SieveOfEratosthenes();
            StringBuilder outputBuilder = new StringBuilder();
            sieve.sieveOfEratosthenes(n, outputBuilder);
            outputArea.setText(outputBuilder.toString());
        } catch (NumberFormatException ex) {
            outputArea.setText("Invalid input. Please enter an integer.");
        }
    }
}
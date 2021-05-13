package sample;

//import Add.Add;
import Databases.Databases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;

public class Controller implements Initializable {
    ObservableList<Service> list= FXCollections.observableArrayList();

    @FXML
    private TableColumn<Service, String> nService;

    @FXML
    private TableColumn<Service, String> nom;

    @FXML
    private TableColumn<Service, String> emplacement;


    static boolean isChanged;



    @FXML
    private TextField serviceToSerchFor;

    @FXML
    TableView<Service> tableview;





    @FXML
    void add() throws IOException, SQLException, ClassNotFoundException, InterruptedException {

      Parent parent= FXMLLoader.load(getClass().getResource("Add.fxml"));
        //FXMLLoader loader =FXMLLoader.load(getClass().getResource("Add.fxml"));
        //Parent parent= FXMLLoader.load(getClass().getResource("Test.fxml"));
        Stage stage=new Stage(StageStyle.DECORATED);
        stage.setTitle("Ajouter un service ");
        stage.setScene(new Scene(parent));
        //Add add = loader.getController();
        //add.setnService(add.generate());
        //isChanged = false;
        //check(isChanged);
        stage.show();


    }
    public boolean check(boolean isChanged) throws SQLException, ClassNotFoundException, InterruptedException {
        while (true) {
            if(isChanged){
                reloadTable();
                isChanged=false;

            }
            System.out.println("this is fuked up");
          //  sleep(250);
        }
    }
    @FXML
    void delete() throws ClassNotFoundException, SQLException {
// TODO: 5/4/21 fahamni hadi 3lah
        Databases databases = new Databases();
        Service selectedForDeletion=tableview.getSelectionModel().getSelectedItem();


        if(selectedForDeletion==null)
        {

            Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setHeaderText(null);
            alert3.setContentText("veuillez sélectionner un service");
            alert3.showAndWait();
            return;

        }




        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suprime service");
        alert.setContentText("voulez-vous vraiment supprimer ce service "+selectedForDeletion.getNom()+"?");
        Optional<ButtonType> answer=alert.showAndWait();

        if(answer.get()==ButtonType.OK)
        {
            //todo
            String req="delete from Services where nService='"+selectedForDeletion.getNService()+"';";
            if(databases.execAction(req))
            {
                Databases.statement.executeUpdate(req);
                alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succus");
                alert.setHeaderText(null);
                alert.setContentText("Le Service a été supprimé");
                alert.showAndWait();
                list.remove(selectedForDeletion);
            }



        }
        else{
            Alert alert2;
            alert2=new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Cancelled");
            alert2.setHeaderText(null);
            alert2.setContentText("Delete Operation Cancelled");
            alert2.showAndWait();
        }
    }

    @FXML
    void edit() throws IOException {
//todo hadi li tsilictioni la line w tasti lokan silictionit
        Service selectedForEdit = tableview.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {

            Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
            alert3.setHeaderText(null);
            alert3.setContentText("Veuillez sélectionner un service");
            alert3.showAndWait();
            System.out.println(selectedForEdit);
            return;


        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Add.fxml"));
            Parent parent = loader.load();
            Add addMembre = (Add) loader.getController();
            //System.out.println(selectedForEdit.getNService()+"----------------");
            addMembre.inflate(selectedForEdit);
            Stage stage = new Stage(StageStyle.DECORATED);

            stage.setTitle("modifier le service");
            stage.setScene(new Scene(parent));
            stage.show();


           /* stage.setOnCloseRequest((e) ->
            {
                //refresh(new ActionEvent());
                try {
                    reloadTable();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

            }

            );*/


        } finally {

        }

    }




    void initCol()
    {
        nService.setCellValueFactory(new PropertyValueFactory<>("nService"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        emplacement.setCellValueFactory(new PropertyValueFactory<>("emplacement"));

    }
    @FXML
    void  loadData() throws SQLException, ClassNotFoundException {
        String s = null;
        serviceToSerchFor.setText(s);//-------------------------------------------
        System.out.println("refrech");
        list.clear();
        Databases databases =new Databases();
        int i = 0;
        String requete="SELECT * FROM Services";
        ResultSet rs= databases.execQuery(requete);
        while (rs.next())
        {
            String nService=rs.getString("nService");

            String nom=rs.getString("nom");

            String emplacement=rs.getString("emplacement");

            System.out.println("Seatues: "+ list.add(new Service(nService,nom,emplacement)));
            System.out.print("  "+list.get(i).nService);
            System.out.print("  "+list.get(i).nom);
            System.out.print("  "+list.get(i).emplacement);
            System.out.println("");
            i++;
        }


        tableview.setItems(list);



        System.out.println(list.isEmpty());
    }
    @FXML
    void reloadTable()throws SQLException, ClassNotFoundException{
        String serviceToSerch = serviceToSerchFor.getText();
        list.clear();
        Databases databases =new Databases();

        String requete="SELECT * FROM Services WHERE nom LIKE '%"+serviceToSerch+"%' OR emplacement LIKE '%"+serviceToSerch+"%' ;";

        ResultSet rs= databases.execQuery(requete);
        while (rs.next())
        {
            String nService=rs.getString("nService");
            String nom=rs.getString("nom");
            String emplacement=rs.getString("emplacement");

            list.add(new Service(nService,nom,emplacement));
        }

        tableview.setItems(list);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            initCol();
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


}

package sample;

import Databases.Databases;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

//import static Databases.Databases.insert;
//import static Databases.Databases.upadate;

public class Add  {

    @FXML
    private TextField nService;

    @FXML
    private TextField nom;

    public void setnService(String nService) {
        this.nService.setText(nService);
    }
    @FXML
    private TextField emplacement;

    @FXML
    private Button save;

    @FXML
    private Button cancel;

    Boolean isInEdit=Boolean.FALSE;




    Databases databases = new Databases();

    public Add() throws SQLException, ClassNotFoundException {
    }

    /* public Add() throws SQLException, ClassNotFoundException {
     }
 */
    @FXML
    void save(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        String nServiceText= nService.getText();
        String nomText=nom.getText();
        String emplacementText=emplacement.getText();

        //if(nService.toString().isEmpty()|| nomText.isEmpty() || emplacementText.isEmpty())
        if( nServiceText.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("un numero d'identifiant vous sera suggéré");
            nService.setText(generate());
            alert.showAndWait();
            return;
        }

        if(isInEdit)
        {
           // nService.setEditable(false);
            editOperation();
            return;
        }
        else
        {   try {
            //String req = "insert into etu values ('"+nService+"', '"+nomText+"', '"+emplacementText+"');";
            String req="INSERT INTO Services (nService,nom,emplacement) VALUES ( '"+nServiceText+"','"+nomText+"', '"+emplacementText+"');";

            databases.insert(req);
            cancel();

        }
            catch (SQLException e){
                System.out.println(e.toString());
                Alert alert2=new Alert(Alert.AlertType.ERROR);
                alert2.setHeaderText(null);
                alert2.setContentText("chonge le numero de service cvp");
                alert2.showAndWait();
            }

        }

       // reloadTable();
        Controller.isChanged=true;

    }



//todo adbaka fahemni kirah yesra hna

    public void inflate(Service service)
    {
        System.out.println(service.getNService());

        nService.setText(String.valueOf(service.getNService()));
        nom.setText(service.getNom());
        emplacement.setText(service.getEmplacement());
        isInEdit=Boolean.TRUE;
        //nService.setEditable(false);

    }
    public String generate(){
        String input=nom.getText()+emplacement.getText();
        int int4 = input.hashCode()%10000;
        int int3 = input.hashCode()%1000;
        int int2 = input.hashCode()%100;

        String result = null;
                result= int2+"-"+int3+"-"+int4;
        return result;
    }

    @FXML
    public void editOperation() throws SQLException, IOException, ClassNotFoundException {
        //Service edit = new Service(nService.getText(),nom.getText(),emplacement.getText());
        System.out.println("nservice: "+nService.getText()+"nom: "+nom.getText()+"emplacement: "+emplacement.getText());
        Service edit = new Service(nService.getText(),nom.getText(),emplacement.getText());
        if(databases.upadate(edit))
        {

            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Updated Service ");
            alert.setHeaderText(null);
            alert.showAndWait();
            Controller.isChanged=true;
            cancel();

        }
        else
        {
            Alert alert2=new Alert(Alert.AlertType.ERROR);
            alert2.setHeaderText(null);
            alert2.setContentText("Failed Upadate");
            alert2.showAndWait();

        }
    }







    @FXML
    public void cancel() throws IOException, SQLException, ClassNotFoundException {
        // get a handle to the stage
        Stage stage = (Stage) cancel.getScene().getWindow();
        // do what you have to do
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent parent = loader.load();
        Controller controller = (Controller) loader.getController();
        controller.reloadTable();

    }



}

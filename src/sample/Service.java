package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;

public class Service{
    // nService  nom emplacement
    SimpleStringProperty nService;
    SimpleStringProperty nom ;
    SimpleStringProperty emplacement;
//----------------------??????????????????????????
    public Service(String nService, String nom, String emplacement) {
            //Integer. parseInt()
            this.nService = new SimpleStringProperty(nService);
            this.nom = new SimpleStringProperty(nom);
            this.emplacement = new SimpleStringProperty(emplacement);

        }


/*
    public Service(TableColumn<Service, Integer> nService, String nom, String emplacement) {
        this.nService = new SimpleIntegerProperty(( nServic.);
        this.nom = new SimpleStringProperty(nom);
        this.emplacement = new SimpleStringProperty(emplacement);
    }*/

    public Service(String nom, String emplacement) {
        this.nom = new SimpleStringProperty(nom);
        this.emplacement = new SimpleStringProperty(emplacement);

    }



    public String getId() {
        return nService.get();
    }

    public SimpleStringProperty nServiceProperty() {
        return nService;
    }

    public Service(SimpleStringProperty nom, SimpleStringProperty emplacement) {
        this.nom = nom;
        this.emplacement = emplacement;
    }




/*
    public String getNom() {
        return nom.get();
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public SimpleStringProperty emplacementProperty() {
        return emplacement;
    }
    */


    public String getNService() {
        return nService.get();
    }

    public void setNService(String nService) {
        this.nService.set(nService);
    }

    public String getNom() {
        return nom.get();
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getEmplacement() {
        return emplacement.get();
    }

    public SimpleStringProperty emplacementProperty() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement.set(emplacement);
    }
}

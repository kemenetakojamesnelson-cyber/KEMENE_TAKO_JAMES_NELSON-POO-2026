package gsm.model;

public class Appel {

    private String numeroAppelant;
    private String nomAppelant;
    private String duree;   //  harmonisé
    private String dateHeure;

    public Appel(String numeroAppelant, String nomAppelant,
                 String duree, String dateHeure) {

        this.numeroAppelant = numeroAppelant;
        this.nomAppelant = nomAppelant;
        this.duree = duree;
        this.dateHeure = dateHeure;
    }

    public String getNumeroAppelant() {
        return numeroAppelant;
    }

    public String getNomAppelant() {
        return nomAppelant;
    }

    public String getDuree() {
        return duree;
    }

    public String getDateHeure() {
        return dateHeure;
    }

    public void afficher() {
        System.out.println("Appel de : " + nomAppelant +
                " (" + numeroAppelant + ")" +
                " | Durée : " + duree +
                " | Date : " + dateHeure);
    }
}
package gsm.model;

public class Appel {

    private String numeroAppelant;
    private String nomAppelant;
    private String duree;
    private String dateHeure;

    public Appel(String numeroAppelant,
                 String nomAppelant,
                 String duree,
                 String dateHeure) {

        this.numeroAppelant = numeroAppelant;
        this.nomAppelant = nomAppelant;
        this.duree = duree;
        this.dateHeure = dateHeure;
    }

    public void afficher() {

        System.out.println(
                "Appel de : " + nomAppelant +
                " (" + numeroAppelant + ")" +
                " | Durée : " + duree +
                " | Date : " + dateHeure
        );
    }
}
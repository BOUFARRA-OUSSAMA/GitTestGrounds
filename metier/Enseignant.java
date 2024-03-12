/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

/**
 *
 * @author Studio
 */
public class Enseignant {
    private int idEn;
    private String nom;
    private Matiere mat;

    public Enseignant(int idEn, String nom, Matiere mat) {
        this.idEn = idEn;
        this.nom = nom;
        this.mat = mat;
    }

    public int getIdEn() {
        return idEn;
    }

    public void setIdEn(int idEn) {
        this.idEn = idEn;
    }

    public Matiere getMat() {
        return mat;
    }

    public void setMat(Matiere mat) {
        this.mat = mat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}

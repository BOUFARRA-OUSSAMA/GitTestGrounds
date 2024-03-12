/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

/**
 *
 * @author Studio
 */
public class Matiere {
    private int idMat;
    private String nomMat;
    private int coef;

    public Matiere(int idMat, String nomMat, int coef) {
        this.idMat = idMat;
        this.nomMat = nomMat;
        this.coef = coef;
    }

    public int getCoef() {
        return coef;
    }

    public void setCoef(int coef) {
        this.coef = coef;
    }

    public int getIdMat() {
        return idMat;
    }

    public void setIdMat(int idMat) {
        this.idMat = idMat;
    }

    public String getNomMat() {
        return nomMat;
    }

    public void setNomMat(String nomMat) {
        this.nomMat = nomMat;
    }

}

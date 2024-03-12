/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metier;

/**
 *
 * @author Studio
 */
public class Etudiant {
    private int num;
    private String nom;
    private int age;
    private String Classe;

    public Etudiant(int num, String nom, int age, String Classe) {
        this.num = num;
        this.nom = nom;
        this.age = age;
        this.Classe = Classe;
    }

    public String getClasse() {
        return Classe;
    }

    public void setClasse(String Classe) {
        this.Classe = Classe;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}

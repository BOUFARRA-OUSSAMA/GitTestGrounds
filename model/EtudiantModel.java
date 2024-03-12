/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import metier.Etudiant;

/**
 *
 * @author Studio
 */
public class EtudiantModel extends AbstractTableModel {

    Vector<Etudiant> ve=new Vector<Etudiant>();
    String[] titres={"Numero", "Nom","Age","Classe"};
    BddConnection bd=new BddConnection();

    public EtudiantModel() {
        ve=getEtudiants();
    }



    public int getRowCount() {
        return ve.size();
    }

    @Override
    public String getColumnName(int column) {
        return titres[column];
    }

    public int getColumnCount() {
        return titres.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Etudiant et=ve.get(rowIndex);
        switch(columnIndex){
            case 0: return et.getNum();
            case 1: return et.getNom();
            case 2: return et.getAge();
            case 3: return et.getClasse();
            default:return null;
        }
    }
    public void addEtudiant(Etudiant et){
        String s="insert into etudiant (numero, nom, age, classe) values ("+et.getNum()+",'"+et.getNom()+"',"+et.getAge()+",'"+et.getClasse()+"')";
        int nbre=bd.executerAction(s);
        if(nbre==1){
            ve.add(et);
            fireTableRowsInserted(ve.size()-1, ve.size()-1);
        }
    }
    public void updateEtudiant(Etudiant et, int indice){
        String s="update etudiant set nom='"+et.getNom()+"', age="+et.getAge()+", classe='"+et.getClasse()+"' where numero="+et.getNum();
        int nbre=bd.executerAction(s);
        if(nbre==1){
            ve.set(indice,et);
            fireTableDataChanged();
        }
    }
    public void deleteEtudiant(int indice){
        Etudiant et=ve.get(indice);
        String s="delete from etudiant  where numero="+et.getNum();
        int nbre=bd.executerAction(s);
        if(nbre==1){
            ve.removeElementAt(indice);
            fireTableRowsDeleted(indice, indice);
        }
    }
    public Vector<Etudiant> getEtudiants(){
        Vector<Etudiant> v=new Vector<Etudiant>();
        String s="select numero, nom, age, classe from etudiant";
        ResultSet rs=bd.executerSelect(s);
        try {
            while(rs.next()){
                int num=rs.getInt(1);
                String nom=rs.getString(2);
                int age=rs.getInt(3);
                String classe=rs.getString(4);
                Etudiant et=new Etudiant(num, nom, age, classe);
                v.add(et);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return v;
    }

}

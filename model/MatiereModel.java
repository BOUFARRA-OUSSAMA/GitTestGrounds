/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import metier.Etudiant;
import metier.Matiere;

/**
 *
 * @author Studio
 */
public class MatiereModel extends AbstractTableModel {

    Vector<Matiere> ve=new Vector<Matiere>();
    String[] titres={"IdMat", "Nom Matiere","Coef"};
    BddConnection bd=new BddConnection();

    public MatiereModel() {
        ve=getMatieres();
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
        Matiere et=ve.get(rowIndex);
        switch(columnIndex){
            case 0: return et.getIdMat();
            case 1: return et.getNomMat();
            case 2: return et.getCoef();
            default:return null;
        }
    }
    public void addMatiere(Matiere et){
        String s="insert into matiere (idmat, nommat, coef) values ("+et.getIdMat()+",'"+et.getNomMat()+"',"+et.getCoef()+")";
        int nbre=bd.executerAction(s);
        if(nbre==1){
            ve.add(et);
            fireTableRowsInserted(ve.size()-1, ve.size()-1);
        }
    }
    public void updateMatiere(Matiere et, int indice){
        String s="update matiere set nommat='"+et.getNomMat()+"', coef="+et.getCoef()+" where idmat="+et.getIdMat();
        int nbre=bd.executerAction(s);
        if(nbre==1){
            ve.set(indice,et);
            fireTableDataChanged();
        }
    }
    public void deleteMatiere(int indice){
        Matiere et=ve.get(indice);
        String s="delete from matiere  where idmat="+et.getIdMat();
        int nbre=bd.executerAction(s);
        if(nbre==1){
            ve.removeElementAt(indice);
            fireTableRowsDeleted(indice, indice);
        }
    }
    public Vector<Matiere> getMatieres(){
        Vector<Matiere> v=new Vector<Matiere>();
        String s="select idmat,nommat,coef from matiere";
        ResultSet rs=bd.executerSelect(s);
        try {
            while(rs.next()){
                int idmat=rs.getInt(1);
                String nommat=rs.getString(2);
                int coef=rs.getInt(3);
                Matiere m=new Matiere(idmat, nommat, coef);
                v.add(m);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return v;
    }}
                
    




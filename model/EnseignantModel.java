/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import metier.Enseignant;
import metier.Matiere;

/**
 *
 * @author Studio
 */
public class EnseignantModel extends AbstractTableModel {

    Vector<Enseignant> ve=new Vector<Enseignant>();
    String [] titres={"Id En", "Nom", "Matière"};
    BddConnection bd=new BddConnection();

    public EnseignantModel() {
        ve=getEnseignants();
    }

    @Override
    public String getColumnName(int column) {
        return titres[column];
    }


    public int getRowCount() {
        return ve.size();
    }

    public int getColumnCount() {
        return titres.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Enseignant en=ve.get(rowIndex);
        switch(columnIndex){
            case 0: return en.getIdEn();
            case 1: return en.getNom();
            case 2: return en.getMat().getNomMat();
            default:return null;
        }
    }
    public void addEnseignant(Enseignant en){
        String s="insert into enseignant (iden, nom, idmat) values("+en.getIdEn()+",'"+en.getNom()+"',"+en.getMat().getIdMat()+")";
        int nbre=bd.executerAction(s);
        if(nbre==1){
            ve.add(en);
            fireTableRowsInserted(ve.size()-1, ve.size()-1);
        }
    }
    public void updateEnseignant(Enseignant en, int indice){
        String s="update enseignant set nom='"+en.getNom()+"', idmat="+en.getMat().getIdMat()+" where iden="+en.getIdEn();
        int nbre=bd.executerAction(s);
        if(nbre==1){
            ve.set(indice, en);
            fireTableDataChanged();
        }
    }
    public void deleteEnseignant(int indice){
        Enseignant en=ve.get(indice);
        String s="delete from enseignant where iden="+en.getIdEn();
        int nbre=bd.executerAction(s);
        if(nbre==1){
            ve.removeElementAt(indice);
            fireTableRowsDeleted(indice, indice);
        }
    }
    public Vector<Enseignant> getEnseignants(){
        Vector<Enseignant> v=new Vector<Enseignant>();
        String s="select enseignant.iden,enseignant.nom, matiere.idmat, matiere.nommat, matiere.coef from enseignant, matiere where matiere.idmat=enseignant.idmat ";
        ResultSet rs=bd.executerSelect(s);
        try {
            while(rs.next()){
                int iden=rs.getInt(1);
                String nom=rs.getString(2);
                int idmat=rs.getInt(3);
                String nommat=rs.getString(4);
                int coef=rs.getInt(5);
                Matiere m=new Matiere(idmat, nommat, coef);
                Enseignant en=new Enseignant(iden, nom, m);
                v.add(en);
            }
        } catch (Exception e) {
        }
        return v;
    }

}

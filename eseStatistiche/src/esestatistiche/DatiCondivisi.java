/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esestatistiche;

import java.util.Vector;

/**
 *
 * @author saccani_federico
 */
public class DatiCondivisi {
    private Vector vett;
    private int daGenerare;
    private int puntiInseriti;
    private int spaziInseriti;
    
    public DatiCondivisi(int daGene){
        vett = new Vector<Character>(daGene);
        daGenerare=daGene;
    }
    
    public synchronized void push(Character val){
        vett.add(val);
    }
    
    public synchronized void addSpaziInseriti(){
        spaziInseriti++;
    }
    
    public synchronized void addPuntiInseriti(){
        puntiInseriti++;
    }
    
    public synchronized int getNumGenerare(){
        return daGenerare;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esestatistiche;

import java.util.Vector;
import java.util.concurrent.Semaphore;

/**
 *
 * @author saccani_federico
 */
public class DatiCondivisi {
    private Vector vett;
    private int daGenerare;
    private int puntiInseriti, puntiLetti;
    private int spaziInseriti, spaziLetti;
    private boolean continua;
    
    private Semaphore syncGenerato;
    private Semaphore syncContato;
    
    private Semaphore syncScritto;
    private Semaphore syncModificato;
    
    public DatiCondivisi(int daGene){
        vett = new Vector<Character>(daGene);
        daGenerare=daGene;
        syncGenerato = new Semaphore(0);
        syncContato = new Semaphore(2);
        syncScritto = new Semaphore(0);
        syncModificato = new Semaphore(0);
        continua=true;
    }
    
    public synchronized void fermaTutti(){
        continua=false;
    }
    public synchronized boolean getContinua(){
        return continua;
    }
    
    public synchronized int getSpaziLetti(){
        return spaziLetti;
    }
    public synchronized int getSpaziInseriti(){
        return spaziInseriti;
    }
    public synchronized int getPuntiLetti(){
        return puntiLetti;
    }
    public synchronized int getPuntiInseriti(){
        return puntiInseriti;
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
    
    public synchronized void addSpaziLetti(){
       spaziLetti++;
    }
    
    public synchronized void addPuntiLetti(){
        puntiLetti++;
    }
    
    public synchronized int getNumGenerare(){
        return daGenerare;
    }
    
    public Semaphore getSyncScritto(){return syncScritto;}
    public Semaphore getSyncModificato(){return syncModificato;}
    
    public  void acquireSyncGenerato() {
        try{
        syncGenerato.acquire();
        }catch(InterruptedException e){System.out.println(e.toString());}
    }
    
    public  void releaseSyncGenerato() {    
        syncGenerato.release();
    }
    
    public  void acquireSyncContato() {
        try{
        syncContato.acquire();
        }catch(InterruptedException e){System.out.println(e.toString());}
    }
    
    public  void releaseSyncContato() {    
        syncContato.release();
    }
    
    public synchronized Character getElement(int pos){
        return (Character)vett.elementAt(pos);
    }
    
    
}

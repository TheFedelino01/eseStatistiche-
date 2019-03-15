/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esestatistiche;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saccani_federico
 */
public class thVisualizza extends Thread{
    private DatiCondivisi ptrDati;
      
    public thVisualizza(DatiCondivisi ptrDati){
        this.ptrDati=ptrDati;
    }
    
    @Override
    public void run(){
        while(ptrDati.getContinua()==true){
            int daGenerare = ptrDati.getNumGenerare();
            int i=0;

            try {
                ptrDati.getSyncModificato().acquire();//Aspetto che qualcuno modifica
            } catch (InterruptedException ex) {
                Logger.getLogger(thVisualizza.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(ptrDati.getContinua()==true){
                System.out.println("Spazi Inseriti:"+ptrDati.getSpaziInseriti());
                System.out.println("Spazi Letti:"+ptrDati.getSpaziLetti());

                System.out.println("Punti Inseriti:"+ptrDati.getPuntiInseriti());
                System.out.println("Punti Letti:"+ptrDati.getPuntiLetti());
                System.out.println("------------\n");
            }
            
            ptrDati.getSyncScritto().release();//DICO CHE HO SCRITTO
        }
        System.out.println("VISUALIZZO STOP");
    }
}

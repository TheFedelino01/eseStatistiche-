/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esestatistiche;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saccani_federico
 */
public class thConta extends Thread{
      private DatiCondivisi ptrDati;
      private boolean punti;
      
    public thConta(DatiCondivisi ptrDati, boolean punti){
        this.ptrDati=ptrDati;
        this.punti=punti;
    }
    
    @Override
    public void run(){
        System.out.println("START CONTO");
        int daGenerare = ptrDati.getNumGenerare();
        int i=0;
        Character preso;
        
        ptrDati.acquireSyncGenerato();//ASPETTO CHE FINISCE DI GENERARE
        System.out.println("CONTO VERAMENTE");
        while(i<daGenerare){
            preso=ptrDati.getElement(i);
            
            //Aggiorno i punti e gli spazi LETTI
            if(preso.equals(' ') && punti==false){//Se NON devo aggiornare i punti 
                ptrDati.addSpaziLetti();
                aspettoCheVisualizza();
            }else if(preso.equals('.') && punti==true){ //Se devo aggiornare i punti
                ptrDati.addPuntiLetti();
                aspettoCheVisualizza();
            }
            
            
            
            
            i++;
        }
        System.out.println("STOP CONTO");
        ptrDati.releaseSyncContato();//DICO CHE HO CONTATO TUTTO

    }
    
    private void aspettoCheVisualizza(){
        ptrDati.getSyncModificato().release();//Dico che ho modificato
            try {
                ptrDati.getSyncScritto().acquire();//Aspetto che visualizza
            } catch (InterruptedException ex) {Logger.getLogger(thConta.class.getName()).log(Level.SEVERE, null, ex);}
    }
}

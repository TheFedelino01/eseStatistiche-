/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esestatistiche;

import java.util.Random;

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
        int daGenerare = ptrDati.getNumGenerare();
        int i=0;
        Character preso;
        
        ptrDati.acquireSyncGenerato();//ASPETTO CHE FINISCE DI GENERARE
        
        while(i<daGenerare){
            preso=ptrDati.getElement(i);
            
            //Aggiorno i punti e gli spazi LETTI
            if(preso.equals(' ') && punti==false){//Se NON devo aggiornare i punti 
                ptrDati.addSpaziLetti();
            }else if(preso.equals('.') && punti==true){ //Se devo aggiornare i punti
                ptrDati.addPuntiLetti();
            }
            
            ptrDati.releasehoModificato();//Dico che ho modificato
            
            i++;
        }
        
        ptrDati.releaseSyncContato();//DICO CHE HO CONTATO TUTTO

    }
}

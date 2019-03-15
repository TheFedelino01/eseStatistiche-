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
public class thGenera1 extends Thread{
    private DatiCondivisi ptrDati;
    
    public thGenera1(DatiCondivisi ptrDati){
        this.ptrDati=ptrDati;
    }
    
    @Override
    public void run(){
        System.out.println("GENERO");
        String str = "abcdefghilmnopqrstuvwxyz .";
        int daGenerare = ptrDati.getNumGenerare();
        int i=0;
        Character selez;
        Random rn = new Random();

        ptrDati.acquireSyncContato();//ASPETTO CHE ABBIANO CONTATO ENTRAMBI I TH
        ptrDati.acquireSyncContato();

        while(i<daGenerare){
            selez=str.charAt(rn.nextInt(26));//Prendo un carattere dal mio alfabeto
            
            ptrDati.push(selez);//Lo carico nel buffer
            
            //Aggiorno i punti e gli spazi inseriti
            if(selez.equals(' ')){
                ptrDati.addSpaziInseriti();
                aspettoCheVisualizza();
            }else if(selez.equals('.')){
                ptrDati.addPuntiInseriti();
                aspettoCheVisualizza();
            }
            
            i++;
        }
        System.out.println("FINITO DI GENERARE");
        ptrDati.releaseSyncGenerato();//Do la possibilità agli altri 2 thread di continuare
        ptrDati.releaseSyncGenerato();
        
    }
    private void aspettoCheVisualizza(){
        ptrDati.getSyncModificato().release();//Dico che ho modificato
            try {
                ptrDati.getSyncScritto().acquire();//Aspetto che visualizza
            } catch (InterruptedException ex) {Logger.getLogger(thConta.class.getName()).log(Level.SEVERE, null, ex);}
    }
}

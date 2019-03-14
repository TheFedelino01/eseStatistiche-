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
public class thGenera1 extends Thread{
    private DatiCondivisi ptrDati;
    
    public thGenera1(DatiCondivisi ptrDati){
        this.ptrDati=ptrDati;
    }
    
    @Override
    public void run(){
        String str = "abcdefghilmnopqrstuvwxyz .";
        int daGenerare = ptrDati.getNumGenerare();
        int i=0;
        Character selez;
        Random rn = new Random();
        
        ptrDati.acquireSyncContato();//ASPETTO CHE ABBIANO CONTATO ENTRAMBI I TH
        ptrDati.acquireSyncContato();
        
        while(i<daGenerare){
            selez=str.charAt(rn.nextInt(25));//Prendo un carattere dal mio alfabeto
            
            ptrDati.push(selez);//Lo carico nel buffer
            
            //Aggiorno i punti e gli spazi inseriti
            if(selez.equals(' ')){
                ptrDati.addSpaziInseriti();
            }else if(selez.equals('.')){
                ptrDati.addPuntiInseriti();
            }
            
            i++;
        }
        
        ptrDati.releaseSyncGenerato();//Do la possibilità agli altri 2 thread di continuare
        ptrDati.releaseSyncGenerato();
        
    }
}

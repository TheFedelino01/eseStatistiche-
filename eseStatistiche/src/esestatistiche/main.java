/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esestatistiche;

/**
 *
 * @author saccani_federico
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int daGenerare=500;
        DatiCondivisi dati = new DatiCondivisi(daGenerare);
        
        thGenera1 genera = new thGenera1(dati);
        thConta contaPunti = new thConta(dati,true);
        thConta contaSpazi = new thConta(dati,false);
        thVisualizza visualizza = new thVisualizza(dati);
        
        contaPunti.start();
        contaSpazi.start();
        genera.start();
        visualizza.start();
        
        
        try{
            contaPunti.join();
            contaSpazi.join();
        }catch(InterruptedException e){
            
        }
        dati.fermaTutti();
        dati.getSyncModificato().release();
        
        
        try{
            visualizza.join();
        }catch(InterruptedException e){}
        
        
        
        
    }
    
}

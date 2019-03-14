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
public class EseStatistiche {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int daGenerare=1;
        DatiCondivisi dati = new DatiCondivisi(daGenerare);
        
        thGenera1 genera = new thGenera1(dati);
        thConta contaPunti = new thConta(dati,true);
        thConta contaSpazi = new thConta(dati,false);
        thVisualizza visualizza = new thVisualizza(dati);
        
        
        genera.start();
        contaPunti.start();
        contaSpazi.start();
        
        
        try{
            contaPunti.join();
            contaSpazi.join();
        }catch(InterruptedException e){
            
        }
        visualizza.start();
        try{
            visualizza.join();
        }catch(InterruptedException e){}
        
        dati.fermaTutti();
        
        
    }
    
}

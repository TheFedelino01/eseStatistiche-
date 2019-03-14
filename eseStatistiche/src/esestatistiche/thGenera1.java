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
        
        while(i<daGenerare){
            selez=str.charAt(rn.nextInt(25));//Prendo un carattere dal io alfabeto
            i++;
        }
    }
}

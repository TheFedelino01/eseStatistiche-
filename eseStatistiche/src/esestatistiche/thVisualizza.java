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

            //ptrDati.acquireHoModificato();//Controllo se qualcuno ha modificato
            while(i<daGenerare){
              System.out.println(ptrDati.getElement(i));
              System.out.println("Spazi Inseriti:"+ptrDati.getSpaziInseriti());
              System.out.println("Spazi Letti:"+ptrDati.getSpaziLetti());

              System.out.println("Punti Inseriti:"+ptrDati.getPuntiInseriti());
              System.out.println("Punti Letti:"+ptrDati.getPuntiLetti());
              System.out.println("------------\n");
            }
        }
    }
}

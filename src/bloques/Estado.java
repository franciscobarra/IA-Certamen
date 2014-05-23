/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bloques;

import java.util.ArrayList;
import java.util.Objects;

public class Estado {
    private ArrayList<String> apilados, mesa;
    private Estado antecesor;
    private String movimiento;
    
    public Estado(ArrayList<String> apilados,ArrayList<String> mesa, 
                    Estado antecesor, String movimiento){
        
        this.apilados=apilados;
        this.mesa=mesa;
        this.movimiento=movimiento;
        this.antecesor=antecesor;
        
    }

    public ArrayList<String> getApilados() {
       ArrayList<String> aux = new ArrayList<String>(apilados);
     
        return aux;
    }

    public ArrayList<String> getMesa() {
        ArrayList<String> aux = new ArrayList<String>(mesa);
     
        return aux;
    }

    public Estado getAntecesor() {
        return antecesor;
    }

    public String getMovimiento() {
        return movimiento;
    }
   
    
     @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Estado)) return false;
        return ((Estado)obj).getApilados().equals(this.getApilados());
    }

    @Override
    public int hashCode() {
        //Hashcode generado por IDE
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.apilados);
        hash = 89 * hash + Objects.hashCode(this.mesa);
        return hash;
    }
}

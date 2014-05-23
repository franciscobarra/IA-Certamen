/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bloques;

import java.util.ArrayList;

/**
 *
 * @author Segurity
 */
public class Busqueda {
    
    private ArrayList<Estado> abierto,cerrado;
    private Estado actual,meta;
    private boolean busqueda;
    
    public Busqueda(Estado actual,Estado meta){
        this.actual = actual;
        this.meta = meta;
        abierto = new ArrayList<Estado>();
        cerrado = new ArrayList<Estado>();
    }
    
    public void setBusqueda(boolean busqueda){
        this.busqueda=busqueda;
    }
    public boolean getBusqueda(){
        return busqueda;
    }
     public String obtenerResultado() {
        StringBuilder str;
        ArrayList<String> resultado = iniciarBusqueda();
        int tam;
        
        if(resultado == null) return null;
       
        str = new StringBuilder();
        tam = resultado.size();
        
        //formateamos el resultado
        for(int contador = 0; contador < tam; contador++) {
            str.append("Paso ").append(contador + 1).append(". ").
                append(resultado.get(contador)).append("\n");
        }
        
        return str.toString();
    }
    
    /**
     * Metodo que inicia la busqueda
     * @return ArrayList 
     */
    private ArrayList iniciarBusqueda() {
        boolean esEstadoMeta = false;
        ArrayList<String> resultado;
       
        abierto.add(actual);
        
  
        while(!abierto.isEmpty() && !esEstadoMeta) {
              actual = abierto.get(0);
              if(!getBusqueda())abierto.remove(0); // busqueda en profundidad;
              if(!cerrado.contains(actual)) {
          
                cerrado.add(actual);
           
                expandir(actual);
              
                if(actual.equals(meta)) esEstadoMeta = true;
                  
            }
            if(getBusqueda())abierto.remove(0); // busqueda en anchura
        }
        
        if(!esEstadoMeta) return null;
        
        resultado = new ArrayList<>();
      
        while(actual.getAntecesor() != null) {
            resultado.add(0, actual.getMovimiento());
            actual = actual.getAntecesor();
        }
        
        return resultado;
    }
    
    public void expandir(Estado actual){
        Estado opcion;
        ArrayList<String> apilados,mesa;
        String movimiento;
        
         
        if(actual.getApilados().size()==2){ // Desapilar el primero de la "torre" cuando hay 2 apilados
           apilados= actual.getApilados();
           mesa = actual.getMesa();
           
           movimiento = apilados.get(1);
           mesa.add(apilados.get(1));
           mesa.add(0,apilados.get(0));
           apilados.remove(0);
           apilados.remove(0);
           
           opcion = new Estado (apilados,mesa,actual,"Estado Actual: "+actual.getApilados()+" - "+actual.getMesa()+
                                 "... Desapilar "+movimiento+" -> Nuevo Estado: "+apilados+" - "+mesa);
           if(getBusqueda())abierto.add(opcion); // busqueda en anchura;
           if(!getBusqueda())abierto.add(0,opcion); // busqueda en profundidad;
        }
        
        if(actual.getApilados().size()==3){ // Desapilar el primero de la "torre" Cuando hay 3 apilados
           apilados= actual.getApilados();
           mesa = actual.getMesa();
         
           movimiento = apilados.get(2);
           mesa.add(apilados.get(2));
           apilados.remove(2);
           
           opcion = new Estado (apilados,mesa,actual,"Estado Actual: "+actual.getApilados()+" - "+actual.getMesa()+
                                 "... Desapilar "+movimiento+" -> Nuevo Estado: "+apilados+" - "+mesa);
           if(getBusqueda())abierto.add(opcion); // busqueda en anchura;
           if(!getBusqueda())abierto.add(0,opcion); // busqueda en profundidad;
           
        }
        
        if(actual.getMesa().size()==1){ // Apila cuando hay 1 elemento en mesa
           for(int i=0; i<actual.getMesa().size(); i++){ 
                 apilados= actual.getApilados();
                 mesa = actual.getMesa();
                
                 movimiento = mesa.get(i);
                 apilados.add(mesa.get(i));
                 mesa.remove(i);
                
                 opcion = new Estado (apilados,mesa,actual,"Estado Actual: "+actual.getApilados()+" - "+actual.getMesa()+
                                 "... Apilar "+movimiento+" -> Nuevo Estado: "+apilados+" - "+mesa);
                if(getBusqueda())abierto.add(opcion); // busqueda en anchura;
                if(!getBusqueda())abierto.add(0,opcion); // busqueda en profundidad;
            }
        }
        if(actual.getMesa().size()==1 && actual.getApilados().size()==2){ // Apila el primero de la torre en el primero de la mesa
        
                 apilados= actual.getApilados();
                 mesa = actual.getMesa();
                 
                 movimiento= apilados.get(1);
                 apilados.add(0,mesa.get(0));
                 mesa.add(apilados.get(1));
                 mesa.remove(0);
                 apilados.remove(1);
                 
                 opcion = new Estado (apilados,mesa,actual,"Estado Actual: "+actual.getApilados()+" - "+actual.getMesa()+
                                      "... Apilar "+movimiento+" -> Nuevo Estado: "+apilados+" - "+mesa);
                 if(getBusqueda())abierto.add(opcion); // busqueda en anchura;
                 if(!getBusqueda())abierto.add(0,opcion); // busqueda en profundidad;
           
        }
        
        if(actual.getApilados().size()==0 && actual.getMesa().size()==3){ // apila cuando hay 3 en mesa y 0 en apilados
               for(int i=0; i<actual.getMesa().size(); i++){
                    for(int j=0; j<actual.getMesa().size(); j++){
                        if (j!=i){ 
                            apilados= actual.getApilados();
                             mesa = actual.getMesa();
                             apilados.add(mesa.get(i));
                             apilados.add(mesa.get(j));
                             mesa.remove(i);
                             if(j-1>=0) mesa.remove(j-1);
                             else mesa.remove(j);
                    
                             opcion = new Estado (apilados,mesa,actual,"Estado Actual: "+actual.getApilados()+" - "+actual.getMesa()+
                                 "... Apilar "+apilados+" -> Nuevo Estado: "+apilados+" - "+mesa);
                             if(getBusqueda())abierto.add(opcion); // busqueda en anchura;
                             if(!getBusqueda())abierto.add(0,opcion); // busqueda en profundidad;
                        }
                    }
                }
        }
    } 
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bloques;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Segurity
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> apilados, mesa;
        Estado inicial,fin;
        Busqueda b;
        String opcion;
        boolean menu=false;
        
        apilados = new ArrayList<String>();
        
        apilados.add("A");
        apilados.add("C");
        
        mesa = new ArrayList<String>();
        
        mesa.add("B");
        
        inicial = new Estado(apilados,mesa,null,null); // Crea Estado inicial
        
        // --------------------------------------------
        
        apilados = new ArrayList<String>();
        
        apilados.add("A");
        apilados.add("B");
        apilados.add("C");
        
        mesa = new ArrayList<String>();
        
        fin = new Estado(apilados,mesa,null,null); // Crea Estado Meta
        
        b = new Busqueda(inicial,fin);
        Scanner sc = new Scanner(System.in);
        
        do{
        System.out.println("Menu:");
        System.out.println("1.- Busqueda en Anchura");
        System.out.println("2.- Busqueda en Profundidad");
        System.out.println("---------------------------");
        System.out.print("Ingrese el numero: ");
        opcion = sc.nextLine();
        switch(opcion){
            case "1":
                b.setBusqueda(true);
                System.out.println("Estado  Inicial: [A, C] -> Apilados , [B] -> Mesa");
                System.out.println("");
                System.out.println(b.obtenerResultado());
                System.out.println("Estado  Final: [A, B, C] -> Apilados , [] -> Mesa");
                System.out.println("Resultado Final... || Busqueda En Anchura");
                menu=true;
                break;
            case "2":
                b.setBusqueda(false);
               System.out.println("Estado  Inicial: [A, C] -> Apilados , [B] -> Mesa");
                System.out.println("");
                System.out.println(b.obtenerResultado());
                System.out.println("Resultado Final... || Busqueda En Profundidad");
                menu=true;
                break; 
            default: 
                System.out.println("Numero no valido");   
             break;
        }
    }while(!menu);        
        
        
    }
    
}

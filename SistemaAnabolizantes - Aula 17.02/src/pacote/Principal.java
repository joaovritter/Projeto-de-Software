/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacote;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laboratorio
 */
public class Principal {
    public static void main(String[] args) {
        Anabolizante a1 = new Anabolizante ("Deca","123","Perigoso mas é top", "19,90","2");
        List<Anabolizante> listaAnabolizante = new ArrayList<Anabolizante>();
        
        for (Anabolizante a : listaAnabolizante){
            System.out.println(listaAnabolizante.toString());
        }
        
    }
}

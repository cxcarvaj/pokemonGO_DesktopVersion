/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

import java.util.Set;

/**
 *
 * @author acarrera
 */
public class Pokemon implements Comparable<Pokemon>{
    private String nombre;
    private String poder;
    private String velocidad;
    private String ruta;
    private String tipo;

    public Pokemon(String nombre, String poder,String velocidad, String ruta, String tipo) {
        this.nombre = nombre;
        this.poder = poder;
        this.ruta= ruta;
        this.velocidad= velocidad;
        this.tipo= tipo;
  
    }

    public String getNombre() {
        return nombre;
    }

    public String getPoder() {
        return poder;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public String getRuta() {
        return ruta;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Nombre:"+nombre+", Poder:"+poder;
    }

    @Override
    public int compareTo(Pokemon p) {
        int rTipo = this.tipo.compareTo(p.tipo);
        int rPoder = this.poder.compareTo(p.poder);
        int rNom = this.nombre.compareTo(p.nombre);
        switch(rTipo){
            case 1:
                return 1;
            case -1:
                return -1;
            case 0:
        switch(rPoder) {
            case 1:
                return 1;
            case -1:
                return -1;
            case 0:
        switch(rNom){
            case 1:
                return 1;
            case -1:
                return -1;
            case 0:
                return 0;
        }
        }
        }
        if(rTipo==0 && rNom ==0 && rPoder==0){
            return 0;
        }else{
            return rTipo;
        }
    }
    
         
}

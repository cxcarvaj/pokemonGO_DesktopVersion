/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author acarrera
 */
public class Entrenador implements Comparable<Entrenador>{
    private String nombre;
    private Map<String, Set<Pokemon>> pokemonsTipo;

    public Entrenador(String nombre) {
        this.nombre = nombre;
        this.pokemonsTipo= new TreeMap<>();
        
    }
    public Entrenador(String nombre, Map<String, Set<Pokemon>> pokemonsTipo) {
        this.nombre = nombre;
        this.pokemonsTipo= pokemonsTipo;
    }
    @Override
    public String toString() {
        return nombre ;
    }

    public Set<Pokemon> getSetPokemonsTipo() {
        return pokemonsTipo.get(this.nombre);
    }

    public String getNombre() {
        return nombre;
    }
    
    public void registroPokemonTrainer(Pokemon p){
        if(!(pokemonsTipo.containsKey(this.nombre)))
            pokemonsTipo.put(this.nombre, new TreeSet<>());
        pokemonsTipo.get(this.nombre).add(p);
        System.out.println(pokemonsTipo);
        try{
            for(Entrenador e: Principal.entrenadores){
                if(e.equals(new Entrenador(nombre,pokemonsTipo))){
                Principal.entrenadores.remove(e);
                Principal.entrenadores.add(new Entrenador(nombre,pokemonsTipo));
                }
            }
        }catch(Exception ex){
            System.out.println("Error"+ex);
        }
    }

    @Override
    public int compareTo(Entrenador e) {
        return this.nombre.compareTo(e.nombre);
        
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Entrenador other = (Entrenador) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }


    
    
}

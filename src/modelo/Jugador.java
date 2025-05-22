package modelo;

import java.util.List;
import java.util.LinkedList;

public class Jugador {
    private String nombre;
    private List<Ficha> array;
    private boolean mano;
    
    public Jugador(String nom){
        nombre=nom;
        array = new LinkedList<Ficha>();
        mano=false;
    }
    
    public void anadirFicha(Ficha f){
        array.add(f);
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setMano(){
        mano=true;
    }
    
    public boolean esMano(){
        return mano;
    }
    
    public boolean tieneFichas(){
        return !array.isEmpty();
    }
    

    public boolean puedeJugar (Mesa mesa){
        boolean res=false;

        if(!mesa.mesaVacia()){
            for(Ficha i : array){
                if(i.esColocable(mesa)){
                    res=true;
                }
            }
        }
        else{
            res=true;
        }

        return res;
    }
    

    public List<Ficha> fichasJugables(Mesa mesa){
        List<Ficha> jugables = new LinkedList<Ficha>();
        if(mesa.mesaVacia()){
            jugables = array;
        }
        else{
            for(Ficha i: array){
                if(i.esColocable(mesa)){
                    jugables.add(i);
                }
            }
        }
        return jugables;
    }
    

    public int sumarPuntos(){
        int suma=0;
        for(Ficha i : array){
            suma+=i.getNum1();
            suma+=i.getNum2();
        }
        return suma;
    }
    
    public void mostrarFichas(){
        for(Ficha i : array){
            System.out.print(i.toString());
        }
    }
    
    public boolean eliminaFicha(Ficha f){
        return array.remove(f);
    }
    
    public String toString(){
        StringBuilder texto = new StringBuilder("Nombre: ");
        texto.append(nombre + "\nFichas: ");
        for(Ficha i : array){
            texto.append(i.toString()+ " ");
        }
        return texto.toString();
    }
    
    
}

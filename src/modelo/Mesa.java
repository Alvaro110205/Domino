package modelo;

import java.util.LinkedList;
import java.util.Deque;

public class Mesa {
    private Deque<Ficha> m;
    private int[] contador;
    
    public Mesa(){
        m= new LinkedList();
        contador = new int[7];
    }
        
    public Ficha getPrimero(){
        return m.getFirst();
    }
    
    public Ficha getUltimo(){
        return m.getLast();
    }
    
    public boolean insertarPrincipio(Jugador j, Ficha f){
        boolean colocada=false;
        if(isPosibleAlPrincipio(f)){
            if(mesaVacia() || f.getNum2()==getPrimero().getNum1()){
                m.addFirst(f);
            }
            else{
                m.addFirst(f.inversa());
            }
            j.eliminaFicha(f);
            getContador()[f.getNum1()]++;
            getContador()[f.getNum2()]++;
            colocada=true;
        }
        return colocada;
    }
    
    public boolean insertarFinal(Jugador j, Ficha f) {
        boolean colocada=false;
        if(isPosibleAlFinal(f)){
            if(mesaVacia() || f.getNum2()==getUltimo().getNum1()){
                m.addLast(f);
            }
            else{
                m.addLast(f.inversa());
            }
            j.eliminaFicha(f);
            getContador()[f.getNum1()]++;
            getContador()[f.getNum2()]++;
            colocada=true;
        }
        return colocada;
    }

    public int[] getContador() {
        return contador;
    }

    public boolean isPosibleAlPrincipio(Ficha aux) {
        return this.mesaVacia()
                || aux.getNum1() == getPrimero().getNum1()
                || aux.getNum2() == getPrimero().getNum1();
    }

    public boolean isPosibleAlFinal(Ficha aux) {
        return this.mesaVacia()
                || aux.getNum1()==getUltimo().getNum2()
                || aux.getNum2()==getUltimo().getNum2();
    }

    public boolean mesaVacia(){
        return m.isEmpty();
    }
    
    public String toString(){
        StringBuilder texto = new StringBuilder("");
        for(Ficha  i : m){
            texto.append(i.toString());
        }
        return texto.toString();
    }
}

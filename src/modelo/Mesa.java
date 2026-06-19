package modelo;

import java.util.LinkedList;
import java.util.Deque;

public class Mesa {


/**
 * Representa la mesa de juego donde se colocan las fichas de dominó.
 * Permite insertar fichas al principio o al final de la mesa y llevar
 * un recuento de los valores que han aparecido durante la partida.
 *
 * @author Álvaro
 * @version 1.1
 */


    /**
     * Colección de fichas colocadas en la mesa.
     */
    private Deque<Ficha> m;

    /**
     * Contador de apariciones de los números del dominó.
     */
    private int[] contador;

    /**
     * Crea una mesa vacía e inicializa el contador.
     */
    public Mesa(){
        m= new LinkedList();
        contador = new int[7];
    }

    /**
     * Obtiene la primera ficha colocada en la mesa.
     *
     * @return primera ficha de la mesa.
     */
    public Ficha getPrimero(){
        return m.getFirst();
    }

    /**
     * Obtiene la última ficha colocada en la mesa.
     *
     * @return última ficha de la mesa.
     */
    public Ficha getUltimo(){
        return m.getLast();
    }

    /**
     * Inserta una ficha al principio de la mesa si la jugada es válida.
     *
     * @param j jugador que realiza la jugada.
     * @param f ficha que se desea insertar.
     * @return true si la ficha se coloca correctamente, false en caso contrario.
     * @see Jugador#eliminaFicha(Ficha)
     */
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

    /**
     * Inserta una ficha al final de la mesa si la jugada es válida.
     *
     * @param j jugador que realiza la jugada.
     * @param f ficha que se desea insertar.
     * @return true si la ficha se coloca correctamente, false en caso contrario.
     * @see Jugador#eliminaFicha(Ficha)
     */
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

    /**
     * Devuelve el contador de apariciones de los números.
     *
     * @return vector contador.
     */
    public int[] getContador() {
        return contador;
    }

    /**
     * Comprueba si una ficha puede colocarse al principio de la mesa.
     *
     * @param aux ficha que se desea comprobar.
     * @return true si puede colocarse al principio, false en caso contrario.
     */
    public boolean isPosibleAlPrincipio(Ficha aux) {
        return this.mesaVacia()
                || aux.getNum1() == getPrimero().getNum1()
                || aux.getNum2() == getPrimero().getNum1();
    }

    /**
     * Comprueba si una ficha puede colocarse al final de la mesa.
     *
     * @param aux ficha que se desea comprobar.
     * @return true si puede colocarse al final, false en caso contrario.
     */
    public boolean isPosibleAlFinal(Ficha aux) {
        return this.mesaVacia()
                || aux.getNum1()==getUltimo().getNum2()
                || aux.getNum2()==getUltimo().getNum2();
    }

    /**
     * Indica si la mesa está vacía.
     *
     * @return true si no contiene fichas, false en caso contrario.
     */
    public boolean mesaVacia(){
        return m.isEmpty();
    }

    /**
     * Devuelve una representación en texto de las fichas colocadas en la mesa.
     *
     * @return cadena con todas las fichas de la mesa.
     */
    @Override
    public String toString(){
        StringBuilder texto = new StringBuilder("");
        for(Ficha i : m){
            texto.append(i.toString());
        }
        return texto.toString();
    }
            }
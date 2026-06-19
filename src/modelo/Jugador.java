package modelo;

import java.util.LinkedList;
import java.util.List;

/**
 * Representa a un jugador de una partida de dominó.
 * Cada jugador dispone de un nombre, una colección de fichas
 * y un indicador que determina si es el jugador mano.
 *
 * @author Álvaro
 * @version 1.1
 */
public class Jugador {

    /**
     * Nombre del jugador.
     */
    private String nombre;

    /**
     * Lista de fichas que posee el jugador.
     */
    private List<Ficha> array;

    /**
     * Indica si el jugador es mano.
     */
    private boolean mano;

    /**
     * Crea un jugador con el nombre especificado.
     * Inicialmente no tiene fichas y no es mano.
     *
     * @param nom nombre del jugador.
     */
    public Jugador(String nom) {
        nombre = nom;
        array = new LinkedList<Ficha>();
        mano = false;
    }

    /**
     * Añade una ficha a la colección del jugador.
     *
     * @param f ficha que se desea añadir.
     */
    public void anadirFicha(Ficha f) {
        array.add(f);
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece al jugador como jugador mano.
     */
    public void setMano() {
        mano = true;
    }

    /**
     * Indica si el jugador es mano.
     *
     * @return true si es mano; false en caso contrario.
     */
    public boolean esMano() {
        return mano;
    }

    /**
     * Comprueba si el jugador todavía posee fichas.
     *
     * @return true si tiene fichas; false si no tiene ninguna.
     */
    public boolean tieneFichas() {
        return !array.isEmpty();
    }

    /**
     * Comprueba si el jugador puede realizar una jugada
     * con alguna de las fichas que posee.
     *
     * @param mesa mesa de juego actual.
     * @return true si puede jugar alguna ficha; false en caso contrario.
     * @see Ficha#esColocable(Mesa)
     */
    public boolean puedeJugar(Mesa mesa) {
        boolean res = false;

        if (!mesa.mesaVacia()) {
            for (Ficha i : array) {
                if (i.esColocable(mesa)) {
                    res = true;
                }
            }
        } else {
            res = true;
        }

        return res;
    }

    /**
     * Obtiene una lista con las fichas que el jugador
     * puede colocar en la mesa.
     *
     * @param mesa mesa de juego actual.
     * @return lista de fichas jugables.
     * @see Ficha#esColocable(Mesa)
     */
    public List<Ficha> fichasJugables(Mesa mesa) {
        List<Ficha> jugables = new LinkedList<Ficha>();

        if (mesa.mesaVacia()) {
            jugables = array;
        } else {
            for (Ficha i : array) {
                if (i.esColocable(mesa)) {
                    jugables.add(i);
                }
            }
        }

        return jugables;
    }

    /**
     * Calcula la suma de puntos de todas las fichas
     * que posee el jugador.
     *
     * @return suma total de puntos.
     */
    public int sumarPuntos() {
        int suma = 0;

        for (Ficha i : array) {
            suma += i.getNum1();
            suma += i.getNum2();
        }

        return suma;
    }

    /**
     * Muestra por consola todas las fichas del jugador.
     */
    public void mostrarFichas() {
        for (Ficha i : array) {
            System.out.print(i.toString());
        }
    }

    /**
     * Elimina una ficha de la colección del jugador.
     *
     * @param f ficha que se desea eliminar.
     * @return true si la ficha se eliminó correctamente;
     * false si no se encontraba en la colección.
     */
    public boolean eliminaFicha(Ficha f) {
        return array.remove(f);
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder("Nombre: ");

        texto.append(nombre + "\nFichas: ");

        for (Ficha i : array) {
            texto.append(i.toString() + " ");
        }

        return texto.toString();
    }
}
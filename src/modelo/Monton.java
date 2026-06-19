package modelo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Representa el montón de fichas de dominó disponibles para robar
 * durante una partida. Al crearse, contiene las 28 fichas de un
 * dominó doble-seis.
 *
 * @author Álvaro
 * @version 1.1
 */
public class Monton {

    /**
     * Lista que almacena las fichas disponibles en el montón.
     */
    private List<Ficha> m;

    /**
     * Crea un montón con todas las fichas de un dominó doble-seis.
     */
    public Monton() {
        m = new LinkedList<Ficha>();

        for(int i = 6; i >= 0; i--) {
            for(int j = 0; j <= i; j++) {
                m.add(new Ficha(i, j));
            }
        }
    }

    /**
     * Extrae y devuelve una ficha aleatoria del montón.
     * La ficha extraída se elimina del montón.
     *
     * @return ficha extraída aleatoriamente.
     */
    public Ficha extraerRandom() {
        Random rand = new Random();
        int valor = rand.nextInt(m.size());

        return m.remove(valor);
    }

    /**
     * Comprueba si el montón está vacío.
     *
     * @return true si no quedan fichas en el montón,
     * false en caso contrario.
     */
    public boolean esVacio() {
        return m.isEmpty();
    }

    /**
     * Devuelve una representación textual del contenido del montón.
     *
     * @return cadena con todas las fichas que permanecen en el montón.
     */
    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder("El monton tiene: ");

        for(Ficha i : m) {
            texto.append(i.toString()).append(" ");
        }

        return texto.toString();
    }
}

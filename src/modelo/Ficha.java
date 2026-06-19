package modelo;

/**
 * Representa una ficha de dominó formada por dos valores numéricos.
 * Cada ficha contiene un número en la parte izquierda y otro en la
 * parte derecha.
 *
 * @author Álvaro
 * @version 1.1
 */
public class Ficha {

    /**
     * Número situado en la parte izquierda de la ficha.
     */
    private final int num1;

    /**
     * Número situado en la parte derecha de la ficha.
     */
    private final int num2;

    /**
     * Crea una ficha de dominó con los valores indicados.
     *
     * @param n1 valor izquierdo de la ficha.
     * @param n2 valor derecho de la ficha.
     */
    public Ficha(int n1, int n2) {
        num1 = n1;
        num2 = n2;
    }

    /**
     * Obtiene el valor izquierdo de la ficha.
     *
     * @return valor almacenado en el lado izquierdo.
     */
    public int getNum1() {
        return num1;
    }

    /**
     * Obtiene el valor derecho de la ficha.
     *
     * @return valor almacenado en el lado derecho.
     */
    public int getNum2() {
        return num2;
    }

    /**
     * Devuelve una nueva ficha con los valores intercambiados.
     *
     * @return ficha inversa de la ficha actual.
     * @see #getNum1()
     * @see #getNum2()
     */
    public Ficha inversa() {
        return new Ficha(num2, num1);
    }

    /**
     * Comprueba si la ficha puede colocarse en la mesa.
     * Una ficha será colocable si alguno de sus extremos coincide
     * con alguno de los extremos visibles de la mesa.
     *
     * @param mesa mesa sobre la que se quiere colocar la ficha.
     * @return true si la ficha puede colocarse; false en caso contrario.
     * @see Mesa
     */
    public boolean esColocable(Mesa mesa) {
        boolean colocable = false;

        if (num1 == mesa.getPrimero().getNum1()
                || num2 == mesa.getPrimero().getNum1()
                || num1 == mesa.getUltimo().getNum2()
                || num2 == mesa.getUltimo().getNum2()) {
            colocable = true;
        }

        return colocable;
    }

    @Override
    public String toString() {
        return "[" + num1 + "|" + num2 + "]";
    }
}
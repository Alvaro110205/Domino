/*
Autores:

-Fernando Rodriguez Alvarez
-Jorge Portela Gonzales
-Adrian Gallego Beltran
*/

package ppal;

import modelo.Ficha;
import modelo.Jugador;
import modelo.Mesa;
import modelo.Monton;

import java.util.*;

public class ProjectDomino {
        

    public static List<Jugador> preguntarJugadores(Monton m){
        Scanner scan = new Scanner(System.in);
        Jugador j;
        List<Jugador> listaJugadores = new LinkedList<Jugador>();
        int numJug=0;
        do{
            try{
            System.out.println("Introduce el numero de jugadores: ");
            numJug=Integer.parseInt(scan.nextLine());
            }
            catch(NumberFormatException exc){
                System.err.println("Por favor introduce un numero del 2 al 4");
                numJug=0;
            }
        }while(numJug<2 || numJug>4);
        int i=1;
        String nombre;
        while(i<=numJug){
            System.out.println("Introduce el nombre del jugador "+i);
            nombre=scan.nextLine();
            j= new Jugador(nombre);
            for(int y=0;y<7;y++){
                j.anadirFicha(m.extraerRandom());
            }
            listaJugadores.add(j);
            i++;
        }
        return listaJugadores;
    }
    

    public static boolean domino(List<Jugador> l){
        boolean finJuego=false;
        Jugador j;
        Iterator<Jugador> itr = l.iterator();
        while(!finJuego && itr.hasNext()){
            j=itr.next();
            if(!j.tieneFichas()){
                finJuego=true;
                System.out.println("Enhorabuena "+j.getNombre()+", eres el ganador!");
            }
        }
        return finJuego;
    }
    

    public static boolean cierre(Mesa mesa){
        boolean cierre=false;
        if(!mesa.mesaVacia()){
            cierre=(mesa.getContador()[mesa.getPrimero().getNum1()]==8 && mesa.getContador()[mesa.getUltimo().getNum2()]==8);
        }
        return cierre;
        }


    public static void juego(Jugador j, Mesa mesa){
        Scanner scan = new Scanner(System.in);
        List<Ficha> jugables = j.fichasJugables(mesa);
        int opcion;
        Ficha aux;
        System.out.print("Las fichas que puedes jugar son: ");
        for(Ficha i : jugables){
            System.out.print(i);
        }
        do{
            System.out.println("\nEscoge la ficha que quieres jugar (1,2,3...): ");
            try{
                opcion=Integer.parseInt(scan.nextLine());
                }catch(NumberFormatException exc){
                    System.err.println("Por favor, introduce un numero");
                    opcion=-1;
                }
        }while(opcion<1 || opcion>=(jugables.size()+1));
        opcion--;
        aux=jugables.get(opcion);
        System.out.println("La ficha escogida es: "+aux.toString());

        colocarFicha(j,aux,mesa);
    }

    private static void colocarFicha(Jugador j, Ficha aux, Mesa mesa) {
        System.out.print("La ficha se puede colocar ");
        if(mesa.isPosibleAlPrincipio(aux)){
            System.out.print("al principio");
        }
        if(mesa.isPosibleAlFinal(aux)){
            System.out.print("al final.");
        }

        boolean colocada=false;
        Scanner scan = new Scanner(System.in);
        char opcionColoc;
        do{
            do{
                System.out.println("\nDonde la quieres colocar?(p/f)");
                opcionColoc=scan.nextLine().charAt(0);
            }while(opcionColoc!='p' && opcionColoc!='f');
            if(opcionColoc=='p'){
                colocada=mesa.insertarPrincipio(j,aux);
            } else if(opcionColoc=='f'){
                colocada=mesa.insertarFinal(j,aux);
            }
            if (!colocada) System.out.println("No se puede colocar en esa posición");
        }while(!colocada);
    }


    public static void turno(Jugador j, Monton m, Mesa mesa){
        Scanner scan = new Scanner(System.in);
        Ficha aux;
        System.out.println("\n///////////////////////////////////////////////////\n");
        System.out.println("Turno de "+j.getNombre());
        System.out.println("\nEl estado de la mesa es "+mesa.toString());
        System.out.print("Tus fichas son: ");
        j.mostrarFichas();
        System.out.print("\n");
        if(j.puedeJugar(mesa)){
            System.out.println(j.getNombre()+", puedes jugar.");
            juego(j, mesa);
        }
        else{
            System.out.println("No puedes jugar ninguna ficha");
            if(!m.esVacio()){
                aux=m.extraerRandom();
                System.out.println("Coges la ficha "+aux.toString());
                j.anadirFicha(aux);
                if(aux.esColocable(mesa)){
                    juego(j,mesa);
                }
            }
            else{
                System.out.println("No puedes coger. El monton esta vacio.");
            }
        }
    }
    
    
    

    public static void calcularGanadorCierre(List<Jugador> jugadores){
        int ganador=0;
        boolean empate=false;
        Jugador mano=null;
        int menor=Integer.MAX_VALUE;
        int[] resultados = new int [jugadores.size()];
        for(int i=0;i<resultados.length;i++){
            resultados[i]=jugadores.get(i).sumarPuntos();
            if(jugadores.get(i).esMano()){
                mano=jugadores.get(i);
            }
            if(resultados[i]==menor){
                empate=true;
            }
            else if(resultados[i]<menor){
                menor=resultados[i];
                ganador=i;
                empate=false;
            }
            
            System.out.println(jugadores.get(i).getNombre() + " suma " + resultados[i] + " puntos.");
        }
        if(empate){
            if(mano.sumarPuntos()==menor){
                System.out.println("Ha habido un empate. Gana el jugador que lleva la mano: " + mano.getNombre());
            }
            else{
                System.out.println("Se ha producido un empate en el que no interviene el jugador mano.");
            }
        }
        else{
            System.out.println("Enhorabuena "+jugadores.get(ganador).getNombre() + ". Eres el ganador por cierre!");
        }
    }
        
    public static void main(String[] args) {
        Mesa mesa = new Mesa();
        List<Jugador> jugadores;
        Monton mont = new Monton();
        int leToca=0;
        jugadores=preguntarJugadores(mont);           //Creamos los jugadores
        Random generador = new Random();
        jugadores.get(generador.nextInt(jugadores.size()-1)).setMano();    //Le damos la mano a un jugador aleatorio
        for(Jugador j : jugadores){                   //Comprobamos qué jugador es mano para darle el primer turno
            if(j.esMano()){
                leToca=jugadores.indexOf(j);
            }
        }
        while(!domino(jugadores) && !cierre(mesa)){
            if(leToca>=jugadores.size()){
                leToca=0;
            }
            turno(jugadores.get(leToca),mont,mesa);
            leToca++;            
        }
        if(cierre(mesa)){
            System.out.println("NADIE MAS PUEDE COLOCAR FICHAS");
            calcularGanadorCierre(jugadores);
        }
        System.out.println("El estado final de la mesa es: "+mesa.toString());
    }
    
}

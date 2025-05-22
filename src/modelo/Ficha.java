package modelo;

public class Ficha {
    private final int num1;
    private final int num2;
    
    public Ficha(int n1, int n2){
        num1=n1;
        num2=n2;
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }
    

    public Ficha inversa(){
        return new Ficha(num2,num1);
    }
    
    public boolean esColocable(Mesa mesa){
        boolean colocable=false;
        if(num1==mesa.getPrimero().getNum1() || num2==mesa.getPrimero().getNum1() ||
           num1==mesa.getUltimo().getNum2() || num2==mesa.getUltimo().getNum2()) {
            colocable=true;
        }
        return colocable;
    }
    
    public String toString(){
        return("["+num1+"|"+num2+"]");
    }
}

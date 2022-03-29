package laboratorio1;

public class MiHebra3 extends Thread{

    int miId;
    CuentaIncrementos1a count;

    public MiHebra3(int miId, CuentaIncrementos1a count){
        this.miId = miId;
        this.count = count;
    }

    public void run(){
        System.out.println("Comienza la hebra con identificador: " + miId + " y con el contador a " + count.dameContador());
        for(int i = 0; i < 1000000; i++) {
           count.incrementaContador();
        }
        System.out.println("Finaliza la hebra con identificador: " + miId + " y con el contador a " + count.dameContador());
    }
}

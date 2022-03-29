package laboratorio1;

public class MiHebra extends Thread{

    int miId;
    int start;
    int end;
    public MiHebra(int miId, int start, int end){
        this.miId = miId;
        this.start = start;
        this.end = end;
    }

    public void run(){
        int suma = 0;
        for(int i = start; i < end; i++) {
            suma += i;
        }
        System.out.println("Identificador: " + miId + " Suma: " + suma);
    }
}

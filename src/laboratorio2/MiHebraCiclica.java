package laboratorio2;

import static laboratorio2.EjemploFuncionCostosa1a.evaluaFuncion;

public class MiHebraCiclica extends Thread{

    int id;
    int numHebras;
    int n;
    double vectorX[];
    double vectorY[];

    public MiHebraCiclica(int id, int numHebras, int n, double vectorX[], double vectorY[]){
        this.id = id;
        this.numHebras = numHebras;
        this.n = n;
        this.vectorX = vectorX;
        this.vectorY = vectorY;
    }

    public void run() {
        for (int i = id; i < n; i += numHebras) {
            vectorY[i] = evaluaFuncion(vectorX[i]);
        }
    }
}

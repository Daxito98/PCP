
package laboratorio2;

import static laboratorio2.EjemploFuncionCostosa1a.evaluaFuncion;

public class MiHebraBloque extends Thread{

    int id;
    int numHebras;
    int n;
    double vectorX[];
    double vectorY[];

    public MiHebraBloque(int id, int numHebras, int n,  double vectorX[],  double vectorY[]){
        this.id = id;
        this.numHebras = numHebras;
        this.n = n;
        this.vectorX = vectorX;
        this.vectorY = vectorY;
    }

    public void run() {
        for (int i = n * id / numHebras; i < n*(id+1)/numHebras; i++) {
            vectorY[i] = evaluaFuncion(vectorX[i]);
        }
    }
}

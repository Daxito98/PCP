package laboratorio1;

public class EjemploCreacionRunnable {

    public static void main(String[] args) {
        new Thread(new MiRun(0)).run();
        new Thread(new MiRun(1)).run();
    }
}

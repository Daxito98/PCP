package laboratorio1;

public class EjemploCreacionThread {

    public static void main(String[] args) {
        System.out.println("Comienzo del programa");
        MiHebra hebra1 = new MiHebra(0,100000,1100000);
        hebra1.start();
        MiHebra hebra2 = new MiHebra(1, 300000, 1300000);

        hebra2.start();
        try {
            hebra1.join();
            hebra2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Fin del programa");
    }
}

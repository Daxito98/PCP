package laboratorio1;

public class EjemploIncrementos1a {

    public static void main(String[] args) {
        int numHebras;

        if(args.length != 1){
            System.err.println("Uso: java programa <numHebras>");
            System.exit(-1);
        }
        try{
            numHebras = Integer.parseInt(args[0]);
        }catch (NumberFormatException ex){
            numHebras = -1;
            System.out.println("ERROR: Argumentos númericos incorrectos.");
            System.exit(-1);
        }
        System.out.println("numHebras: " + numHebras);
        MiHebra3 hebras[] = new MiHebra3[numHebras];

        CuentaIncrementos1a count = new CuentaIncrementos1a();
        System.out.println("Inicio: " + count.dameContador());
        for(int i = 0; i < numHebras; i++){
            hebras[i] = new MiHebra3(i,count);
        }
        for(int i = 0; i < numHebras; i++){
            hebras[i].start();
            try {
                hebras[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final: " + count.dameContador());
    }
}

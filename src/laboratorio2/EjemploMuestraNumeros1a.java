package laboratorio2;
/*
class EjemploMuestraNumeros1a {

  public static void main( String args[] ) {
    int n, numHebras;

    // Comprobacion y extraccion de los argumentos de entrada.
    if (args.length != 2) {
      System.err.println("Uso: java programa <numHebras> <n>");
      System.exit(-1);
    }
    try {
      numHebras = Integer.parseInt(args[0]);
      n = Integer.parseInt(args[1]);
    } catch (NumberFormatException ex) {
      numHebras = -1;
      n = -1;
      System.out.println("ERROR: Argumentos numericos incorrectos.");
      System.exit(-1);
    }
    MiHebra[] miHebras = new MiHebra[numHebras];
    for (int i = 0; i < miHebras.length; i++) {
        miHebras[i] = new MiHebra(i, numHebras, n);
        miHebras[i].start();
      try {
          miHebras[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
*/
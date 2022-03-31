package laboratorio3;

import java.util.concurrent.atomic.AtomicInteger;

import static laboratorio3.EjemploMuestraPrimosEnVector2a.esPrimo;



// ============================================================================
class MiHebraPrimoDistCiclica extends Thread {

  int id;
  int numHebras;
  long[] n;

  public MiHebraPrimoDistCiclica(int id, int numHebras, long[] vector){
    this.id = id;
    this.numHebras = numHebras;
    this.n = vector;
  }
  public void run() {
    for (int i = id; i < n.length; i += numHebras) {
      if(esPrimo(n[i])) {
        System.out.println( "  Encontrado primo: " + n[i]);
      }
    }
  }
}

// ============================================================================
class MiHebraPrimoDistPorBloques extends Thread {

  int id;
  int numHebras;
  long[] n;

  public MiHebraPrimoDistPorBloques(int id, int numHebras, long vector[]){
    this.id = id;
    this.numHebras = numHebras;
    this.n = vector;
  }
  public void run() {
    for (int i = n.length * id / numHebras; i < n.length*(id+1)/numHebras; i++) {
      if( esPrimo(n[i])) {
        System.out.println( "  Encontrado primo: " + n[i]);
      }
    }
  }
}

// ============================================================================
class MiHebraPrimoDistDinamica extends Thread {

  AtomicInteger indice;
  long[] n;

  public MiHebraPrimoDistDinamica(AtomicInteger indice, long vector[]){
    this.indice = indice;
    this.n = vector;
  }
  public void run() {
    for (int i = indice.get(); i < n.length; i++){
      if(esPrimo(n[indice.get()])){
        System.out.println( "  Encontrado primo: " + n[indice.get()]);
      }
      indice.addAndGet(1);
    }
  }
}
// ===========================================================================
public class EjemploMuestraPrimosEnVector2a {
// ===========================================================================

  // -------------------------------------------------------------------------
  public static void main( String args[] ) {
    int     numHebras;
    long    t1, t2;
    double  ts, tc, tb, td;
    /*
    long[] vectorNumeros = {
                200000033L, 200000039L, 200000051L, 200000069L, 
                200000161L, 200000183L, 200000201L, 200000209L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                4L, 4L, 4L, 4L, 4L, 4L, 4L, 4L
            };

     */
     long    vectorNumeros[] = {
                200000033L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                200000039L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                200000051L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                200000069L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                200000161L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                200000183L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                200000201L, 4L, 4L, 4L, 4L, 4L, 4L, 4L,
                200000209L, 4L, 4L, 4L, 4L, 4L, 4L, 4L
            };
    // Comprobacion y extraccion de los argumentos de entrada.
    if( args.length != 1 ) {
      System.err.println( "Uso: java programa <numHebras>" );
      System.exit( -1 );
    }
    try {
      numHebras = Integer.parseInt( args[ 0 ] );
    } catch( NumberFormatException ex ) {
      numHebras = -1;
      System.out.println( "ERROR: Argumentos numericos incorrectos." );
      System.exit( -1 );
    }
    //
    // Implementacion secuencial.
    //
    System.out.println( "" );
    System.out.println( "Implementacion secuencial." );
    t1 = System.nanoTime();
    for( int i = 0; i < vectorNumeros.length; i++ ) {
      if( esPrimo( vectorNumeros[ i ] ) ) {
        System.out.println( "  Encontrado primo: " + vectorNumeros[ i ] );
      }
    }
    t2 = System.nanoTime();
    ts = ( ( double ) ( t2 - t1 ) ) / 1.0e9;
    System.out.println( "Tiempo secuencial (seg.):                    " + ts );

    //
    // Implementacion paralela ciclica.
    //
    System.out.println( "" );
    System.out.println( "Implementacion paralela ciclica." );
    t1 = System.nanoTime();
    // Gestion de hebras para la implementacion paralela ciclica
    MiHebraPrimoDistCiclica[] hebras = new MiHebraPrimoDistCiclica[numHebras];
    for (int i = 0; i < hebras.length; i++) {
      hebras[i] = new MiHebraPrimoDistCiclica(i, numHebras, vectorNumeros);
      hebras[i].start();
      try {
        hebras[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    t2 = System.nanoTime();
    tc = ( ( double ) ( t2 - t1 ) ) / 1.0e9;
    System.out.println( "Tiempo paralela ciclica (seg.):              " + tc );
    System.out.println( "Incremento paralela ciclica:                 " + ((tc-ts)/ts)*100);
    //
    // Implementacion paralela por bloques.
    //
    System.out.println( "" );
    System.out.println( "Implementacion paralela por bloques." );
    t1 = System.nanoTime();
    // Gestion de hebras para la implementacion paralela ciclica
    MiHebraPrimoDistPorBloques[] misHebras = new MiHebraPrimoDistPorBloques[numHebras];
    for (int i = 0; i < misHebras.length; i++) {
      misHebras[i] = new MiHebraPrimoDistPorBloques(i, numHebras, vectorNumeros);
      misHebras[i].start();
      try {
        misHebras[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    t2 = System.nanoTime();
    tb = ( ( double ) ( t2 - t1 ) ) / 1.0e9;
    System.out.println( "Tiempo paralela por bloques (seg.):              " + tb );
    System.out.println( "Incremento paralela por bloques:                 " + ((tb-ts)/ts)*100);

    //
    // Implementacion paralela dinamica.
    //
    System.out.println( "" );
    System.out.println( "Implementacion paralela dinamica." );
    t1 = System.nanoTime();
    AtomicInteger indice = new AtomicInteger();
    MiHebraPrimoDistDinamica[] hebras1 = new MiHebraPrimoDistDinamica[numHebras];
    for(int i = 0; i < hebras1.length; i++){
      hebras1[i] = new MiHebraPrimoDistDinamica(indice, vectorNumeros);
      hebras1[i].start();
      try {
        hebras1[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    t2 = System.nanoTime();
    td = ( ( double ) ( t2 - t1 ) ) / 1.0e9;
    System.out.println( "Tiempo paralela dinamica (seg.):              " + td );
    System.out.println( "Incremento paralela dinamica:                 " + ((td-ts)/ts)*100);
  }

  // -------------------------------------------------------------------------
  static boolean esPrimo( long num ) {
    boolean primo;
    if( num < 2 ) {
      primo = false;
    } else {
      primo = true;
      long i = 2;
      while( ( i < num )&&( primo ) ) {
        primo = ( num % i != 0 );
        i++;
      }
    }
    return( primo );
  }
}

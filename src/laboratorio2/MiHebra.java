package laboratorio2;

public class MiHebra extends Thread{

    int start;
    int end;


    public MiHebra(int start, int end){
        this.start = start;
        this.end = end;

    }

    public void run(){
       for(int i = start; i < end; i++)
           System.out.println(i);
    }
}

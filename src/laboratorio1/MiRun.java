package laboratorio1;

public class MiRun implements Runnable{

    int miId;

    public MiRun(int miId){
        this.miId = miId;
    }

    public void run(){
        for(int i = 0; i < 1000; i++)
            System.out.println(miId);
    }
}

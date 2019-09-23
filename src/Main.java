import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double x[]=new double [2];
        x[0]=2;
        x[1]=1.5;
        SPUSK q=new SPUSK(x,0.000005);
        System.out.println("SPUSK ");
        q.p();
        x[0]=2;
        x[1]=1.5;
        NUTON w=new NUTON(x,0.000005);
        System.out.println(" ");
        System.out.println("Nuton");
        w.p();
        x[0]=2;
        x[1]=1.5;
        NUTONPPP r=new NUTONPPP(x,0.000005);
        System.out.println(" ");
        System.out.println("Nuton UPG");
        r.p();
        x[0]=2;
        x[1]=1.5;
        Fletcher t=new Fletcher(x,0.000005);
        System.out.println(" ");
        System.out.println("FLETCHER");
        t.p();

    }

}

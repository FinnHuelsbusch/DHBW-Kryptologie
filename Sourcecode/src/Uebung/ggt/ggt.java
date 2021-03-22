package Uebung.ggt;

public class ggt {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(ggtSub(237,27));
        System.out.println(System.currentTimeMillis()-start);
        start = System.currentTimeMillis();
        System.out.println(ggtSub(31,103));
        System.out.println(System.currentTimeMillis()-start);
        start = System.currentTimeMillis();
        System.out.println(ggtSub(47,1));
        System.out.println(System.currentTimeMillis()-start);
    }


    public static int ggt(int a, int b){
        if(a < b) {
            return ggt(b, a);
        } else if(a%b == 0){
            return b;
        }
         else {
        return ggt(a % b, b);
        }
    }
    public static int ggtSub(int a, int b){
        if(a < b) {
            return ggtSub(b-a, a);
        } else if(a== b ){
            return b;
        }
        else {
            return ggtSub(a - b, b);
        }
    }
}

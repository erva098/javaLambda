package lambda;

import java.util.stream.IntStream;

public class Lambda05 {
    public static void main(String[] args) {
        System.out.println("TASK 01 amele topla-->"+toplaAmele(10));
        System.out.println("TASK 01 cincix topla-->"+toplaCincix(10));
        System.out.println("   ***   ");

        System.out.println("TASK 02 -->"+toplaCift(10));//2+4+6+8+10
        System.out.println("   ***   ");

        System.out.println("TASK 03 -->"+toplaIlkXCift(10));//2+4+6...18+20
        System.out.println("   ***   ");

        System.out.println("TASK 04 -->"+toplaIlkXTek(10));//1+3+5....19;
        System.out.println("   ***   ");

        System.out.println("TASK 05 -->");
        ikininIlkKuvvetPrint(7);
        System.out.println("\n   ***   ");

        System.out.println("TASK 06 -->");
        istenenSayiIlkKuvvetPrint(4,3);
        System.out.println("   ***   ");
        istenenSayiIlkKuvvetPrint(3,4);
        System.out.println("   ***   ");

        System.out.println("TASK 07 -->"+istenenSayiFactorial(3));//1*2*3
        System.out.println("   ***   ");

        System.out.println("TASK 08 -->"+xKuvveti(4,3));
        System.out.println("   ***   ");

    }

    //TASK 01 --> Structured Programming ve Functional Programming ile 1'den x'e kadar tamsayilari toplayan bir program create ediniz.

    //Structured(AMELE) Programming
    public static int toplaAmele(int x) {
        int toplam = 0;

        for (int i = 0; i <= x; i++) {
            toplam += i;
        }
        return toplam;
    }

    //Functional(cincix Programming

    public static int toplaCincix(int x) {
     return   IntStream.range(1,x+1).//1'den x'e kadar (x dahil,x+1 haric) int degerler akisa alindi
             sum();//akisdaki int degerler toplandi
    }


    //TASK 02 --> 1'den x'e kadar cift tamsayilari toplayan bir program create ediniz.

    public static int toplaCift(int x) {
        return   IntStream.//int class'indan int degerleri akit demek
                rangeClosed(1,x).//1'den x'e kadar (x dahil) int degerler akisa alindi
                filter(Lambda01::ciftBul).//akisdai cift int degerler filtrelendi
                sum();//akisdaki int degerler toplandi
    }


    //TASK 03 --> Ilk x pozitif cift sayiyi toplayan program  create ediniz.

    public static int toplaIlkXCift(int x) {
        return   IntStream.
                iterate(2,t->t+2).//2'den baslayarak sonsuza kadar elemanlari 2 artirarak akisa alir -->2,4,6...
                limit(x).//akisdaki ilk x int degeri akisa alir
                sum();//akisdaki int degerler toplandi

        //iterate(seed, repeat action) --> seed'den başlayarak repeat action'a göre  sonsuza kadar elemanları akısa koyar
    }


    //TASK 04 --> Ilk X pozitif tek tamsayiyi toplayan programi  create ediniz.

    public static int toplaIlkXTek(int x) {
        return   IntStream.
                iterate(1,t->t+2).//2'den baslayarak sonsuza kadar elemanlari 2 artirarak akisa alir -->1,3,5...
                        limit(x).//akisdaki ilk x int degeri akisa alir
                        sum();//akisdaki int degerler toplandi

    }



    //TASK 05 --> 2'nin ilk x kuvvetini ekrana yazdiran programi  create ediniz.

    public static void ikininIlkKuvvetPrint(int x) {
        IntStream.
                iterate(2,t->t*2).//2'den baslayarak sonsuza kadar elemanlari 2 ile carparak akisa alir -->2,4,8...
                        limit(x).//akisdaki ilk x int degeri akisa alir
                        forEach(Lambda01::yazdir);
    }



    //TASK 06 --> Istenilen bir sayinin ilk x kuvvetini ekrana yazdiran programi  create ediniz.

    public static void istenenSayiIlkKuvvetPrint(int istenenSayi, int x) {
        IntStream.
                iterate(istenenSayi,t->t*istenenSayi).
                limit(x).//akisdaki ilk x int degeri akisa alir
                forEach(Lambda01::yazdir);
    }


    //TASK 07 --> Istenilen bir sayinin faktoriyelini hesaplayan programi  create ediniz.

    public static int istenenSayiFactorial(int x){

        return IntStream.
                rangeClosed(1,x).
                //reduce(Math::multiplyExact);
                reduce(1,(t,u)->t*u);
    }


    //TASK 08 --> Istenilen bir sayinin  x. kuvvetini ekrana yazdiran programi  create ediniz.

    public static int xKuvveti (int istenenSayi, int x) {
        //return Math.pow(istenenSayi, x);

        return IntStream.
                iterate(istenenSayi,t->t*istenenSayi).
                limit(x).skip(x-1).reduce(0,(t,u)->u);

    }
}
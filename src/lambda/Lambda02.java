package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Lambda02 {
    public static void main(String[] args) {

        List<Integer> sayi = new ArrayList<>(Arrays.asList(4, 2, 6, 11, -5, 7, 3, 15));

        ciftKarePrint(sayi);
        System.out.println("\n  ***  ");
        tekKupBirFazlaPrint(sayi);
        System.out.println("\n  ***  ");
        ciftKarekokPrint(sayi);
        System.out.println("\n  ***  ");
        maxEl(sayi);
        System.out.println("\n  ***  ");
        ciftKareMaxBul(sayi);
        System.out.println("\n  ***  ");
        elTopla(sayi);
        System.out.println("\n  ***  ");
        ciftCarp(sayi);
        System.out.println("\n  ***  ");

    }
    // Task : Functional Programming ile listin cift elemanlarinin  karelerini ayni satirda aralarina bosluk bırakarak print ediniz

    public static void ciftKarePrint(List<Integer> sayi){
        sayi.
                stream().//akisa aldik
                filter(Lambda01::ciftBul).//cift olanlari ayirdik
                map(t->t*t).//kareleri alindi
                forEach(Lambda01::yazdir);//yazdirma
        //map()--> Stream içerisindeki elemanları başka tiplere dönüştürmek veya üzerlerinde işlem yapmak (update) için Map kullanılmaktadır.
    }

    // Task : Functional Programming ile listin tek elemanlarinin  kuplerinin bir fazlasini ayni satirda aralarina bosluk birakarak print edin

    public static void tekKupBirFazlaPrint(List<Integer> sayi){
        sayi.
                stream().//sayilar akisa alindi
                filter(t->t%2!=0).//tek elemanlar filtrelendi
                map(t->(t*t*t)+1).//tek elemanlarin kuplerinin 1 fazlasina update edildi
                forEach(Lambda01::yazdir);//print edildi
    }

    // Task : Functional Programming ile listin cift elemanlarinin   karekoklerini ayni satirda aralarina bosluk birakarak yazdiriniz

    public static void ciftKarekokPrint(List<Integer> sayi){
        sayi.
                stream().
                filter(Lambda01::ciftBul).
                map(Math::sqrt).//math ref-->double deger return eder
                //forEach(Lambda01::yazdir); bu method int return edecegi icin map'den cikan datalari calistirmaz
                forEach(t->System.out.println(t+" "));
    }

    // Task : list'in en buyuk elemanini yazdiriniz

    public static void maxEl(List<Integer> sayi){
      Optional<Integer> maxSayi= sayi.
              stream().
              reduce(Math::max);//akisa giren elamanlarin action sonrasi tek eleman haline getirir
                               //terminal method'u bundan sonra baska method kullanamazsin,foreach de bu sekilde

        System.out.println(maxSayi);
        System.out.println("Halukca"+ sayi.stream().reduce(Math::max));

         /*
 reduce()-->azaltmak ... bir cok datayi tek bir dataya(max min carp top vs islemlerde) cevirmek icin kullanilir.
 kullanımı yaygındır pratiktir.
 Bir Stream içerisindeki verilerin teker teker işlenmesidir. Teker teker işleme sürecinde, bir önceki adımda elde edilen sonuç
 bir sonraki adıma girdi olarak sunulmaktadır. Bu sayede yığılmlı bir hesaplama süreci elde edilmiş olmaktadır.
 reduce metodu ilk parametrede identity değeri, ikinci parametrede ise BinaryOperator türünden bir obj kullanılır.
 reduce işleminde bir önceki hesaplanmış değer ile sıradaki değer bir işleme tabi tutulmaktadır.
 İşleme başlarken bir önceki değer olmadığı için bu değer identity parametresinde tanımlanmaktadır.

 */
    }

    // Task : List'in cift elemanlarin karelerinin en buyugunu print ediniz

    public static void ciftKareMaxBul(List<Integer> sayi){
        System.out.println(sayi.
                stream().
                filter(Lambda01::ciftBul).
                map(t -> t * t).
                reduce(Math::max));//36 daha genis bir class

        System.out.println("daha hizli specific integer class : "+sayi.
                stream().
                filter(Lambda01::ciftBul).
                map(t -> t * t).
                reduce(Integer::max));//36 daha specific class daha hizli run olur
    }

    // Task : List'teki tum elemanlarin toplamini yazdiriniz.
    //Lambda Expression...

    public static void elTopla(List<Integer> sayi){
     int toplam=sayi.stream().reduce(0,(a,b)->a+b); //Lambda Expression...

        /*
        a ilk degerini her zaman atanan degerden(identity) alir
        b degerini her zaman stream()'dan akistan alir
        a ilk degerinden sonraki her degeri action(islem)'den alir. islem burada toplam oluyor
         */

        System.out.println("Lambda exp. : "+toplam);
        //Method Expression...
        Optional<Integer> topla=sayi.stream().reduce(Integer::sum); //bu null veriyor o yuzden optional olmasi gerek int yazinca kabul etmiyor
        //yukarida atanmis bir 0 degeri var o yuzden int yazdigimiz halde kabul etti. Ama burada atanmis bir deger yok
        System.out.println("method ref : "+sayi.stream().reduce(Integer::sum));//method ref

    }

    // Task : List'teki cift elemanlarin carpimini  yazdiriniz.

    public static void ciftCarp(List<Integer> sayi){
        System.out.println("method ref : "+sayi.
                stream().
                filter(Lambda01::ciftBul).
                reduce(Math::multiplyExact));

        //Lambda Expression...
        System.out.println("Lambda exp. : "+sayi.
                stream().
                filter(Lambda01::ciftBul).
                reduce(1, (a, b) -> (a * b)));


    }

}

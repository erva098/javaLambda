package lambda;

import java.util.*;

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
        minBul(sayi);
        System.out.println("\n  ***  ");
        bestenBykEnKck(sayi);
        ciftKareKbPrint(sayi);
        System.out.println("\n  ***  ");
        tekKareBkPrint(sayi);

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
 yaptigimiz action'dan tek bir sonuc cikacaksa reduce kullanilir
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
                reduce(Integer::max));//36 specific class daha hizli run olur
    }

    // Task : List'teki tum elemanlarin toplamini yazdiriniz.
    //Lambda Expression...

    public static void elTopla(List<Integer> sayi){
     int toplam=sayi.
             stream().
             reduce(0,(a,b)->a+b); //Lambda Expression...

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
                reduce(1, (a, b) -> (a * b)));//reduce eger expression ile yapiliyorsa baslangic degeri vermen gerekir,
                                                     // isleme etki etmemesi gerekir verilen degerin
                                                    //bir de a,b gibi iki deger vermen gerekir t burada calismaz

    }

    // Task : List'teki elemanlardan en kucugunu 4 farklı yontem ile print ediniz.

    public static void minBul(List<Integer> sayi){
        //1. yontem Method Reference --> Integer class
        Optional<Integer> minSayiInteger=sayi.stream().reduce(Integer::min);
        System.out.println(minSayiInteger);
        //2. yontem Method Reference --> Math class
        Optional<Integer> minSayiMath= sayi.stream().reduce(Math::min);
        System.out.println(minSayiMath);
        //3. yontem Lambda Expression
        int minSayiJambda= (sayi.stream().reduce(Integer.MAX_VALUE, (x, y) -> x < y ? x : y)); // baslangic degeri varsa optional'a gerek yok
        System.out.println(minSayiJambda);                                                        //identity isleme etki etmemeli
        //4. yontem Method Reference --> Haluk class
       Optional<Integer> minSayiHaluk=sayi.stream().reduce(Lambda02::byHalukMin);
        System.out.println(minSayiHaluk);
    }
    public static int byHalukMin(int a,int b){//bu method kendisine iki int degerin en kucugunu return eder

        return a<b?a:b;
    }


    // Task : List'teki 5'ten buyuk en kucuk tek sayiyi print ediniz.

    public static void bestenBykEnKck(List<Integer> sayi){
        System.out.println(sayi.stream().filter(t -> t % 2 != 0 && t > 5).reduce(Lambda02::byHalukMin));
    }

    // Task : list'in cift  elemanlarinin karelerini  kucukten buyuge print ediniz.

    public static void ciftKareKbPrint(List<Integer> sayi){
        sayi.
                stream().//akisa alindi
                filter(Lambda01::ciftBul).//cift elemanlar filtrelendi
                map(t->t*t).//filtrelenen cift sayi karesi alindi
                sorted().//karesi alinan elemanlar dogal(k->b) siralandi
                forEach(Lambda01::yazdir);//print edildi

        //sorted() => Doğal düzene göre sıralanmış, bu akışın elemanlarında oluşan bir akış döndürür.
        //Sorted() methodu tekrarlı kullanılırsa en son kullanılan aktif olur.
    }

    // Task : list'in tek  elemanlarinin kareleri ni buykten kucuge  print ediniz.

    public static void tekKareBkPrint(List<Integer> sayi){
        sayi.//akis kaynagi
                stream().//akisa alindi
                filter(t->t%2==1).//tek elemanlar filtrelendi
                map(t->t*t).//filtrelenen cift sayi karesi alindi
                sorted(Comparator.reverseOrder()).//karesi alinan elemanlar (b->k) siralandi
                forEach(Lambda01::yazdir);//print edildi


    }

}

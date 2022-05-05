package lambda;

import java.util.*;
import java.util.stream.Stream;

public class Lambda03 {
    public static void main(String[] args) {

        List<String> menu = new ArrayList<>(Arrays.asList("kusleme", "adana", "trilece", "havucDilim",
                "buryan", "yaglama", "kokrec", "arabAsi", "guvec"));

        alfBykTekrarsiz(menu);
        System.out.println("\n  ***  ");
        chrSayisiTersSirali(menu);
        System.out.println("\n  ***  ");
        chrSayisiBkSirala(menu);
        System.out.println("\n  ***  ");
        sonHrfBkSirala(menu);
        System.out.println("\n  ***  ");
        charKaresiCiftElemanSirala(menu);
        System.out.println("\n  ***  ");
        harfSayisi7denAzKontrol(menu);
        System.out.println("\n  ***  ");
        wIleBaslayanElemanKontrol(menu);
        System.out.println("\n  ***  ");
        xIleBitenElemanKontrol(menu);
        System.out.println("\n  ***  ");
        charSayisiBykElPrint(menu);
        System.out.println("\n  ***  ");
        ilkElHaricSonHrfSiraliPrint(menu);


    }

    // Task : List elemanlarini alafabetik buyuk harf ve  tekrarsiz print ediniz.

    public static void alfBykTekrarsiz(List<String> yemek) {
        yemek.//akis kaynagi
                stream().//akisa girdi
                // map(t->t.toUpperCase()).//jamb.Ex elemanlar buyuk harf update edildi
                        map(String::toUpperCase).//meth.ref elemanlar buyuk harf update edildi
                sorted().//alfabetik(natural dogal)sira yapildi
                distinct().//benzersiz : tekrarsiz hale getirildi
                forEach(t -> System.out.print(t + " "));//print edildi

        //distinct() => Bu method tekrarlı elemanları sadece bir kere akısa sokar.
        // Bu akışın farklı elemanlarından (Object.equals (Object) 'e göre) oluşan bir akış döndürür.
        // Sıralı akışlar için, farklı elemanın seçimi sabittir
        // (yinelenen öğeler için, karşılaşma sırasında ilk görünen öğe korunur.)
        // Sırasız akışlar için, herhangi bir kararlılık garantisi verilmez. Stream return eder.

    }

    // Task : list elelmanlarinin character sayisini ters sirali olarak tekrarsiz print ediniz..

    public static void chrSayisiTersSirali(List<String> menü) {
        menü.
                stream().//akisa alindi
                // map(t->t.length()).
                        map(String::length).//elemanlar karakter sayisina update edildi.Siralamayi istedigimiz gibi action'a alabilirim
                sorted(Comparator.reverseOrder()).//ters sira yapildi
                distinct().//benzersiz yapildi
                //forEach(t->System.out.print(t+" "));
                        forEach(Lambda01::yazdir);//print edildi

    }

    // Task : List elemanlarini character sayisina gore kckten byk e gore print ediniz..

    public static void chrSayisiBkSirala(List<String> menü) {
        menü.
                stream().//akisa alindi
                sorted(Comparator.//comparator class'ina gidip length'e gore siraliyor
                comparing(String::length)).//comparing karsilastir
                forEach(t -> System.out.print(t + " "));

    }

    // Task : list elemanlarinin son harfine gore ters sirali print ediniz.

    public static void sonHrfBkSirala(List<String> menü) {
        menü.
                stream().
                sorted(Comparator.
                        comparing(t -> t.toString().//karsilastirmadan once string null kabul eder,null hicbir isleme alinmaz
                                // o yuzden bunu toString ile hiclige cevirdik
                                //tostring olmasaydi charAt gelmezdi
                                        charAt(t.toString().length() - 1)).
                        reversed()).//ters siraladik
                forEach(t -> System.out.print(t + " "));


    }

    // Task : listin elemanlarin karakterlerinin cift sayili  karelerini hesaplayan,ve karelerini tekrarsiz buyukten kucuge sirali  print ediniz..

    public static void charKaresiCiftElemanSirala(List<String> menü) {
        menü.
                stream().//akisa alindi
                map(t -> t.length() * t.length()).//elemanlar string o yuzden burada sayiya cevirdik
                filter(Lambda01::ciftBul).//cift elemanlar filtrelendi
                distinct().//benzersiz yapildi
                sorted(Comparator.reverseOrder()).//buyukten kucuge siraladik
                forEach(Lambda01::yazdir);

    }

    // Task : List elemanlarinin karakter sayisini 7 ve 7 'den az olma durumunu kontrol ediniz.

    public static void harfSayisi7denAzKontrol(List<String> menu) {
        //amele code
        boolean kontrol = menu.
                stream().
                allMatch(t -> t.length() <= 7);

        if (kontrol) {
            System.out.println("List elemanlari 7 ve daha az harften olusuyor.");
        } else {
            System.out.println("List elemanlari 7 harften buyuk.");
        }

        //cincix code
        System.out.println(menu.stream().allMatch(t -> t.length() <= 7) ?
                "List elemanlari 7 ve daha az harften olusuyor." : "List elemanlari 7 harften buyuk.");

        //anyMatch() --> en az bir eleman sarti saglarsa true aksi durumda false return eder
        //allMatch() --> tum  elemanlar sarti saglarsa true en az bir eleman sarti saglamazsa false return eder.
        //noneMatch()--> hic bir sarti SAGLAMAZSA true en az bir eleman sarti SAGLARSA false return eder.

    }

    // Task : List elelmanlarinin "W" ile baslamasını kontrol ediniz.

    public static void wIleBaslayanElemanKontrol(List<String> menu) {
        System.out.println(menu.
                stream().
                noneMatch(t -> t.startsWith("w")) ? //kucuk buyuk hassasiyeti yok bu sekilde
                "w ile baslayan yemahh olu mu" :
                "agam wenemen ne menen bir sey? ");
    }

    // Task : List elelmanlarinin "x" ile biten en az bir elemanı kontrol ediniz.

    public static void xIleBitenElemanKontrol(List<String> menu) {
        System.out.println(menu.
                stream().
                anyMatch(t -> t.endsWith("x")) ? //kucuk buyuk hassasiyeti yok bu sekilde endswith oncesi t ile de yapabilirsin
                //eger olsun istiyorsan anymatch oncesi map(String::toUpperCase). yazabilirsin
                "agam senden bir cacix olmaz.." :
                "agam senin aradigin yemek bu torpaklarda yoooogg? ");
    }

    // Task : Karakter sayisi en buyuk elemani yazdiriniz.

    public static void charSayisiBykElPrint(List<String> menu) {
      Stream<String> sonIsim= menu.
                stream().
                sorted(Comparator.
                        comparing(t -> t.toString().length()).//String ifadelerde t'yi bazen java compile edemiyor
                        // length calisacakca String de calisir. Akisa giren elemanlar
                        // ne olursa olsun once bir toString yapmak gerekiyor
                        //ondan sonra String methodlarini kullan
                                reversed()). //tersten siraladi
                        //findFirst());//ilk eleman alindi
                limit(3);//limit(a) akısdan cıkan elemanları a parametresine gore ilk a elamanı alır

        /*
        sonIsim.toArray() --> limit() meth return dan dolayi stream type olan sonIsim
        toArray() method ile array type convert edildi
         */

        System.out.println(Arrays.toString(sonIsim.toArray()));//array'a cevrilen sonisim stream print edildi

        //limit(1) => Sınırlandırma demek. Bu akışın elemanlarından oluşan, uzunluğu maxSize'dan uzun olmayacak
        // şekilde kesilmiş bir akış return eder. Stream return eder. (referans yazdiriyor)

        /*
  TRİCK : •    Stream'ler ekrana direk yazdırılamaz. Stream'i toArray() ile Array'e çeviririz.
  Array'i de Arrays.toString() 'in içine alıp yazdırabiliriz.
•  Ör; System.out.println(Arrays.toString(stream.toArray())); veya System.out.println(Arrays.asList(***.toArray())); kullanılabilir.
   */

        //akis ciktisini bir varieble assign edilebilir.

        Optional<String> enBykKrEl = menu.
                stream().
                sorted(Comparator.
                        comparing(t -> t.toString().length()).
                        reversed()).
                findFirst();

        System.out.println(enBykKrEl);
    }

    // Task : list elemanlarini son harfine göre siralayıp ilk eleman hariç kalan elemanlari print ediniz.

    public static void ilkElHaricSonHrfSiraliPrint(List<String> menu) {
        menu.
                stream().//akisa alindi
                sorted(Comparator.comparing(t -> t.charAt(t.length() - 1))).//son harfe gore siralandi
                skip(1).//ilk eleman atlandi
                forEach(t -> System.out.print(t + " "));

    //skip(1) => atlama demek. Akışın ilk n elemanını attıktan sonra bu akışın kalan elemanlarından oluşan bir akış return eder.
    // Bu akış n'den daha az öğe içeriyorsa, boş bir akış döndürülür. Bu, durum bilgisi olan bir ara işlemdir.
    //skip(list.size()-1) => List'in uzunluğunun 1 eksiğini yazarsak son elemanı yazdırırız.

    }

}

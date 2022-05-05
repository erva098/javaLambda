package lambda;

import java.util.*;
import java.util.stream.Collectors;

public class Lambda04 {
    /*
TASK :
fields --> Universite (String)
           bolum (String)
           ogrcSayisi (int)
           notOrt (int)
           olan POJO clas craete edip main method içinde 5 arklı obj'den List create ediniz.
 */
    public static void main(String[] args) {
        Universite bogazici = new Universite("bogazici", "matematik", 571, 93);
        Universite itu = new Universite("istanbul teknik", "matematik", 622, 81);
        Universite istanbul = new Universite("istanbul", "hukuk", 1453, 71);
        Universite marmara = new Universite("marmara", "bilgisayar muh", 1071, 77);
        Universite ytu = new Universite("yıldız teknik", "gemi", 333, 74);
        List<Universite> unv = new ArrayList<>(Arrays.asList(bogazici, itu, istanbul, marmara, ytu));

        System.out.println("Task01 : "+notOrt74BykUnv(unv));
        System.out.println("\n  ***  ");
        System.out.println("Task02 : "+ogrcSayisi110AzMi(unv));
        System.out.println("\n  ***  ");
        System.out.println("Task03 : "+matBolumVarmi(unv));
        System.out.println("\n  ***  ");
        System.out.println("Task04 : "+ogrSayiBkSirala(unv));
        System.out.println("\n  ***  ");
        ogrcSayiBkSiralaVoid(unv);
        System.out.println("\n  ***  ");
        System.out.println("Task05 : "+notOrtBkSiraliIlkUc(unv));
        System.out.println("\n  ***  ");
        System.out.println("Task06 : "+enAzOgrSayisi2Unv(unv));
        System.out.println("\n  ***  ");
        System.out.println("MapToInt Task07: "+notOrt63BykUnvOgrcSayisiToplaToInt(unv));
        System.out.println("\n  ***  ");
        System.out.println("Task07 : "+notOrt63BykUnvOgrcSayisiTopla(unv));
        System.out.println("\n  ***  ");
        System.out.println("Task08 : "+ogrcSayisi333BykNotOrtOrtalamaAl(unv));
        System.out.println("\n  ***  ");
        System.out.println("Task09 : "+mathBolmSayisi(unv));
        System.out.println("\n  ***  ");
        System.out.println("Task10 : "+ogrcSayisi571BykMaxNotOrt(unv));
        System.out.println("\n  ***  ");
        System.out.println("Task11 : "+ogrcSayisi1071AzMinNotOrt(unv));


    }

    //task 01--> notOrt'larinin 74' den buyuk olup olmadigini kontrol eden print create ediniz.

    public static boolean notOrt74BykUnv(List<Universite> unv){
        return unv.
                stream().
                allMatch(t->t.getNotOrt()>74);//allMatch hepsi 74 den buyuk mu,kucuk olan da var o yuzden false
    }

    //task 02-->ogrc sayilarinin   110 dan az olmadigini  kontrol eden pr create ediniz.

    public static boolean ogrcSayisi110AzMi(List<Universite> unv){
      return  unv.
              stream().
              allMatch(t->t.getOgrcSayisi()>110);
    }

    //task 03-->universite'lerde herhangi birinde "matematik" olup olmadigini  kontrol eden pr create ediniz.

    public static boolean matBolumVarmi(List<Universite> unv){
      return  unv.
              stream().
              anyMatch(t->t.getBolum().toLowerCase().contains("mat"));//true
    }


    //task 04-->universite'leri ogr sayilarina gore b->k siralayiniz.

    public static List<Universite> ogrSayiBkSirala(List<Universite> unv) {
        return unv.
                stream().
                sorted(Comparator.comparing(Universite::getOgrcSayisi).reversed()).//akisi duzenledi
                collect(Collectors.toList());//collect()->akisdaki elemanlari istenen sarta gore toplar
        //Collectors.toList()->collect'e toplanan elemanlarilist'e cevirir
    }

    public static void ogrcSayiBkSiralaVoid(List<Universite> unv){
        System.out.println(unv.
                stream().
                sorted(Comparator.comparing(Universite::getOgrcSayisi).reversed()).
                collect(Collectors.toList()));

    }


    //task 05-->universite'leri notOrt gore  b->k siralayip ilk 3 'unu print ediniz.

    public static List<Universite>  notOrtBkSiraliIlkUc(List<Universite> unv){
     return  unv.
                stream().//akisa alindi
                sorted(Comparator.comparing(Universite::getNotOrt).reversed()).//notOrt gore b-k siralandi
                limit(3).//akisin ilk 3 elemani alindi
                collect(Collectors.toList());//akisin ilk 3 elemani list'e assign edildi
                //toList());akisin ilk 3 elemani list'e assign edildi
    }


    //task 06--> ogrc sayisi en az olan 2. universite'yi  print ediniz.

    public static List<Universite> enAzOgrSayisi2Unv(List<Universite> unv){
       return unv.
               stream().
               sorted(Comparator.comparing(Universite::getOgrcSayisi)).
               limit(2).
               skip(1).//akis burada devam ediyor
               collect(Collectors.toList());//o yuzden bir yere baglamak gerikiyordu.Collect toplamakti
    }


    //task 07--> notOrt 63 'den buyuk olan universite'lerin ogrc sayilarini toplamini print ediniz.

    public static int notOrt63BykUnvOgrcSayisiTopla(List<Universite> unv){
      return   unv.
              stream().
              filter(t->t.getNotOrt()>63).
              map(t->t.getOgrcSayisi()).//akistaki objeler ogrSayisina cevrildi
              //reduce(Integer::sum); optional gerekir
              //reduce(Math::addExact); optional gerekir
              reduce(0,(t,u)->t+u);//burada optional istemiyor cunku ilk deger var



    }

    public static int notOrt63BykUnvOgrcSayisiToplaToInt(List<Universite> unv){
        return   unv.
                stream().
                filter(t->t.getNotOrt()>63).
                mapToInt(t->t.getOgrcSayisi()). //akis elemanlarini int data type olarak convert eder
                                                //akistaki elemanlari isterseniz ozel bir data type'a cevrebilirsiniz
                sum();
        // mapToInt() --> bu method akistaki elemanlarin data type'ini parametresindeki
        //degere gore int data type update eder

    }


    //task 08--> Ogrenci sayisi 333'dan buyuk olan universite'lerin notOrt'larinin ortalamasini bulunuz.

    public static OptionalDouble ogrcSayisi333BykNotOrtOrtalamaAl(List<Universite> unv){
     return   unv.
             stream().
             filter(t->t.getOgrcSayisi()>333).
             mapToDouble(t->t.getNotOrt()).
             average();//akistaki elemanlarin ort alir
        // mapToDouble() --> bu method akistaki elemanlarin data type'ini parametresindeki
        //degere gore double data type update eder
    }


    //task 09-->"matematik" bolumlerinin sayisini  print ediniz.

    public static int mathBolmSayisi(List<Universite> unv){//cast yerine method'u long yapsak da olur
      return (int) unv.
              stream().
              filter(t->t.getBolum().contains("matematik")).
              count();
              //count();---> akistaki eleman sayisini return eder 2
    }


    //task 10-->Ogrenci sayilari 571'den fazla olan universite'lerin en buyuk notOrt'unu bulunuz.

    public static OptionalInt ogrcSayisi571BykMaxNotOrt(List<Universite> unv){
      return  unv.
                stream().//akis
                filter(t->t.getOgrcSayisi()>571).//unv obj akisi filtrelendi
                mapToInt(t->t.getNotOrt()).//akistaki unv objesi notOrt akisi olarak update edildi
                max();//akisin en byk degerini return eder.MapToInt olmazsa max() method'u calismaz
    }


    //task 11-->Ogrenci sayilari 1071'den az olan universite'lerin en kucuk notOrt'unu bulunuz.

    public static OptionalInt ogrcSayisi1071AzMinNotOrt(List<Universite> unv){
      return  unv.
                stream().
                filter(t->t.getOgrcSayisi()<1071).
                mapToInt(t->t.getNotOrt()).
                min();
    }

}

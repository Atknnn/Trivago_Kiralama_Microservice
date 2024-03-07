package com.atakan.junittest;

import com.atakan.utility.Islemler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test01_YasamDongusu {
    /**
     * Uygulamaları geliştirirken en çok düşülen sorunlardan birini gidermek için yapılır. Kodun
     * düzenlenmesi ve revizyonu.
     * Test bir method için yazılır. Ancak bir methodun tüm ihtimalleri için ayrı ayrı testler yazılmalıdır.
     * Testlerin erişim belirteçleri olmaz,
     * İşlem yapıp bittikleri için geri değer dönmelerine gerek yoktur. Void
     * Bir methodun test olarak çalışması için üzerinde @Test anotasyonu eklenir.
     */
    @Test
    void IlkTest(){
        Islemler islemler = new Islemler();
        int toplam = islemler.toplam(43,34);
        Assertions.assertEquals(toplam,77);
        System.out.println("test yapptık");
    }

}

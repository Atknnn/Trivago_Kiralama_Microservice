package com.atakan.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ZamanlanmisGorevler {

    @Value("${my-applicaton.date}")
    Date date;

    /**
     * * * * * * *
     * - - - - - -
     * | | | | | |
     * | | | | | +--- haftanın günü (0 - 6) (Pazar = 0 veya 7)
     * | | | | +----- ay (1 - 12)
     * | | | +------- ayın günü (1 - 31)
     * | | +--------- saat (0 - 23)
     * | +----------- dakika (0 - 59)
     * +------------- saniye (0 - 59)  [isteğe bağlı]
     * her zamanın 50 ve katları saniyesinde çalışır
     */
    @Scheduled(cron = "* */60 * * * *")
    public void calisZamanli(){
        System.out.println("ZamanlanmisGorevler çalıştı...: "+ new Date());
    }
}

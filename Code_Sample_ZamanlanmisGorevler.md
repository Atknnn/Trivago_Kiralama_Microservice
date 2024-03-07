Java'da zamanlanmış görevler genellikle `Timer` ve `TimerTask` sınıflarını veya `ScheduledExecutorService` kullanarak oluşturulur. İşte her iki yaklaşım için de örnekler:

**1. Timer ve TimerTask Kullanımı:**

```java
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Görev çalıştı.");
            }
        };

        // Görevi 5 saniye sonrasına zamanla ve her 5 saniyede bir tekrarla
        timer.schedule(task, 5000, 5000);
    }
}
```

**2. ScheduledExecutorService Kullanımı:**

```java
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Görev çalıştı.");

        // Görevi 5 saniye sonrasına zamanla ve her 5 saniyede bir tekrarla
        executor.scheduleAtFixedRate(task, 5, 5, TimeUnit.SECONDS);
    }
}
```
    
    Bu kod parçaları, görevinizi belirli bir gecikme sonrasında başlatıp belirli bir periyotta tekrarlar. 
    İlk argüman gecikmeyi, ikinci argüman ise tekrarlama periyodunu belirtir (her iki durumda da milisaniye olarak).
    
    Eğer tekrarlanmayan bir görev oluşturmak isterseniz, `timer.schedule(task, delay)` veya `executor.schedule(task, delay, unit)` 
    metodlarını kullanabilirsiniz. Bu durumda, belirttiğiniz gecikme sonrasında görev bir kere çalışacaktır.
    

## SPRİNG BOOT İLE ZAMANLANMIŞ GÖREVLER

        Spring Boot ile zamanlanmış görevler oluşturmak için `@EnableScheduling` ve `@Scheduled` anotasyonlarını kullanabilirsiniz. 
    Öncelikle `@EnableScheduling` anotasyonunu `@SpringBootApplication` anotasyonu ile işaretlenmiş sınıfınıza ekleyin:    
        Spring Boot'taki `@Scheduled` anotasyonu `cron` parametresi için cron tabanlı zamanlama kullanır. 
    Cron ifadeleri genellikle UNIX tabanlı sistemlerde zamanlanmış görevler oluşturmak için kullanılır ve Spring Boot da
    bu ifadeleri zamanlamayı tanımlamak için kullanır.    
        Cron ifadesi genellikle 6 veya 7 alan (saniye, dakika, saat, gün, ay, gün-of-week, yıl) içerir. 
    Her alan belirli bir zaman birimini temsil eder.

```
* * * * * *
- - - - - -
| | | | | |
| | | | | +--- haftanın günü (0 - 6) (Pazar = 0 veya 7)
| | | | +----- ay (1 - 12)
| | | +------- ayın günü (1 - 31)
| | +--------- saat (0 - 23)
| +----------- dakika (0 - 59)
+------------- saniye (0 - 59)  [isteğe bağlı]
```

    Örneğin, her Pazartesi saat 00:00'da çalışacak bir işlem için ifade `"0 0 0 * * MON"` şeklinde olacaktır. Burada:

    - İlk `0`: Dakikanın 0. saniyesinde
    - İkinci `0`: 0. dakikada
    - Üçüncü `0`: 0. saatte (yani gece yarısı)
    - `*`: Ayın her günü
    - `*`: Her ay
    - `MON`: Haftanın Pazartesi günü

        Bu, görevin her ayın her günü, yani her Pazartesi gece yarısı çalıştırılacağını belirtir. Belirli bir zaman 
    aralığı için birden çok değer belirtmek istiyorsanız virgül kullanabilirsiniz. Örneğin, her Pazartesi ve 
    Çarşamba günü saat 00:00'da çalışacak bir işlem için ifade `"0 0 0 * * MON,WED"` şeklinde olacaktır.
        Belirli bir zaman aralığında her x birimde çalıştırmak için bölü `/` işaretini kullanabilirsiniz. 
    Örneğin, her 5 dakikada bir çalışacak bir işlem için ifade `"0 */5 * * * *"` şeklinde olacaktır.
        Cron ifadeleri oldukça esnek olduğu için birçok farklı zamanlama ihtiyacını karşılayabilir.
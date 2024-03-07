# KURULUMLAR

## MongoDB İşlemler

    Not: MongoDB ile işlem yaparken admin kullanıcısı ve admin şifresi kullanılmamalıdır. Bu nedenle 
    oluşturulacak her DB için yeni kullanıcı ve şifre tanımlanır.
    1- Önecelikle DB yi tanımlayın
    2- üzerinde çalışma yapabilme için mongoDb Compass üzerinden MONGOSH ı açın (sol en altta)
    3- "use DB_Adı" şeklinde komut girilir.
    4- bu DB yi yönetecek olan bir kullanıcı tanımlıyorsunuz.
    db.createUser({
        user: "defaultUSer",
        pwd: "bilge!*123",
        roles: ["readWrite", "dbAdmin"]
    })

    -> db.createUser({user: "defaultUser",pwd: "bilge!*123",roles: ["readWrite", "dbAdmin"]})

### DOCKER ÜZERİNDE MONGODB KURULUMU

    docker run --name mongodb -e "MONGO_INITDB_ROOT_USERNAME=admin" -e "MONGO_INITDB_ROOT_PASSWORD=root" -p 27017:27017 mongo:7.0-rc-jammy


### DOCKER POSTGRESQL KURULUMU

     docker run --name postgresdb -e POSTGRES_PASSWORD=root -p 5433:5432 -d postgres

### DİKKAT Mac M1,M2,M3 işlemcilerde docker imaj oluştururken yapıalcak işlem

    --platform linux/amd64 komutu eklenmelidir.
    docker build -t javaboost2/java11boostauthmicroservice:v003 --platform linux/amd64 .

### DOCKER REDIS KURULUMU

    docker run --name lokalredis -d -p 6379:6379 redis:7.2.3-alpine3.18

### DOCKER ELASTICSEARCH KURULUMU
    - xpack.security.enabled=true

    - xpack.security.transport.ssl.enabled=true

    docker run -d -p 9200:9200 -p 9300:9300 -e "xpack.security.enabled=false" -e "xpack.security.transport.ssl.enabled=false"  -e "ELASTIC_USERNAME=admin" -e "ELASTIC_PASSWORD=root" -e "ES_JAVA_OPTS=-Xms512m -Xmx1024m" -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:8.7.1

###   DOCKER DESKTOP ÜZERİNDE UYGULAMALARIMIZI YÜKLEME KOMUTLARI
    Sırası ile lokalde kurulması gerekenler
    1- docker run --name mongodb -e "MONGO_INITDB_ROOT_USERNAME=admin" -e "MONGO_INITDB_ROOT_PASSWORD=root" -p 27017:27017 mongo:7.0-rc-jammy
    1.1 kurulumun ardından mongo compas ile bağlanıp, mongosh içinde yeni bir yetkili kullanıcı oluştur
    1.1.1 db.createUser({user: "defaultUser",pwd: "bilge!*123",roles: ["readWrite", "dbAdmin"]})
    2- docker run --name postgresdb -e POSTGRES_PASSWORD=root -p 5433:5432 -d postgres
    3- docker run -d --name some-rabbit -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER="BilgeAdmin" -e RABBITMQ_DEFAULT_PASS="Aa123456" rabbitmq:3-management
    
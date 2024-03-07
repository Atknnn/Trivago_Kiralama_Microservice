# Öncelikle uygulamanızın çalışabileceği bir imaj kullanıyorsunuz
# Burada içinde OS ve Java JDK olan bir versionu hub.docker.com dan
# seçerek buraya ekliyorsunuz.
FROM amazoncorretto:17
# Uygulamanızın jar dosyasını oluşturacağınuz docker conrtainer içine
# COPY ile ekliyorsunuz. Dikkat ederseniz COPY [DOSYANIZ] [KOPYALANAN YER]
COPY build/libs/AuthMicroService-v.0.1.jar app.jar
# Container ayağa kaldırıldığında çalışması için gerekli komutları işletecek
# kısmı yazıyorsunuz.
ENTRYPOINT ["java","-jar","/app.jar"]
# Bu dosyayı kullanarak yeni bir docker imge oluşturmak için
# docker build komutunu kullanıyoruz. tam olarak komut şu şekildedir.
# docker build -t [hub.docker repository name]/[project Name]:[version] .
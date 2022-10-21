package lesson5.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import static javax.swing.text.html.HTML.Tag.BODY;


/*утилитарный класс(все методы должны быть статическими, для
того что бы не прописывать лишний раз static, не должен иметь
экземпляров
 */

@UtilityClass
public class RetrofitUtils {
    Properties prop = new Properties();//создается объект property
    private static InputStream configFile;//читается из файла

    static {
        try {
            configFile = new FileInputStream("src/main/resources/my.properties");
            //путь к файлу
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    // может генерировать exeption-ы  и скрывает их
    @SneakyThrows
    public String getBaseUrl() {
        prop.load(configFile);// метод load в классе property гененерит in out exeption
        return prop.getProperty("url");
    }


    //определяем перехватики
    //интерсептор который предлагается библиотекой
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    //самостоятельно созданный интерсептор
    LoggingInterceptor logging2 = new LoggingInterceptor();
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();//доступ к самому клиенту  okhttp3 для настройки


// по факту в RetrofitUtils может быть нескольео retrofit клиентов, с разным сожердимым и билдерами


/*    public Retrofit getRetrofit(){//статический метод(т.к. есть Util), обращение к нему напрямую//он настраивает библеотеку retrofit
        return new Retrofit.Builder()// настраивается через билдер
                .baseUrl(getBaseUrl())// в нашем случае настраивает только url
                //фабрика , указывается что в качестве сериализации
                 десериализации будет исп.библиотека джексон
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }*/

    public Retrofit getRetrofit(){
        //определяем уровень логирования
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        return new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())//создаем и настраиваем Http клиент на использование перхватчика- указывает тот что определили для данного объекта
                .client(httpClient.build())
                .build();
    }




}
package lesson5.api;

import lesson5.dto.GetCategoryResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface CategoryService {
    //1 шаг:создание интерфейса-сервиса с методом

    /* с помощью соответствующей тип-аннотации вызываем
     необходимый метод// значение value- это суффикс
     (динамическая часть адреса
     */

    @GET("categories/{id}")
    //возвращаемое значение
    Call<GetCategoryResponse> getCategory(@Path("id") int id);
    //слева-объект call-вызов внутри которого опредеяем возввращаемый объект, обрабатываемый по итогам взаимодействия
    //<GetCategoryResponse>- это наш json-м.б. автосгенерирован
    // справа- указываем название метода и то, что вспавляем в запрос-path=pathparam, query= queryparams

}

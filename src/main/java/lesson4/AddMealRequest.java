package lesson4;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

//https://nsergey.com/jackson-annotations/- cайт с аннотациями

//  если какое то значение будет null мы не передаем его в json
@JsonInclude(JsonInclude.Include.NON_NULL)

//Порядок свойств,порядок появления полей.
@JsonPropertyOrder( {
        "date",
        "slot",
        "position",
        "type",
        "value"
})

/*Lombok -  @ToString, @EqualsAndHashCode, @Getter @Setter и @RequiredArgsConstructor.
@Data генерирует весь шаблонный код работащий с объектами POJO (Plain Old Java Objects).
, также конструктор. Но скрывает их.
 */
@Data
public class AddMealRequest {

    //поле, которое надо сериализовать или десериализовать
    @JsonProperty("date")// возможность указать наименование json переменной , которая отличается от наименования переменной класса
    private Integer date;
    @JsonProperty("slot")
    private Integer slot;
    @JsonProperty("position")
    private Integer position;
    @JsonProperty("type")
    private String type;
    @JsonProperty("value")
    private Value value;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder( {
            "ingredients"
    })
    @Data
    private static class Value {
        @JsonProperty("ingredients")
        private List<Ingredient> ingredients = new ArrayList<Ingredient>();
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder( {"name"})
    @Data
    private static class Ingredient {
        @JsonProperty("name")
        private String name;
    }
}

package Lesson4;

import lesson4.Builder.Builder;
import lesson4.Builder.MyClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExampleMyClassBuilderTest{
    @Test
    void createObject(){

        //создание объекта через геттеры и сеттеры
        MyClass myClass1 = new MyClass();
        myClass1.setAttr1("");
        myClass1.setAttr2(true);
        myClass1.setAttr3(1l);


        // создаем экземпляр класса через конструктор,сразу прописывая атрибуты
        MyClass myClass2 = new MyClass("",true,1l);

        //через паттерн проектирования Builder- позволяет создать экземпляр с лишь необходитмыми атрибутами в данный момент
        MyClass myClass3 = new Builder()// создаем еще один класс Builder( в названии-builder)
                // указываем сразу необходимые атрибуты через их сеттеры
                .setAttr1("")
                .setAttr2(true)
                .setAttr3(1l)
                .createMyClass();// в конце вызываем билд

        Assertions.assertEquals(myClass1,myClass3);
        Assertions.assertFalse(myClass1==myClass3);
    }
}

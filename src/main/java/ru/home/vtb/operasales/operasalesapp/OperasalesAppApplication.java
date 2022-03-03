package ru.home.vtb.operasales.operasalesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.home.vtb.operasales.operasalesapp.services.OperaService;

@SpringBootApplication
public class OperasalesAppApplication {

    public static void main(String[] args) {

       final ConfigurableApplicationContext ctx = SpringApplication.run(OperasalesAppApplication.class, args);
       
       // Да, забыл "приватизировать" на радостях, что начало получаться!!!
       // хотя помню, что все поля должны ТОЛЬКО из методов редактироваться !
       // Всё правильно делаете! критика приветствуется.

       // А структуру я сразу попытался сымитировать под работу с базой - вроде похоже ;-)
       // далее ещё работаю с аспектами и "интерфейсами". Скоро будет вторая чать.
       // Сложно идёт перенастройка сознания под новые методы программирования.
       // А программить мы умеем - надо всего лишь методы освоить.

       // сформируем список релизов
       ctx.getBean(OperaService.class).newOpera("Борис Годунов", "05.05.2022","16+", "Душераздирающая история про русского царя.");
       ctx.getBean(OperaService.class).newOpera("Отелло", "06.05.2022","16+", "Спойлер. Мавр таки убийца.");
       ctx.getBean(OperaService.class).newOpera("Юнона и Авось", "07.05.2022","12+", "Красивая история про верность и любовь в период великого путешествия.");
       System.out.println(ctx.getBean(OperaService.class).toString());


       // продадим пару билетов на Отелло 1ряд, места 4 и 5
       // отобразим карту зала
       // для действий с билетами можно контекст присвоить переменной и работать уже с ней - так вроде проще
       System.out.println(ctx.getBean(OperaService.class).getOpera("Отелло", "06.05.2022").toString());
       // резервируем билеты
       ctx.getBean(OperaService.class).getOpera("Отелло", "06.05.2022").saleTiket(1,4);
       ctx.getBean(OperaService.class).getOpera("Отелло", "06.05.2022").saleTiket(1,5);
       // отобразим карту зала
       System.out.println(ctx.getBean(OperaService.class).getOpera("Отелло", "06.05.2022").toString());

       // продадим пару билетов на Борис Годунов 5 ряд, места 6 и 7
       System.out.println(ctx.getBean(OperaService.class).getOpera("Борис Годунов", "05.05.2022").toString());
       ctx.getBean(OperaService.class).getOpera("Борис Годунов", "05.05.2022").saleTiket(5,6);
       ctx.getBean(OperaService.class).getOpera("Борис Годунов", "05.05.2022").saleTiket(5,7);
       System.out.println(ctx.getBean(OperaService.class).getOpera("Борис Годунов", "05.05.2022").toString());

       // статус по билетам
       System.out.println(ctx.getBean(OperaService.class).toString());

       // сдадим один билет на Отелло 1х4
       System.out.println("сдадим один билет на Отелло 1х4");
       ctx.getBean(OperaService.class).getOpera("Отелло", "06.05.2022").dropTiket(1,4);

       // статус по билетам
       System.out.println(ctx.getBean(OperaService.class).toString());

       //УПС! Борис меняет дату релиза!
       System.out.println("\nУПС! Борис меняет дату релиза!");
       ctx.getBean(OperaService.class).changeOpera("Борис Годунов",
               "05.05.2022",
               "Борис Годунов",
               "15.05.2022",
               "16+",
               "Душераздирающая история про русского царя."
               );

       // статус по билетам
       System.out.println(ctx.getBean(OperaService.class).toString());

       //УПС! Отелло отменили!
       System.out.println("\nУПС! Отелло отменили!");
       ctx.getBean(OperaService.class).dropOpera("Отелло", "06.05.2022");

       // статус по билетам
       System.out.println(ctx.getBean(OperaService.class).toString());

    }


}

package ru.home.vtb.operasales.operasalesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.home.vtb.operasales.operasalesapp.operas.Opera;
import ru.home.vtb.operasales.operasalesapp.services.OperaService;

@SpringBootApplication
public class OperasalesAppApplication {

    public static void main(String[] args) {

       final ConfigurableApplicationContext ctx = SpringApplication.run(OperasalesAppApplication.class, args);
       Opera opera;
       String sResult = null;


       // сформируем список релизов
       opera = ctx.getBean(OperaService.class).newOpera("Борис Годунов", "05.05.2022","16+", "Душераздирающая история про русского царя.");
       opera = ctx.getBean(OperaService.class).newOpera("Отелло", "06.05.2022","16+", "Спойлер. Мавр таки убийца.");
       opera = ctx.getBean(OperaService.class).newOpera("Юнона и Авось", "07.05.2022","12+", "Красивая история про верность и любовь в период великого путешествия.");

       //Вывод описания опер
       //System.out.println(ctx.getBean(OperaService.class).toString());
//       System.out.println("Список релизов и доступных билетов:\n");
//       System.out.println(opera.toString());


       // продадим пару билетов на Отелло 1ряд, места 4 и 5
       // отобразим карту зала
       // для действий с билетами можно контекст присвоить переменной и работать уже с ней - так вроде проще
       opera = ctx.getBean(OperaService.class).getOpera("Отелло", "06.05.2022");
       //System.out.println( opera.toString());
       // резервируем билеты
       System.out.println("\nПокупаем билет на Отелло 06.05.2022 р1м4");
       try {
          sResult = ctx.getBean(OperaService.class).saleTiket(opera, 1, 4);
          //System.out.println(sResult);
       } catch (NullPointerException e){
          System.out.println(e.getMessage());
       }
       System.out.println("\nПокупаем билет на Отелло 06.05.2022 р1м5");
       try {
          sResult = ctx.getBean(OperaService.class).saleTiket(opera, 1, 5);
          System.out.println(sResult);
       } catch (NullPointerException e){
          System.out.println(e.getMessage());
       }

       // отобразим карту зала
       System.out.println("\n отобразим карту зала после покупки билета");
       System.out.println(opera.toString());


//       // сдадим один билет на Отелло 1х4
//       System.out.println("сдадим один билет на Отелло 1х4");
//       ctx.getBean(OperaService.class).getOpera("Отелло", "06.05.2022").dropTiket(1,4);
//
//       // статус по билетам
//       System.out.println(ctx.getBean(OperaService.class).toString());
//
       //УПС! Борис меняет дату релиза!
       System.out.println("\nУПС! Борис меняет дату релиза!");
       ctx.getBean(OperaService.class).changeOpera("Борис Годунов",
               "05.05.2022",
               "Борис Годунов",
               "15.05.2022",
               "16+",
               "Душераздирающая история про русского царя."
               );
//
//       // статус по билетам
//       System.out.println(ctx.getBean(OperaService.class).toString());
//
//       //УПС! Отелло отменили!
//       System.out.println("\nУПС! Отелло отменили!");
//       ctx.getBean(OperaService.class).dropOpera("Отелло", "06.05.2022");
//
//       // статус по билетам
//       System.out.println(ctx.getBean(OperaService.class).toString());

    }

}

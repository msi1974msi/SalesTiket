package ru.home.vtb.operasales.operasalesapp.services;

import org.springframework.stereotype.Service;
import ru.home.vtb.operasales.operasalesapp.annotation.SendMail;
import ru.home.vtb.operasales.operasalesapp.operas.Opera;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class OperaService {
    private Map<String, Opera> mapOpera = new HashMap<>(); // key = name + " " + sDate
    private Opera nOpera;

    public Opera getOpera(String name, String sDate){
        //System.out.println("Создаём новый релиз: "+ name + " " + sDate);
        nOpera = mapOpera.get(name + " " + sDate);
        if (nOpera == null) {
            System.out.println("Такого релиза нет!"+ name + " " + sDate);
        }
        return nOpera;
    }

    @SendMail
    public Opera newOpera(String name, String sDate, String category, String description){
        //System.out.println("Создаём новый релиз: "+ name + " " + sDate);
        nOpera = mapOpera.get(name + " " + sDate);
        if (nOpera == null) {
            //System.out.println("Создан новый релиз: "+ name + " " + sDate);
            nOpera = new Opera(name, sDate, category, description);
            mapOpera.put(name + " " + sDate,nOpera);
        }
        return nOpera;
    }

    public void dropOpera(String name, String sDate){
        String key = name + " " + sDate;
        nOpera = mapOpera.get(key);
        if (nOpera == null) {
            System.out.println("Такого релиза нет!" + name + " " + sDate);
        }  else {
            mapOpera.remove(key);
        }
    }

    @SendMail
    public Opera changeOpera(String name, String sDate, String newName, String newDate, String newCategory, String newDescription){
        String key = name + " " + sDate;
        String newKey = newName + " " + newDate;
        nOpera = mapOpera.get(key);
        if (nOpera == null) {
            System.out.println("Такого релиза нет!"+ name + " " + sDate);
        } else {
            mapOpera.remove(key);
            mapOpera.put(newKey, nOpera);
            nOpera.changeOpera(newName, newDate, newCategory,newDescription);
        }
        return nOpera;
    }

    @SendMail
    public String saleTiket(Opera opera, int row, int place) {
        int result = opera.saleTiket(row,place);
        String sResult = null;
        switch (result) {
            case 1: sResult = "Билет успешно продан" ;
                break;
            case 2: sResult = "Билет уже недоступен для покупки!";
                break;
            case 3: sResult = "Запрошенного билета не существует!";
                break;
        }
        if (result != 1) {
            //кидаем ошибку
            throw new NullPointerException(sResult);
        }
        return sResult;
    }

    @Override
    public String toString(){
        StringBuilder sOperas = new StringBuilder();
        int cnt = 0;
        Iterator<Map.Entry<String, Opera>> itr = mapOpera.entrySet().iterator();
        while (itr.hasNext()) {
            if (cnt == 0) {
                System.out.println("Предлагаем вам список релизов:\n");
                cnt++;
            }
            Map.Entry<String, Opera> entry =  itr.next();
            String key = entry.getKey();
            Opera opera = entry.getValue();
            sOperas.append(key + ".\n Количество свободных билетов = " + opera.countfreetikets() + "\n");
            sOperas.append("Возрастная категоря:" + opera.getCategory() + "\n");
            sOperas.append("Описание: " + opera.getDescription() + "\n");
            sOperas.append("----------------------------------------\n");
        }
        return String.valueOf(sOperas);
    }
}

package ru.home.vtb.operasales.operasalesapp.services;

import org.springframework.stereotype.Service;
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

    public Opera newOpera(String name, String sDate, String category, String description){
        //System.out.println("Создаём новый релиз: "+ name + " " + sDate);
        nOpera = mapOpera.get(name + " " + sDate);
        if (nOpera == null) {
            System.out.println("Создан новый релиз: "+ name + " " + sDate);
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

    @Override
    public String toString(){
        Iterator<Map.Entry<String, Opera>> itr = mapOpera.entrySet().iterator();
        StringBuilder sOperas = new StringBuilder();
        System.out.println("Предлагаем вам список релизов:\n");
        while (itr.hasNext()) {
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

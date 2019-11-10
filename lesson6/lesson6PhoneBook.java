public class PhoneNumberFormatException extends RuntimeException {
    public PhoneNumberFormatException() {
        super("Номер телефона может содержать только цифры и иметь длину 11 символов");
    }
}


import java.util.*;
import java.util.HashMap;

public class PhoneBook {
    Map<String, List<String>> phoneBook = new HashMap<>();

    public void add(String surname, String phoneNumber){
        if (phoneNumber.length() != 11 || !phoneNumber.matches("[\\d]+")){
            throw new PhoneNumberFormatException();
        }
        List<String> listPhone = new ArrayList<>();
        listPhone.add(phoneNumber);

        if (phoneBook.containsKey(surname)){
            listPhone.addAll(phoneBook.get(surname));
            phoneBook.put(surname, listPhone);
        }
        else{
            phoneBook.put(surname, listPhone);
        }
    }

    public List<String> get(String surname){
        return phoneBook.get(surname);
    }
}


import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        //Task 1
        List<String> myArray = new ArrayList<>(Arrays.asList("Москва","Пермь","Ульяновск"
        ,"Москва","Самара","Пермь","Краснодар","Тула","Москва","Красноярск"));

        Map<String, Integer> myMap = new HashMap<>();

        for (String str: myArray) {
            if(myMap.containsKey(str)){
                myMap.put(str,myMap.get(str).intValue()+1);
            }
            else {
                myMap.put(str,1);
            }
        }

        System.out.println("Массив слов: " + myArray);
        System.out.println("Сколько раз встречается каждое слово: " + myMap);
        System.out.println();
        System.out.println("Список слов из которых состоит массив:");
        for (Map.Entry<String,Integer> map:myMap.entrySet()) {
            System.out.println(map.getKey());
        }
        System.out.println();

        //Task 2
        PhoneBook myPhoneBook = new PhoneBook();

        myPhoneBook.add("Петров", "79520000000");
        myPhoneBook.add("Сидоров", "79520001111");
        myPhoneBook.add("Иванов", "79520000002");
        myPhoneBook.add("Иванов", "79520000003");
        myPhoneBook.add("Иванов", "79520000004");
        myPhoneBook.add("Сидоров", "74950001111");

        //Поиск по фамилии
        for (String str : myPhoneBook.get("Иванов")) {
            System.out.println(str);
        }
    }
}

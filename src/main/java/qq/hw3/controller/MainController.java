package qq.hw3.controller;

import qq.hw3.entity.Phone;
import qq.hw3.impl.PhoneCrudServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainController {
    private final PhoneCrudServiceImpl phoneCrudServiceMPO = new PhoneCrudServiceImpl();

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        menu();
        String position = "";
        while ((position = bufferedReader.readLine()) != null) {
            crud(position, bufferedReader);
            menu();
        }

    }

    private void menu() {
        System.out.println();
        System.out.println("If you want CREATE phone please enter 1");
        System.out.println("If you want UPDATE phone please enter 2");
        System.out.println("If you want DELETE phone please enter 3");
        System.out.println("If you want FIND ONE phone please enter 4");
        System.out.println("If you want FIND ALL phones please enter 5");
        System.out.println("If you want CLOSE app please enter 6");


    }


    private void crud(String position, BufferedReader bufferedReader) throws IOException {
        switch (position) {
            case "1" -> create(bufferedReader);
            case "2" -> update(bufferedReader);
            case "3" -> delete(bufferedReader);
            case "4" -> findOne(bufferedReader);
            case "5" -> findAll();
            case "6" -> System.exit(0);


        }
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("Please enter brand");
        String brand = reader.readLine();
        System.out.println("Please enter model");
        String model = reader.readLine();

        int year = 0;
        System.out.println("Please enter year");
        try {
            year = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Incorrect data.Please choose operation again and enter a number");
            return;
        }


        Phone phone = new Phone();
        phone.setBrand(brand);
        phone.setModel(model);
        phone.setYear(year);
        phoneCrudServiceMPO.createCRUD(phone);
    }

    private void findAll() {
        Phone[] phones = phoneCrudServiceMPO.findAllCRUD();

        for (int i = 0; i < phones.length; i++) {
            Phone phone = phones[i];
            if (phone != null) {
                System.out.println("serial number = " + phone.getSerialNumber());
                System.out.println("brand = " + phone.getBrand());
                System.out.println("model = " + phone.getModel());
                System.out.println("year = " + phone.getYear());
                System.out.println("---------------------------------------------------------------------------");
            }
        }
    }

    private void delete(BufferedReader reader) throws IOException {
        System.out.println("Please enter serial number to delete a phone");
        String sn = reader.readLine();
        try {
            phoneCrudServiceMPO.deleteCRUD(sn);
        } catch (NullPointerException e) {
            System.out.println("Serial number not found,please try again");
        }

    }


    private void update(BufferedReader reader) throws IOException {

        System.out.println("Please enter serial number to update a phone");
        String sn = reader.readLine();

        Phone[] phones = phoneCrudServiceMPO.findAllCRUD();

        for (int i = 0; i < phones.length; i++) {
            if (phones[i] != null && phones[i].getSerialNumber().equals(sn)) {
                System.out.println("Phone found");
                break;
            } else if (i == phones.length - 1) {
                System.out.println("Serial number not found,please try again");
                return;
            }

        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("");
        System.out.println("Please enter new brand");
        String brand = bufferedReader.readLine();
        System.out.println("Please enter new model");
        String model = bufferedReader.readLine();

        int year = 0;
        System.out.println("Please enter new year");
        try {
            year = Integer.parseInt(bufferedReader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Incorrect data.Please enter a year");
            year = Integer.parseInt(bufferedReader.readLine());
        }

        Phone phone = new Phone();
        phone.setSerialNumber(sn);
        phone.setBrand(brand);
        phone.setModel(model);
        phone.setYear(year);

        phoneCrudServiceMPO.updateCRUD(phone);

    }


    private void findOne(BufferedReader reader) throws IOException {

        System.out.println("Please enter serial number to find one phone");
        String sn = reader.readLine();

        Phone phone = phoneCrudServiceMPO.findOneCRUD(sn);

        if (phone.getSerialNumber() != null) {
            System.out.println("----------------Requested info below---------------------------------------");
            System.out.println("serial number = " + phone.getSerialNumber());
            System.out.println("brand = " + phone.getBrand());
            System.out.println("model = " + phone.getModel());
            System.out.println("year = " + phone.getYear());
            System.out.println("---------------------------------------------------------------------------");
        } else {
            System.out.println("Serial number not found,please try again");
        }
    }
}










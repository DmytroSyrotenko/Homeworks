package java8.hw3.db;


import java8.hw3.entity.Phone;
import java.util.Objects;
import java.util.UUID;

public class PhoneDb {


    private Phone[] phones = new Phone[2];
    private int lastPhoneIndex = 0;

    public void create(Phone phone) {

        if (lastPhoneIndex == phones.length - 1) {
            Phone[] newPhones = new Phone[phones.length * 2];
            System.arraycopy(phones, 0, newPhones, 0, phones.length);
            phones = newPhones;
            add(phone);
        } else {
            add(phone);
        }
    }

    public Phone[] findAll() {
        return phones;
    }

    public void add(Phone phone) {
        phone.setSerialNumber(UUID.randomUUID().toString());
        phones[lastPhoneIndex] = phone;
        lastPhoneIndex++;

    }

    public void delete(String sn) {

        int index = 0;


        for (int i = 0; i < phones.length; i++) {
            if (Objects.equals(phones[i].getSerialNumber(), sn)) {
                index = i;
                Phone[] newPhones = new Phone[phones.length - 1];
                System.arraycopy(phones, 0, newPhones, 0, index);
                System.arraycopy(phones, index + 1, newPhones, index, phones.length - index - 1);
                phones = newPhones;
                lastPhoneIndex--;
                System.out.println("Phone with serial number " + sn + " deleted");
                break;
            }

        }
    }


    public void update(Phone phone){

        int index=-1;

        for (int i = 0; i < phones.length; i++) {
            if (Objects.equals(phones[i].getSerialNumber(), phone.getSerialNumber())){
                index=i;
                break;
            }
        }
        phones[index]=phone;
        System.out.println("Phone has been updated");

    }


    public Phone findOne(String sn) {

        Phone phone = new Phone();

            for (int i = 0; i < phones.length; i++) {
                if (phones[i]!=null && Objects.equals(phones[i].getSerialNumber(), sn)) {

                    phone = phones[i];
                    return phone;
                }
            }

        return phone;
    }


}

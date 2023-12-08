package controller;

import controller.myReader.MyReader;
import entity.Account;
import entity.Client;
import entity.Transaction;
import service.AccountCrudService;
import service.ClientCrudService;
import service.ClientInfoExportCsvService;
import service.impl.AccountCrudServiceImpl;
import service.impl.ClientCrudServiceImpl;
import service.impl.ClientInfoExportCsvServiceImpl;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class MainController {

    final ClientCrudService clientCrudService = new ClientCrudServiceImpl();
    final AccountCrudService accountCrudService = new AccountCrudServiceImpl();
    final ClientInfoExportCsvService clientInfoExportCsvService = new ClientInfoExportCsvServiceImpl();

    public void start() {
        MyReader reader = new MyReader();
        menuMain();
        String position;
        while ((position = reader.readLine()) != null) {
            crudMain(position, reader);
        }
        menuMain();
    }

    private void startLevel2() {
        MyReader reader = new MyReader();
        menuOperationsWithExistingClient();
        String position;
        while ((position = reader.readLine()) != null) {
            crudOperationsWithExistingClient(position, reader);
        }
        menuOperationsWithExistingClient();
    }

    private void startLevel3() {
        MyReader reader = new MyReader();
        menuUpdateDeleteForClientAttributes();
        String position;
        while ((position = reader.readLine()) != null) {
            crudUpdateDeleteForClientAttributes(position, reader);
        }
        menuUpdateDeleteForClientAttributes();
    }

    private void menuMain() {
        System.out.println("1.0**********Main menu(Id=" + clientCrudService.getCurrentSessionClientId() + ")**********");
        System.out.println("Create new client enter 1");
        System.out.println("Choose existing client enter 2");
        System.out.println("Find all clients enter 3");
        System.out.println("Exit program enter 0");
        System.out.println("**************************");
    }

    private void crudMain(String position, MyReader reader) {
        switch (position) {
            case "1" -> {
                createNewClient(reader);
                start();
            }
            case "2" -> {
                findOneClient(reader);
                startLevel2();
            }
            case "3" -> {
                findAllClients();
                start();
            }
            case "0" -> System.exit(0);
        }
    }

    private void menuOperationsWithExistingClient() {
        System.out.println("1.1********Operations with existing client(Id=" + clientCrudService.getCurrentSessionClientId() + ")********");
        System.out.println("Clients accounts and balance info enter 1");
        System.out.println("Send money to other client enter 2");
        System.out.println("Client settings enter 3");
        System.out.println("Export of statistics for the period in csv file enter 4");
        System.out.println("Return to previous menu enter 99");
        System.out.println("**************************");
    }

    private void crudOperationsWithExistingClient(String position, MyReader reader) {
        switch (position) {
            case "1" -> {
                checkBalance();
                startLevel2();
            }
            case "2" -> {
                sendMoney(reader);
                startLevel2();
            }
            case "3" -> startLevel3();
            case "4" -> {
                exportDataToCsv(reader);
                startLevel2();
            }
            case "99" -> returnToMenu10();
        }
    }

    private void menuUpdateDeleteForClientAttributes() {
        System.out.println("1.1.4********Update/Delete menu********");
        System.out.println("Update client enter 1");
        System.out.println("Delete client enter 2");
        System.out.println("Create new account for client enter 3");
        System.out.println("Update account enter 4");
        System.out.println("Delete account enter 5");
        System.out.println("Find one account enter 6");
        System.out.println("Return to previous menu enter 99");
        System.out.println("**************************");
    }

    private void crudUpdateDeleteForClientAttributes(String position, MyReader reader) {
        switch (position) {
            case "1" -> {
                updateClient(reader);
                startLevel3();
            }
            case "2" -> {
                deleteClient();
                returnToMenu10();
            }
            case "3" -> {
                createNewAccount(reader);
                startLevel3();
            }
            case "4" -> {
                updateAccount(reader);
                startLevel3();
            }
            case "5" -> {
                deleteAccount(reader);
                startLevel3();
            }
            case "6" -> {
                findOneAccount(reader);
                startLevel3();
            }
            case "99" -> returnToMenu11();
        }
    }


    private void returnToMenu10() {
        clientCrudService.resetCurrentClientId();
        start();
    }

    private void returnToMenu11() {
        startLevel2();
    }

    private void createNewClient(MyReader reader) {
        System.out.println("Enter first name");
        String fn = reader.readLine();
        System.out.println("Enter last name");
        String ln = reader.readLine();
        Client client = new Client();
        client.setFirstName(fn);
        client.setLastName(ln);
        Account account = new Account();
        client.getAccounts().add(account);
        clientCrudService.create(client);
    }

    private void updateClient(MyReader reader) {
        System.out.println("Enter first name");
        String fn = reader.readLine();
        System.out.println("Enter last name");
        String ln = reader.readLine();
        Client client = new Client();
        client.setId(clientCrudService.getCurrentSessionClientId());
        client.setFirstName(fn);
        client.setLastName(ln);
        clientCrudService.update(client);
    }

    private void deleteClient() {
        clientCrudService.delete(clientCrudService.getCurrentSessionClientId());
    }

    private void findOneClient(MyReader reader) {
        System.out.println("enter id");
        Long id = Long.valueOf(reader.readLine());
        Client client = clientCrudService.findOne(id);
        System.out.println("----");
        System.out.println("Id = " + client.getId());
        System.out.println("First name = " + client.getFirstName());
        System.out.println("Last name = " + client.getLastName());
        System.out.println("----");
    }

    private void findAllClients() {
        System.out.println("----------------------------------");
        System.out.println("All clients:");
        Collection<Client> clients = clientCrudService.findAll();
        clients.forEach(System.out::println);
        System.out.println("----------------------------------");
    }

    private void createNewAccount(MyReader reader) {
        System.out.println("Enter account name");
        String name = reader.readLine();
        Account account = new Account();
        account.setName(name);
        Client currentClient = clientCrudService.findOne(clientCrudService.getCurrentSessionClientId());
        currentClient.getAccounts().add(account);
        clientCrudService.update(currentClient);
    }

    private void updateAccount(MyReader reader) {
        System.out.println("Enter account id");
        long id = Long.parseLong(reader.readLine());
        System.out.println("Enter NEW account name");
        String name = reader.readLine();
        System.out.println("Enter NEW account sum");
        BigDecimal sum = BigDecimal.valueOf(Double.parseDouble(reader.readLine()));
        Account account = accountCrudService.findOne(id);
        account.setName(name);
        account.setSum(sum);
        accountCrudService.update(account);
    }

    public void sendMoney(MyReader reader) {
        long senderAccountId;
        long recipientAccountId;
        BigDecimal sum;
        try {
            System.out.println("Enter sender account id (one of your accounts)");
            senderAccountId = Long.parseLong(reader.readLine());
            System.out.println("Enter recipient account id ");
            recipientAccountId = Long.parseLong(reader.readLine());
            System.out.println("Enter sum (use .(dot) as a divider)");
            sum = BigDecimal.valueOf(Double.parseDouble(reader.readLine()));
        } catch (IllegalArgumentException e) {
            System.out.println("One or more entered values are not numbers.Try again");
            return;
        }
        System.out.println("Enter comment");
        String comment = reader.readLine();
        Transaction transaction = new Transaction();
        transaction.setSender(senderAccountId);
        transaction.setRecipient(recipientAccountId);
        transaction.setSum(sum);
        transaction.setComment(comment);
        accountCrudService.attachTransactionToSenderAccountAndChangeSum(transaction);
    }

    private void deleteAccount(MyReader reader) {
        System.out.println("Enter account id");
        long id = Long.parseLong(reader.readLine());
        accountCrudService.delete(id);
    }

    private void findOneAccount(MyReader reader) {
        System.out.println("Enter account id");
        Long accountId = Long.valueOf(reader.readLine());
        System.out.println("----------------------------------");
        System.out.println(accountCrudService.findOne(accountId));
        System.out.println("----------------------------------");
    }

    private void checkBalance() {
        System.out.println("----------------------------------");
        System.out.println("Your accounts and balances:");
        Client currentClient = clientCrudService.findOne(clientCrudService.getCurrentSessionClientId());
        currentClient.getAccounts().forEach(System.out::println);
        System.out.println("----------------------------------");
    }


    public void exportDataToCsv(MyReader reader) {
        try {
            System.out.println("Enter your account id");
            long accountId = Long.parseLong(reader.readLine());
            System.out.println("Enter FROM date in format YYYY-MM-DD");
            Date from = java.sql.Date.valueOf(reader.readLine());
            System.out.println("Enter UNTIL date in format YYYY-MM-DD");
            Date until = java.sql.Date.valueOf(reader.readLine());
            clientInfoExportCsvService.exportCsv(accountId, from, until);
        } catch (IllegalArgumentException e) {
            System.out.println("Entered data incorrect.Try again");
        }
    }
}

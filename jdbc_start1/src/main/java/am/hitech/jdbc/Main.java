package am.hitech.jdbc;

import am.hitech.jdbc.model.UserAddress;
import am.hitech.jdbc.repo.UserAddressRepo;
import am.hitech.jdbc.repo.UserRepo;
import am.hitech.jdbc.service.AccountService;
import am.hitech.jdbc.service.AddressService;
import am.hitech.jdbc.service.NumberService;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    AccountService accountService = new AccountService();
    public static void main(String[] args) throws SQLException, NotFoundException, InternalServerError {

        UserRepo userRepo = new UserRepo();
        System.out.println(userRepo.checkUser("hayk@gmail.com",2222));


//        AddressService addressService = new AddressService();
//        System.out.println(addressService.getAll());
//        UserAddressRepo userAddressRepo = new UserAddressRepo();
//        System.out.println(userAddressRepo.getAllUserAddress());

//        NumberService numberService = new NumberService();
//        System.out.println(numberService.getAllNumber());
//        System.out.println(numberService.getAllUser());
//        System.out.println(numberService.info());

//        Main main = new Main();
//        main.scannerTransfer();

//        Main main = new Main();
//      main.transfer(1, 20, 100);

//        String query = "select * from `user`";
//
//        Connection connection = DataSource.getConnection();
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery(query);
//
//        while (resultSet.next()) {
//            System.out.println(resultSet.getString("first_name"));
//        }
//        resultSet.close();
//        statement.close();
//        connection.close();

//        UserService userService = new UserService();
//        System.out.println(userService.getByUser("hayk@gmail.com"));
//        System.out.println(userService.getById(17));
//        System.out.println(userService.getByUserMoreAgeThenGiven(18));
//        System.out.println(userService.getByUser2("Hay"));
//        userService.updateUser("vvvv","vvvv",17);
//        userService.createUser(new User());
//        userService.createUser2(new User(0,"Alexander","Makedonaci","Alex@mail.ru",66));
//        userService.createUser2(new User(0,"Alexander1","Makedonaci1","Alex@mail.ru1",66));
//        userService.deleteUser(17);

//        AddressService addressService = new AddressService();
//        System.out.println(addressService.getById(32));
//        System.out.println(addressService.getByUserId(8));
//        System.out.println(addressService.getAll());
//        addressService.createAddress(new Address(0,"Nigeria","Niger","Niger",555,8));
//        addressService.updateAddress("Greece", "Athens", 34);
//        addressService.deleteAddress(34);
//        System.out.println(addressService.getAddressUserByMap(11));
//        System.out.println(addressService.getAddressUserByMap(33));
//        System.out.println(addressService.getAddressUserByMap(30));
//        System.out.println(addressService.getAddressUserByMap1(11));
 //       System.out.println(addressService.getAddressUserByMap2(11));

//        AccountService accountService = new AccountService();
//        accountService.createAccount(new Account(0,"test2",1111, 500, 8));
//        accountService.updateAccount("test3",400,3);
//        accountService.deleteAccount(3);
//        accountService.transfer(5, 1, 1000);


    }


    private void transfer(int fromUserId, int toUserId, int amount) throws InternalServerError, NotFoundException {

        accountService.transfer(fromUserId,toUserId,amount);
    }

    private void scannerTransfer() throws NotFoundException, InternalServerError {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your number for login(FORMAT EXAMPLE: 94303030)");
        int number = scanner.nextInt();
        System.out.println("Enter your password for login(FORMAT EXAMPLE: 1111)");
        int password = scanner.nextInt();
        int fromUserId = accountService.login(number,password);

        System.out.println("Enter number who you want to add money(FORMAT EXAMPLE: 55101010)");
        int number1 = scanner.nextInt();
        int toUserId = accountService.findUseridWithNumber(number1);

        System.out.println("Enter how much money to send");
        int amount = scanner.nextInt();
        accountService.checkAmount(amount);

        accountService.transfer(fromUserId,toUserId,amount);

    }
}

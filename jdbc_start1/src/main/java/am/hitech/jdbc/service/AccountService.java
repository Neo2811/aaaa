package am.hitech.jdbc.service;

import am.hitech.jdbc.model.Account;
import am.hitech.jdbc.repo.AccountRepo;
import am.hitech.jdbc.util.exceptionMessage.ErrorMessage;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;

public class AccountService {

    private AccountRepo accountRepo = new AccountRepo();

    public void transfer(int from, int to, int amount) throws InternalServerError, NotFoundException {
        if (accountRepo.buildAccount(accountRepo.checkFrom(from)) == null || accountRepo.buildAccount(accountRepo.checkFrom(to)) == null) {
            throw new InternalServerError(ErrorMessage.NOT_FOUND_ACCOUNT);
        } else if (checkAmount(amount, from) < 0) {

        }else
            accountRepo.transfer(from, to, amount);
    }

    public int login(int number, int password) throws NotFoundException {
        int id = accountRepo.login(number, password);
        if (id == -1) {
            throw new NotFoundException(ErrorMessage.LOGIN_FAILED);
        }
        System.out.println("login successful");
        return id;
    }


    public int findUseridWithNumber(int number) throws NotFoundException {
        int id = accountRepo.findUseridWithNumber(number);
        if (id == -1) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_ACCOUNT);
        }
        return id;
    }

    public int checkAmount(int amount) throws NotFoundException {
        int aaa = accountRepo.checkAmount(amount);
        if (aaa < 0) {
            throw new NotFoundException(ErrorMessage.NOT_ENOUGH_BALANCE_IN_YOUR_ACCOUNT);
        }
        return aaa;
    }

    public int checkAmount(int amount, int userId) throws NotFoundException {
        int aaa = accountRepo.checkAmount(amount, userId);
        if (aaa < 0) {
            throw new NotFoundException(ErrorMessage.NOT_ENOUGH_BALANCE_IN_YOUR_ACCOUNT);
        }
        return aaa;
    }

    public void createAccount(Account account) throws InternalServerError {
        int row = accountRepo.createAccount(account);

        if (row == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }

    public void updateAccount(String userName, int balance, int id) throws InternalServerError {
        int row = accountRepo.updateAccount(userName, balance, id);

        if (row == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);

        }
    }

    public void deleteAccount(int id) throws InternalServerError {
        int row = accountRepo.deleteAccount(id);

        if (row == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }
}

package am.hitech.jdbc.service;

import am.hitech.jdbc.model.User;
import am.hitech.jdbc.repo.UserRepo;
import am.hitech.jdbc.util.exceptionMessage.ErrorMessage;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import java.util.List;
import java.util.Random;

public class UserService {
    UserRepo userRepo = new UserRepo();

    public void updateUser(String firstName, String lastName, int id) throws InternalServerError{
        int row = userRepo.updateUser(firstName,lastName,id);

        if (row == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }

    public void createUser(User user) throws InternalServerError{
        int row = userRepo.createUser(user);

        if (row == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }

    public void createUser2(User user) throws InternalServerError{
        int row = userRepo.createUser2(user);

        if (row == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }

    public void createUserV2(User user) throws InternalServerError{
        int row = userRepo.createUserV2(user);

        if (row == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }

    public void deleteUser(int id) throws InternalServerError {
        int row = userRepo.deleteUser(id);

        if (row == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }

    public User getByUser(String userName) throws NotFoundException {

        if (userRepo.getByUser(userName) == null) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_USER);
        }
        return userRepo.getByUser(userName);
    }

    public User getById(int id) throws NotFoundException{
        if (userRepo.getById(id) == null) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_USER);
        }
        return userRepo.getById(id);
    }

    public User checkUser(String username, int password){
        User user=null;
        try {
            user=userRepo.checkUser(username,password);
            if (user==null){
                throw new NotFoundException(ErrorMessage.WRONG_LOGIN_OR_PASSWORD);
            }

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getByUserMoreAgeThenGiven(int age) throws NotFoundException{
        if (userRepo.getByUserMoreAgeThenGiven(age) == null) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_USER);
        }
        return userRepo.getByUserMoreAgeThenGiven(age);
    }

    public List<User> getByUser2(String s) throws NotFoundException{
        List<User> list = userRepo.getByUser2(s);
        if (list.isEmpty()) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_USER);
        }
        return userRepo.getByUser2(s);
    }
    public String randomString(){
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < 6; i++) {
            int index = random.nextInt(alphaNumeric.length());
            char randomChar = alphaNumeric.charAt(index);
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;
    }
    public void updatePasswordToken(String email) throws NotFoundException {
        int row = userRepo.updatePasswordToken(email);
        if (row == 0) {
            throw new NotFoundException(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }
    public String getPasswordToken(String email) {
        return userRepo.getPasswordToken(email);
    }
    public void updateUserPassword(String email, int password) throws NotFoundException {
        int row = userRepo.updateUserPassword(email,password);
        if (row == 0) {
            throw new NotFoundException(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }
    public boolean isAllSpace(String s) {
        boolean isAllSpace = false;
        for (int i = 0,n = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                n++;
            }
            if (n == s.length()) {
                return true;
            }
        }
        return isAllSpace;
    }
}

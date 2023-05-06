package am.hitech.jdbc.service;

import am.hitech.jdbc.model.UserAddress;
import am.hitech.jdbc.repo.UserAddressRepo;
import am.hitech.jdbc.util.exceptionMessage.ErrorMessage;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import java.util.List;

public class UserAddressService {
    UserAddressRepo userAddressRepo = new UserAddressRepo();

    public List<UserAddress> getAllUserAddress() throws NotFoundException {
        if (userAddressRepo.getAllUserAddress().isEmpty()) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_USER );
        }
        return userAddressRepo.getAllUserAddress();
    }
}

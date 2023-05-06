package am.hitech.jdbc.service;

import am.hitech.jdbc.model.Address;
import am.hitech.jdbc.model.User;
import am.hitech.jdbc.repo.AddressRepo;
import am.hitech.jdbc.util.exceptionMessage.ErrorMessage;
import am.hitech.jdbc.util.exceptions.InternalServerError;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import java.util.List;
import java.util.Map;

public class AddressService {
    AddressRepo addressRepo = new AddressRepo();

    public Address getById(int id) throws NotFoundException {

        if (addressRepo.getById(id) == null) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_USER);
        }
        return addressRepo.getById(id);
    }

    public List<Address> getByUserId(int id) throws NotFoundException{
        if (addressRepo.getByUserId(id).isEmpty()) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_USER);
        }
        return addressRepo.getByUserId(id);
    }

    public List<Address> getAll() throws NotFoundException{
        if (addressRepo.getAll().isEmpty()) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_USER);
        }
        return addressRepo.getAll();
    }

    public void createAddress(Address address) throws InternalServerError {
        int row = addressRepo.createAddress(address);

        if (row == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }

    public void updateAddress(String country, String city, int id) throws NotFoundException {
        int row = addressRepo.updateAddress(country,city,id);

        if (row == 0) {
            throw new NotFoundException(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }

    public void deleteAddress(int id) throws InternalServerError {
        int row = addressRepo.deleteAddress(id);

        if (row == 0) {
            throw new InternalServerError(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
    }

    public Map<Address, User> getAddressUserByMap(int addressId) throws NotFoundException{
        Map<Address, User> map = addressRepo.getAddressUserByMap(addressId);
        if (map.isEmpty()) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_ADDRESS
            );
        }
        return map;
    }

    public Map<Address, User> getAddressUserByMap1(int addressId) throws NotFoundException {
        Map<Address, User> map = addressRepo.getAddressUserByMap1(addressId);

        if (map.isEmpty()) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_ADDRESS);
        }
        return map;
    }

    public Map<Address, User> getAddressUserByMap2(int addressId) throws NotFoundException {
        Map<Address, User> map = addressRepo.getAddressUserByMap1(addressId);

        if (map.isEmpty()) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_ADDRESS);
        }
        return map;
    }


}

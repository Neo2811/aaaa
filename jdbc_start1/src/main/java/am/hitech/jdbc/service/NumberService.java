package am.hitech.jdbc.service;

import am.hitech.jdbc.model.Number;
import am.hitech.jdbc.repo.NumberRepo;
import am.hitech.jdbc.util.exceptionMessage.ErrorMessage;
import am.hitech.jdbc.util.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class NumberService {
    NumberRepo numberRepo = new NumberRepo();

    public List<Number> getAllNumber() throws NotFoundException {
        if (numberRepo.getAllNumber().isEmpty()) {
            throw new NotFoundException(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
        return numberRepo.getAllNumber();
    }

    public List<Number> getAllUser() throws NotFoundException {
        if (numberRepo.getAllUser().isEmpty()) {
            throw new NotFoundException(ErrorMessage.ANOTHER_ERROR_MESSAGE);
        }
        return numberRepo.getAllUser();
    }

    public List<Number> info() throws NotFoundException {
        List<Number> list = new ArrayList<>();
        Number number;

        List<Number> list1 = getAllNumber();
        List<Number> list2 = getAllUser();
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).getUserId() == 0) {
                number = new Number();
                number.setNumber(list1.get(i).getNumber());
                number.setFirstname("No");
                number.setLastname("User");

                list.add(number);

            }else if (list1.get(i).getUserId() != 0){
                for (int j = 0; j < list2.size(); j++) {
                    if (list1.get(i).getUserId() == list2.get(j).getUserId()) {
                        number = new Number();
                        number.setNumber(list1.get(i).getNumber());
                        number.setFirstname(list2.get(j).getFirstname());
                        number.setLastname(list2.get(j).getLastname());

                        list.add(number);
                    }
                }
            }
        }
        return list;
    }
}

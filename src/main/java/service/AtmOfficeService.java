package service;

import entity.AtmOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AtmOfficeRepository;

import java.util.List;

@Service
public class AtmOfficeService {

    private final AtmOfficeRepository atmOfficeRepository;

    @Autowired
    public AtmOfficeService(AtmOfficeRepository atmOfficeRepository) {
        this.atmOfficeRepository = atmOfficeRepository;
    }

    public AtmOffice getById(Integer id){
        return atmOfficeRepository.getById(id);
    }

    public List<AtmOffice> getAll(){
        return atmOfficeRepository.findAll();
    }
}

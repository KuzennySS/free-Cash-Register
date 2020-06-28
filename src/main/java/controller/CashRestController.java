package controller;

import entity.AtmOffice;
import entity.ErrorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.AtmOfficeService;

import java.util.List;

@RestController
public class CashRestController {

    private AtmOfficeService atmOfficeService;

    @Autowired
    public CashRestController(AtmOfficeService atmOfficeService) {
        this.atmOfficeService = atmOfficeService;
    }

    /**
     * REST контроллер возвращает банкомат по id
     */
    @GetMapping("/branches/{id}")
    public ResponseEntity<?> getAtmOffice(@PathVariable(name = "id") int id) {
        final AtmOffice atmOffice = atmOfficeService.getById(id);

        return atmOffice != null
                ? new ResponseEntity<>(atmOffice, HttpStatus.OK)
                : new ResponseEntity<>(new ErrorRequest("branch not found"), HttpStatus.NOT_FOUND);
    }

    /**
     * REST контроллер возвращает список всех банкоматов
     */
    @GetMapping("/branches")
    public List<AtmOffice> getAtmOffice() {
        return atmOfficeService.getAll();
    }
}

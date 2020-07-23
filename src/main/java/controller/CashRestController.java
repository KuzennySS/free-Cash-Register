package controller;

import entity.AtmOffice;
import entity.BranchesWithPredicting;
import entity.ErrorRequest;
import entity.NearAtmOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.AtmOfficeService;
import service.CalculateDistance;
import service.CalculateWorkLoad;

import java.util.List;

@RestController
public class CashRestController {

    private AtmOfficeService atmOfficeService;

    private CalculateDistance calculateDistance;

    private CalculateWorkLoad calculateWorkLoad;

    @Autowired
    public CashRestController(AtmOfficeService atmOfficeService,
                              CalculateDistance calculateDistance,
                              CalculateWorkLoad calculateWorkLoad) {
        this.atmOfficeService = atmOfficeService;
        this.calculateDistance = calculateDistance;
        this.calculateWorkLoad = calculateWorkLoad;
    }

    /**
     * REST контроллер возвращает банкомат по id
     */
    @GetMapping("/branches/{id}")
    public ResponseEntity<?> getAtmOffice(@PathVariable int id) {
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

    /**
     * REST контроллер возвращает ближайший банкомат
     */
    @GetMapping("/branches/")
    public ResponseEntity<?> getNearestOffice(@RequestParam Double lat, @RequestParam Double lon) {
        NearAtmOffice nearAtmOffice = calculateDistance.getNearestAtm(lat, lon);

        return nearAtmOffice != null
                ? new ResponseEntity<>(nearAtmOffice, HttpStatus.OK)
                : new ResponseEntity<>(new ErrorRequest("branch not found"), HttpStatus.NOT_FOUND);
    }

    /**
     * REST контроллер возвращает загруженость конкретного банкомата
     */
    @GetMapping("/branches/{id}/predict")
    public ResponseEntity<?> getPredictWorkload(@PathVariable int id,
                                                @RequestParam(name = "dayOfWeek") int dayOfWeek,
                                                @RequestParam(name = "hourOfDay") int hourOfDay) {

        BranchesWithPredicting branchesWithPredicting = calculateWorkLoad.getPredict(id, dayOfWeek, hourOfDay);

        return branchesWithPredicting != null
                ? new ResponseEntity<>(branchesWithPredicting, HttpStatus.OK)
                : new ResponseEntity<>(new ErrorRequest("branch not found"), HttpStatus.NOT_FOUND);
    }
}

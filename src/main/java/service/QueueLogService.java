package service;

import entity.QueueLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.QueueLogRepository;

import java.util.List;

@Service
public class QueueLogService {

    private final QueueLogRepository repository;

    @Autowired
    public QueueLogService(QueueLogRepository repository) {
        this.repository = repository;
    }

    public List<QueueLog> getWorkLoadForPredict(int idAtmOffice, int dayOfWeek, int hourOfDay){
        return repository.getWorkLoadForPredict(idAtmOffice, dayOfWeek, hourOfDay);
    }
}

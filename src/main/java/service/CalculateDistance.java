package service;

import org.springframework.stereotype.Service;

@Service
public class CalculateDistance {

    // усредненный радиус Земли в метрах
    private static final Integer Rearth = 6371000;

    // широта человека
    private static Integer latitudePerson;

    // широта банкомата
    private static Integer latitudeAtm;

    // долгота человека
    private static Integer longitudePerson;

    // долгота банкомата
    private static Integer longitudeAtm;

}

package service;

import entity.AtmOffice;
import entity.NearAtmOffice;
import entity.dto.AtmDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.math3.util.FastMath.*;

@Service
public class CalculateDistance {

    private final AtmOfficeService atmOfficeService;

    @Autowired
    public CalculateDistance(AtmOfficeService atmOfficeService) {
        this.atmOfficeService = atmOfficeService;
    }

    // усредненный радиус Земли в метрах
    private static final Integer rEarth = 6371000;

    /**
     * Расчет дистанции между координатами человека и банкомата
     *
     * @param latitudePerson  -   широта человека
     * @param longitudePerson -   долгота человека
     * @param latitudeAtm     -   широта банкомата
     * @param longitudeAtm    -   долгота банкомата
     * @return -   растояние между ними
     */
    private double caclulateDistance(double latitudePerson, double longitudePerson, double latitudeAtm, double longitudeAtm) {
        // получение координат точек в радианах
        double latPerson = latitudePerson * PI / 180;
        double latAtm = latitudeAtm * PI / 180;
        double longPerson = longitudePerson * PI / 180;
        double longAtm = longitudeAtm * PI / 180;
        // косинусы широт
        double cosFi1 = cos(latPerson);
        double cosFi2 = cos(latAtm);
        // разниц долгот
        double deltaLat = latAtm - latPerson;
        double deltaLon = longAtm - longPerson;
        // sin delta
        double sinLat = sin(deltaLat / 2);
        double sinLon = sin(deltaLon / 2);
        // возведение в квадрат
        double sqSinDeltaLat = pow(sinLat, 2);
        double sqSinDeltaLon = pow(sinLon, 2);
        // второе слагаемое
        double a = cosFi1 * cosFi2 * sqSinDeltaLon;
        // первое слагаемое + второе слагаемое
        double b = sqSinDeltaLat + a;
        // извлекаем корень из суммы
        double c = sqrt(b);
        // берем arcsin
        double d = asin(c);
        // получаем итоговое расстояние
        return 2 * rEarth * d;
    }

    /**
     * Определение расстояния между человеком и всеми банкоматами, выбор минимального
     *
     * @param latitudePerson  -   широта человека
     * @param longitudePerson -   долгота человека
     * @return -   id ближайшего банкомата
     */
    public NearAtmOffice getNearestAtm(double latitudePerson, double longitudePerson) {
        List<AtmOffice> atms = atmOfficeService.getAll();
        Map<Integer, Double> atmDistance = new HashMap<>();
        // наполняем map
        atms.stream()
                .map(atmOffice -> getAtmDistance(latitudePerson, longitudePerson, atmOffice))
                .forEach(atmDist -> atmDistance.put(atmDist.getIdAtm(), atmDist.getDistance()));
        // находим минимальное расстояние из Map
        double minDistance = atmDistance.values().stream()
                .min(Comparator.comparing(Double::doubleValue))
                .orElse(-1.0);
        // находим idAtm соответствующий минимальному расстоянию из map
        int idAtm = atmDistance.keySet().stream()
                .filter(k -> atmDistance.get(k) == minDistance)
                .findFirst().get();
        final AtmOffice atmOffice = atmOfficeService.getById(idAtm);
        return NearAtmOffice.builder()
                .id(atmOffice.getId())
                .title(atmOffice.getTitle())
                .lon(atmOffice.getLon())
                .lat(atmOffice.getLat())
                .address(atmOffice.getAddress())
                .distance((int) minDistance)
                .build();
    }

    private AtmDistance getAtmDistance(double latitudePerson, double longitudePerson, AtmOffice atmOffice) {
        return new AtmDistance(atmOffice.getId(),
                caclulateDistance(latitudePerson, longitudePerson, atmOffice.getLat(), atmOffice.getLon()));
    }

}

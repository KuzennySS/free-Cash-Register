package service;

import org.springframework.stereotype.Service;

import static org.apache.commons.math3.util.FastMath.*;

@Service
public class CalculateDistance {

    // усредненный радиус Земли в метрах
    private static final Integer rEarth = 6371000;

    // широта банкомата
    private static double latitudeAtm;

    // долгота банкомата
    private static double longitudeAtm;

    public double getDistance(double latitudePerson, double longitudePerson) {
        // получение координат точек в радианах
        double latPerson = latitudePerson * PI / 180;
        double latAtm = latitudeAtm * PI / 180;
        double longPerson = longitudePerson * PI / 180;
        double longAtm = longitudeAtm * PI / 180;

        // косинусы и синусы широт и разниц долгот
        double cosLatPerson = cos(latPerson);
        double cosLatAtm = cos(latAtm);

        double deltaLat = (latitudeAtm - latitudePerson) / 2;
        double deltaLon = (longitudeAtm - longitudePerson) / 2;
        double sinDeltaLat = sin(deltaLat);
        double sinDeltaLon = sin(deltaLon);
        double sqSinDeltaLat = pow(sinDeltaLat, 2);
        double sqSinDeltaLon = pow(sinDeltaLon, 2);
        double a = cosLatPerson * cosLatAtm * sqSinDeltaLon;
        double b = sqSinDeltaLat + a;
        double c = pow(b, -2);
        double d = asin(c);
        double distance = 2 * rEarth * d;

        return distance;
    }

}

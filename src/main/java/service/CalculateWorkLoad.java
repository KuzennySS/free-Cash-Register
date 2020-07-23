package service;

import entity.AtmOffice;
import entity.BranchesWithPredicting;
import entity.QueueLog;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * Сервис расчета загружености офиса
 */
@Service
public class CalculateWorkLoad {

    private final QueueLogService queueLogService;

    private final AtmOfficeService atmOfficeService;

    @Autowired
    public CalculateWorkLoad(QueueLogService queueLogService, AtmOfficeService atmOfficeService) {
        this.queueLogService = queueLogService;
        this.atmOfficeService = atmOfficeService;
    }

    public BranchesWithPredicting getPredict(int id, int dayOfWeek, int hourOfDay) {
        final AtmOffice atmOffice = atmOfficeService.getById(id);
        if (atmOffice == null) return null;
        dayOfWeek = dayOfWeek == 7 ? 0 : dayOfWeek;
        // получаем все данные по ожиданиям
        List<QueueLog> queueLogs = queueLogService.getWorkLoadForPredict(id, dayOfWeek, hourOfDay);
        // вычисляем время ожидания
        Double[] deltaDoble = queueLogs.stream()
                .map(this::getResidual)
                .toArray(Double[]::new);
        double[] delta = ArrayUtils.toPrimitive(deltaDoble);
        Arrays.sort(delta);
        //calculate median
        Median median = new Median();
        double medianValue = median.evaluate(delta);
        // создаем сущность для ответа на запрос
        return BranchesWithPredicting.builder()
                .id(id)
                .title(atmOffice.getTitle())
                .lon(atmOffice.getLon())
                .lat(atmOffice.getLat())
                .address(atmOffice.getAddress())
                .dayOfWeek(dayOfWeek)
                .hourOfDay(hourOfDay)
                .predicting((long) medianValue)
                .build();
    }

    /**
     * Вычисляет разницу между двумя временами в секундах
     * @param queueLog  -   сущность ожидания
     * @return          - время в секундах
     */
    private double getResidual(QueueLog queueLog) {
        return Duration.between(queueLog.getStartTimeOfWait(), queueLog.getEndTimeOfWait()).toSeconds();
    }
}

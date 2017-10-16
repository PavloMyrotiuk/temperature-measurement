package com.myrotiuk.temperaturemeasurement.dto;

public class MeasurementsResponse {
    private String sensorUuid;
    private double averageLastHour;
    private double averageLast7Days;
    private double maxLast30Days;

    public MeasurementsResponse(String sensorUuid, double averageLastHour, double averageLast7Days, double maxLast30Days) {
        this.sensorUuid = sensorUuid;
        this.averageLastHour = averageLastHour;
        this.averageLast7Days = averageLast7Days;
        this.maxLast30Days = maxLast30Days;
    }

    public String getSensorUuid() {
        return sensorUuid;
    }

    public double getAverageLastHour() {
        return averageLastHour;
    }

    public double getAverageLast7Days() {
        return averageLast7Days;
    }

    public double getMaxLast30Days() {
        return maxLast30Days;
    }
}

package com.dretha.drethamod.utils;

public class DrethaMath {
    /**
     * Возвращает число из второго интервала процентно от пула чисел равного параметру number
     * @param interval1Of - начало первого интервала
     * @param interval1To - конец первого интервала
     * @param interval2Of - начало второго интервала
     * @param interval2To - конец второго интервала
     * @param number - число в первом интервале (корректируется)
     * @return - число из второго интервала процентно от пула чисел равное number
     */
    public static double getNumberOfInterval(double interval1Of, double interval1To, double interval2Of, double interval2To, double number)
    {
        if (interval1Of>interval1To || interval2Of>interval2To) return 0;
        number = Math.max(interval1Of, number);
        number = Math.min(interval1To, number);

        double pool1 = interval1To-interval1Of;
        double procent = (number-interval1Of)/pool1;

        double pool2 = interval2To-interval2Of;

        return pool2*procent+interval2Of;
    }

    /**
     * Считает число по пропорции
     * @param interval1Of - начало первого интервала (конец - бесконечность)
     * @param interval2Of - начало второго интервала (конец - бесконечность)
     * @param number - число из первого интервала (не бывает меньше интервала)
     * @return - число из второго интервала пропорционально равное первому числу
     */
    public static double getNumberOfProportion(double interval1Of, double interval2Of, double number)
    {
        return (number*interval2Of)/interval1Of;
    }

    public static int getNumberOfProportion(int interval1Of, int interval2Of, int number)
    {
        int result = (int) Math.round((double)(number*interval2Of)/interval1Of);
        result = Math.max(interval2Of, result);
        return result;
    }

    public static int getNumberOfProportionLimited(int interval1Of, int min, int max, int number)
    {
        int result = (number*min)/interval1Of;
        result = Math.max(min, result);
        result = Math.min(max, result);
        return result;
    }

    public static class IntervalFinder
    {
        public final double number;
        public final double interval1Of;
        public final double interval1To;

        /**
         * Класс, упрощающий массовое использование getNumberOfInterval
         * Позволяет задать изначально первый интервал и число
         * @param number - исходное число 1 интервала
         * @param interval1Of - от
         * @param interval1To - до
         */
        public IntervalFinder(double number, double interval1Of, double interval1To) {
            this.number = number;
            this.interval1Of = interval1Of;
            this.interval1To = interval1To;
        }

        public double find(double interval2Of, double interval2To) {
            return DrethaMath.getNumberOfInterval(interval1Of, interval1To, interval2Of, interval2To, number);
        }
    }
}

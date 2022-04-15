package com.dretha.drethamod.utils;

public class DrethaMath {
    /**
     * ���������� ����� �� ������� ��������� ��������� �� ���� ����� ������� ��������� number
     * @param interval1Of - ������ ������� ���������
     * @param interval1To - ����� ������� ���������
     * @param interval2Of - ������ ������� ���������
     * @param interval2To - ����� ������� ���������
     * @param number - ����� � ������ ��������� (��������������)
     * @return - ����� �� ������� ��������� ��������� �� ���� ����� ������ number
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

    public static double getNumberOfInterval(double interval1Min, double interval1Bound, double interval1Bound2, double interval1Max, double interval2Min, double interval2Bound, double interval2Bound2, double interval2Max, double number)
    {
        number = Math.max(interval1Min, number);
        number = Math.min(interval1Max, number);

        if (number<=interval1Bound)
            return getNumberOfInterval(interval1Min, interval1Bound, interval2Min, interval2Bound, number);
        else if (number<=interval1Bound2)
            return getNumberOfInterval(interval1Bound, interval1Bound2, interval2Bound, interval2Bound2, number);
        else
            return getNumberOfInterval(interval1Bound2, interval1Max, interval2Bound2, interval2Max, number);
    }

    /**
     * ������� ���� � ��� ��� interval2To ������ interval2Of
     */
    public static double getNumberOfReverseInterval(double interval1Of, double interval1To, double interval2Of, double interval2To, double number)
    {
        if (interval1Of>interval1To || interval2Of<interval2To) return 0;
        number = Math.max(interval1Of, number);
        number = Math.min(interval1To, number);

        double pool1 = interval1To-interval1Of;
        double procent = (number-interval1Of)/pool1;

        double pool2 = interval2Of - interval2To;

        double preResult = pool2*procent+ interval2To;
        return (interval2Of -preResult) + interval2To;
    }

    /**
     * ������� ����� �� ���������
     * @param interval1Of - ������ ������� ��������� (����� - �������������)
     * @param interval2Of - ������ ������� ��������� (����� - �������������)
     * @param number - ����� �� ������� ��������� (�� ������ ������ ���������)
     * @return - ����� �� ������� ��������� ��������������� ������ ������� �����
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
         * �����, ���������� �������� ������������� getNumberOfInterval
         * ��������� ������ ���������� ������ �������� � �����
         * @param number - �������� ����� 1 ���������
         * @param interval1Of - ��
         * @param interval1To - ��
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

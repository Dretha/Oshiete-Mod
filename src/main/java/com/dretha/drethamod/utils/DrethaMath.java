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
}

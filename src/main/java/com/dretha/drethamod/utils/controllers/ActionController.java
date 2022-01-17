package com.dretha.drethamod.utils.controllers;
/**����������������� ����������, ���������� �������� ������*/
public class ActionController {

    protected int ticksPre = 0;
    protected int interval;

    public ActionController(int interval) {
        this.interval = interval;
    }

    public void setTicksPre(int ticks) {
        this.ticksPre = ticks;
    }

    /**action ended; �������� �� ���������� ����� ����������: ����� �� ��������� ����� ��������. ���� �����, ����� ���� ��������������� ����*/
    public boolean endAct(int ticks) {
        return ticksPre+interval<ticks;
    }
}

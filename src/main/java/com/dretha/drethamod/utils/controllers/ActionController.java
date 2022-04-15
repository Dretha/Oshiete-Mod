package com.dretha.drethamod.utils.controllers;
/**����������������� ����������, ���������� �������� ������*/
public class ActionController {

    protected int ticksPre;
    protected int interval;

    public ActionController(int interval) {
        this.interval = interval;
        this.ticksPre = -interval;
    }

    public void setTicksPre(int ticks) {
        this.ticksPre = ticks;
    }

    /**action ended; �������� �� ���������� ����� ����������: ����� �� ��������� ����� ��������. ���� �����, ����� ���� ��������������� ����*/
    public boolean endAct(int ticks, boolean condition) {
        boolean b = condition && endAct(ticks);
        if (b)
            setTicksPre(ticks);
        return b;
    }
    public boolean endAct(int ticks) {
        return ticksPre+interval<ticks;
    }
}

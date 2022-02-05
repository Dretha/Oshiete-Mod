package com.dretha.drethamod.utils.controllers;
/**���������� ��������, ��������� ����� �� ��������� �������� �� ����� ��� � �������*/
public class NoParamActionController {

    protected int ticksPre = 0;
    protected int interval = 0;

    public void setTicksPre(int ticks) {
        this.ticksPre = ticks;
    }

    /**action ended; �������� �� ���������� ����� ����������: ����� �� ��������� ����� ��������. ���� �����, ����� ���� ��������������� ����*/
    public boolean endAct(int ticks, int interval) {
        if (this.interval>interval && !simpleCheckEndAct(ticks, this.interval)) {
            interval = this.interval;
        } else {
            this.interval = interval;
        }
        return ticksPre+interval<ticks;
    }
    private boolean simpleCheckEndAct(int ticks, int interval) {
        return ticksPre+interval<ticks;
    }
}

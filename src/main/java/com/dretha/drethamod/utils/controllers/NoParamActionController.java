package com.dretha.drethamod.utils.controllers;
/**���������� ��������, ��������� ����� �� ��������� �������� �� ����� ��� � �������*/
public class NoParamActionController {

    protected int ticksPre = 0;

    public int ticksPre() {
        return ticksPre;
    }

    public void setTicksPre(int ticks) {
        this.ticksPre = ticks;
    }

    /**action ended; �������� �� ���������� ����� ����������: ����� �� ��������� ����� ��������. ���� �����, ����� ���� ��������������� ����*/
    public boolean endAct(int ticks, int interval) {
        boolean b = ticksPre+interval<ticks;
        if (b)
            setTicksPre(ticks);
        return b;
    }
}

package com.dretha.drethamod.utils.controllers;
/**Контроллер действия, проверяет можно ли совершить действие по тикам пре и текущим*/
public class NoParamActionController {

    protected int ticksPre = 0;

    public int ticksPre() {
        return ticksPre;
    }

    public void setTicksPre(int ticks) {
        this.ticksPre = ticks;
    }

    /**action ended; закончен ли промежуток между действиями: можно ли совершить новое действие. Если можно, новые тики устанавливаются сами*/
    public boolean endAct(int ticks, int interval) {
        boolean b = ticksPre+interval<ticks;
        if (b)
            setTicksPre(ticks);
        return b;
    }
}

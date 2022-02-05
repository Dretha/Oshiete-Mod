package com.dretha.drethamod.utils.controllers;
/**Контроллер действия, проверяет можно ли совершить действие по тикам пре и текущим*/
public class NoParamActionController {

    protected int ticksPre = 0;
    protected int interval = 0;

    public void setTicksPre(int ticks) {
        this.ticksPre = ticks;
    }

    /**action ended; закончен ли промежуток между действиями: можно ли совершить новое действие. Если можно, новые тики устанавливаются сами*/
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

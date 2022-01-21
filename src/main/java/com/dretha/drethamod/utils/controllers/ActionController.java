package com.dretha.drethamod.utils.controllers;
/**параметризованный контроллер, промежуток изменить нельзя*/
public class ActionController {

    protected int ticksPre = 0;
    protected int interval;

    public ActionController(int interval) {
        this.interval = interval;
    }

    public void setTicksPre(int ticks) {
        this.ticksPre = ticks;
    }

    /**action ended; закончен ли промежуток между действиями: можно ли совершить новое действие. Если можно, новые тики устанавливаются сами*/
    public boolean endAct(int ticks, boolean condition) {
        boolean b = condition && ticksPre+interval<ticks;
        if (b) {
            setTicksPre(ticks);
        }
        return b;
    }
    public boolean endAct(int ticks) {
        return ticksPre+interval<ticks;
    }
}

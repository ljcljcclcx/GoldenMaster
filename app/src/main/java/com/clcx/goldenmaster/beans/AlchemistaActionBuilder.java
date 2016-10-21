package com.clcx.goldenmaster.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/21.
 */
public class AlchemistaActionBuilder implements IAlchemistaAction {

    private Alchemista alchemista;

    public AlchemistaActionBuilder(Alchemista alchemista) {
        this.alchemista = alchemista;
    }

    @Override
    public void alchemist(List<AlchemiItem> alitems) {

    }

    @Override
    public void putItemToBag(AlchemiItem item) {
        alchemista.getBag().add(item);
    }

    @Override
    public void soldItem(AlchemiItem item) {
        alchemista.getBag().remove(item);
    }

    @Override
    public void putItemToShop(AlchemiItem item) {
        alchemista.getBag().remove(item);
    }
}

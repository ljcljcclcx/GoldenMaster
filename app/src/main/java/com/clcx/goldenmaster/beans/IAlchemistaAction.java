package com.clcx.goldenmaster.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/21.
 */
public interface IAlchemistaAction {
    void alchemist(List<AlchemiItem> alitems);

    void putItemToBag(AlchemiItem item);

    void soldItem(AlchemiItem item);

    void putItemToShop(AlchemiItem item);
}

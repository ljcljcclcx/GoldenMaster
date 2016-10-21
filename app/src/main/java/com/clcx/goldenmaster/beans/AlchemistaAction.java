package com.clcx.goldenmaster.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljc123 on 2016/10/21.
 */
public class AlchemistaAction {
    public static AlchemistaActionBuilder builder(Alchemista alchemista) {
        return new AlchemistaActionBuilder(alchemista);
    }
}

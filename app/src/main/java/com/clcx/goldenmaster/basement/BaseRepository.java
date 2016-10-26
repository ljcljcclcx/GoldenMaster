package com.clcx.goldenmaster.basement;

import com.clcx.goldenmaster.basement.data.RepositoryClcx;

/**
 * Created by ljc123 on 2016/10/21.
 */
public class BaseRepository implements Cloneable {
    @Override
    public Object clone() {
        RepositoryClcx stu = null;
        try {
            stu = (RepositoryClcx) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return stu;
    }
}
package com.clcx.goldenmaster.basement.data;


import com.clcx.goldenmaster.basement.BaseRepository;

import java.util.Map;

import rx.Observable;

/**
 * Created by ljc123 on 2016/10/21.
 */
public abstract class RepositoryClcx<T> extends BaseRepository {
    public T data;

    public Map<String, String> param;

    public abstract Observable<DataClcx<T>> getPageAt(int page);
}

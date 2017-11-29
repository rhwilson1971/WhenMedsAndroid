package com.wymsii.whenmeds.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.content.Context;

import com.wymsii.whenmeds.AppDatabase;
import com.wymsii.whenmeds.script.Script;

import java.util.List;

/**
 * Created by reuben on 11/27/17.
 */

public class ScriptListViewModel extends AndroidViewModel {
    private final MediatorLiveData<List<Script>> mObservableScripts;

    public ScriptListViewModel(Application application, Context context){
        super(application);

        mObservableScripts = new MediatorLiveData<>();

        mObservableScripts.setValue(null);

        com.wymsii.whenmeds.AppDatabase app =
                com.wymsii.whenmeds.AppDatabase.getInstance(context);

        

        // LiveData<List<Script>> scripts
    }
}

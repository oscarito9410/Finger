package com.example.oscar.finger.ui.Activation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.oscar.finger.R;
import com.example.oscar.finger.ui.Activation.Adapters.AdapterActivation;
import com.example.oscar.finger.ui.Activation.Model.ModuleActivation;
import com.example.oscar.finger.ui.Activation.Presenter.ActivationContract;
import com.example.oscar.finger.ui.Activation.Presenter.ActivationPresenterImpl;
import com.mattprecious.swirl.SwirlView;
import com.neurotec.lang.NModule;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivationActivity extends AppCompatActivity implements ActivationPresenterImpl.ActivationView {

    @BindView(R.id.recyclerModules)
    RecyclerView mRecyclerModules;
    @BindView(R.id.tvLicencse)
    TextView tvLicense;
    private ActivationContract presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitation);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setPresenter();
    }

    @Override
    public void setListComponents(ArrayList<ModuleActivation> listComponents) {
        AdapterActivation mAdpt=new AdapterActivation(listComponents,this);
        mRecyclerModules.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerModules.setAdapter(mAdpt);
    }

    @Override
    public void setInfoLicense(String infoLicense, boolean activated) {
        tvLicense.setText(infoLicense.concat(activated? "Activada": "No activa"));
    }

    @Override
    public void setPresenter() {
        if(presenter==null){
            presenter=new  ActivationPresenterImpl(this);
            presenter.requestListModules();
        }
    }

    @Override
    public void setListeners() {
    }
}

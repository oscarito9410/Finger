package com.example.oscar.finger.ui.Activation.Presenter;
import com.example.oscar.finger.base.BaseViewContract;
import com.example.oscar.finger.ui.Activation.Model.ModuleActivation;
import com.neurotec.lang.NModule;
import com.neurotec.licensing.NLicensing;

import java.util.ArrayList;

/**
 * Created by Oscar on 29/12/2017.
 */
public class ActivationPresenterImpl implements  ActivationContract {

    private ActivationView view;

    public ActivationPresenterImpl(ActivationView view){
            this.view=view;
    }

    private ArrayList<ModuleActivation> getComponents(boolean activated) {
        ArrayList<ModuleActivation> components = new ArrayList<>();
        NModule[] modules = NModule.getLoadedModules();
        if (modules != null) {
            for (NModule module : modules) {
                if (module != null) {
                    String activatedStr = module.getActivated();
                    if (activatedStr != null && !activatedStr.equals("")) {
                        for (String activatedPart : activatedStr.split(",")) {
                            String[] apParts = activatedPart.split(":");
                            if (apParts.length > 1) {
                                if (activated && "yes".equals(apParts[apParts.length - 1].trim().toLowerCase())) {
                                    components.add(new ModuleActivation(apParts[0].trim()));
                                } else if (!activated && "no".equals(apParts[apParts.length - 1].trim().toLowerCase())) {
                                    components.add(new ModuleActivation(apParts[0].trim()));
                                }
                            }
                        }
                    }
                }
            }
        }
        return components;
    }
    @Override
    public void requestListModules() {
            if(view!=null){
                view.setListComponents(getComponents(true));
                view.setInfoLicense(NLicensing.nativeModuleOf().getTitle(),false);
            }
    }
    public interface ActivationView extends BaseViewContract{
        void  setListComponents(ArrayList<ModuleActivation>listComponents);
        void  setInfoLicense(String infoLicense, boolean activated);
    }
}

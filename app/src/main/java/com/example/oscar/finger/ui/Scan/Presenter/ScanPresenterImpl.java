package com.example.oscar.finger.ui.Scan.Presenter;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.example.oscar.finger.ApplicationBase;
import com.example.oscar.finger.R;
import com.example.oscar.finger.Utils.Licensing.LicensingManager;
import com.example.oscar.finger.Utils.Licensing.LicensingStateResult;
import com.example.oscar.finger.base.BaseViewContract;
import com.mattprecious.swirl.SwirlView;
import com.neurotec.biometrics.NFinger;
import com.neurotec.biometrics.NSubject;
import com.neurotec.biometrics.client.NBiometricClient;
import com.neurotec.cluster.NCluster;
import com.neurotec.devices.NDeviceManager;
import com.neurotec.devices.NDeviceType;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by Oscar on 29/12/2017.
 */

public class ScanPresenterImpl implements ScanContract, LicensingManager.LicensingStateCallback {
    private ScanView view;
    private NBiometricClient mBiometricClient;

    public ScanPresenterImpl(ScanView view) {
        this.view = view;
    }


    @Override
    public void getLicenseAsync() {
        LicensingManager.getInstance().obtain(ApplicationBase.getIntance().getApplicationContext(),
                this, getComponents());
    }

    @Override
    public void initScanner() {
        if (!LicensingManager.isActivated(LicensingManager.LICENSE_FINGER_DEVICES_SCANNERS) || !LicensingManager.isFingerExtractionActivated()) {
            return;
        }
        NCluster.NATIVE_LIBRARY.getClass();
        mBiometricClient = new NBiometricClient();
        mBiometricClient.setUseDeviceManager(true);
        NDeviceManager deviceManager = mBiometricClient.getDeviceManager();
        deviceManager.setDeviceTypes(EnumSet.of(NDeviceType.ANY));
        mBiometricClient.initialize();
        NDeviceManager.DeviceCollection devices = deviceManager.getDevices();
        if (devices.size() > 0) {
        } else {

        }
    }

    protected List<String> getComponents() {
        return Arrays.asList(LicensingManager.LICENSE_FINGER_DETECTION,
                LicensingManager.LICENSE_FINGER_EXTRACTION,
                LicensingManager.LICENSE_FINGER_MATCHING,
                LicensingManager.LICENSE_FINGER_MATCHING_FAST,
                LicensingManager.LICENSE_FINGER_DEVICES_SCANNERS,
                LicensingManager.LICENSE_FINGER_WSQ,
                LicensingManager.LICENSE_FINGER_STANDARDS_FINGER_TEMPLATES,
                LicensingManager.LICENSE_FINGER_STANDARDS_FINGERS);
    }

    @Override
    public void onLicensingStateChanged(LicensingStateResult stateResult) {
        switch (stateResult.getState()) {
            case OBTAINING:
                view.showProgress();
                break;
            case OBTAINED:
                view.setStatusLicense(R.string.license_obtained);
                view.hideProgress();
                break;
            case NOT_OBTAINED:
                view.setStatusLicense(R.string.license_not_obtained);
                view.hideProgress();
                break;
            default:
                throw new AssertionError("Unknown state: " + stateResult.getState());

        }

    }

    public interface ScanView extends BaseViewContract {
        void showProgress();

        void hideProgress();

        void setStatusLicense(@StringRes int mensaje);

        void onScannerResult(boolean error);
    }
}

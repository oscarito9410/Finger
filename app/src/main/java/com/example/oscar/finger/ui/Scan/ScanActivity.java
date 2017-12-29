package com.example.oscar.finger.ui.Scan;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.oscar.finger.R;
import com.example.oscar.finger.Utils.Licensing.Utils;
import com.example.oscar.finger.ui.Activation.ActivationActivity;
import com.example.oscar.finger.ui.Scan.Presenter.ScanContract;
import com.example.oscar.finger.ui.Scan.Presenter.ScanPresenterImpl;
import com.mattprecious.swirl.SwirlView;
import com.neurotec.lang.NCore;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ScanActivity extends AppCompatActivity  implements ScanPresenterImpl.ScanView {

    @BindView(R.id.swirlView)
    SwirlView swirlView;
    private ProgressDialog dialog;
    private ScanContract presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        setListeners();
        setPresenter();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                startActivity(new Intent(this, ActivationActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onCapturar(View view) {
        if (Utils.canWritteSettings(this)) {
            presenter.initScanner();
        }
    }

    @Override
    public void setPresenter() {
        if(this.presenter==null) {
            this.presenter = new ScanPresenterImpl(this);
            this.presenter.getLicenseAsync();
        }
    }

    @Override
    public void setListeners() {
        NCore.setContext(this);
        this.dialog=new ProgressDialog(this);
        this.dialog.setMessage(getString(R.string.obtaining_license));
        ButterKnife.bind(this);
        swirlView.setState(SwirlView.State.ON);
    }

    @Override
    public void showProgress() {
        this.dialog.show();
    }

    @Override
    public void hideProgress() {
        this.dialog.hide();
    }

    @Override
    public void setStatusLicense(@StringRes  int mensaje) {
        Toast.makeText(this, getString(mensaje), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScannerResult(boolean error) {
        swirlView.setState(error? SwirlView.State.ERROR: SwirlView.State.ON);
    }
}

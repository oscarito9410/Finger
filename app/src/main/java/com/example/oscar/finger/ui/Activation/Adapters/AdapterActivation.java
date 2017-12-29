package com.example.oscar.finger.ui.Activation.Adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.oscar.finger.R;
import com.example.oscar.finger.ui.Activation.Model.ModuleActivation;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Oscar on 29/12/2017.
 */
public class AdapterActivation extends RecyclerView.Adapter<AdapterActivation.ViewHolder> {

    private ArrayList<ModuleActivation>listModuleActivation;
    private LayoutInflater layoutInflater;
    private Context context;

    public AdapterActivation(ArrayList<ModuleActivation> listModuleActivation, Context context) {
        this.listModuleActivation = listModuleActivation;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(this.context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return  new ViewHolder(layoutInflater.inflate(R.layout.item_module,parent,false));
    }

    public  ModuleActivation getItemAtPosition(int position){
        return  this.listModuleActivation.get(position);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvModule.setText(getItemAtPosition(position).getName());
    }

    @Override
    public int getItemCount() {
        return listModuleActivation.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvModule)
        TextView tvModule;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

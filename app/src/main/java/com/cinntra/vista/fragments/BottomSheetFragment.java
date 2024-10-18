package com.cinntra.vista.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cinntra.vista.EasyPrefs.Prefs;
import com.cinntra.vista.R;
import com.cinntra.vista.adapters.BottomSheetAdapter;
import com.cinntra.vista.databinding.AlertDatabseListingBinding;
import com.cinntra.vista.globals.Globals;
import com.cinntra.vista.interfaces.ChangeTeam;
import com.cinntra.vista.interfaces.FragmentRefresher;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;



public class BottomSheetFragment extends BottomSheetDialogFragment implements BottomSheetAdapter.ItemListener {

    //    @BindView(R.id.database_listing)
//    RecyclerView database_listing;
//    @BindView(R.id.cross)
//    ImageView cross;
    FragmentRefresher fragmentRefresher;
    ChangeTeam changeTeam;


    public BottomSheetFragment(Dashboard dashboard) {
        this.changeTeam = dashboard;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    AlertDatabseListingBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=AlertDatabseListingBinding.inflate(inflater,container,false);
        View v = inflater.inflate(R.layout.alert_databse_listing, container, false);

        //ButterKnife.bind(this,v);
        fragmentRefresher =(FragmentRefresher)getActivity();


        binding.databaseListing.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        binding.databaseListing.setAdapter(new BottomSheetAdapter(getActivity(),Dashboard.teamList_Hearchi,this));

        binding.cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return binding.getRoot();
    }


    @Override
    public void onClickAt(int position) {
        Prefs.putString(Globals.SalesEmployeeCode,Dashboard.teamList_Hearchi.get(position).getSalesEmployeeCode());
        Prefs.putString(Globals.name,Dashboard.teamList_Hearchi.get(position).getFirstName()+" "+Dashboard.teamList_Hearchi.get(position).getLastName());

        Globals.TeamEmployeeID = Dashboard.teamList_Hearchi.get(position).getId().toString();
        Prefs.putString(Globals.Role,Dashboard.teamList_Hearchi.get(position).getRole());
        changeTeam.changeTeam(Dashboard.teamList_Hearchi.get(position).getFirstName()+" "+Dashboard.teamList_Hearchi.get(position).getLastName());
        fragmentRefresher.onRefresh();
        dismiss();
    }
}
package com.sugata.mycredibleinfo.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sugata.mycredibleinfo.ProfDetailsClasses.ProfessionalDetailsData;
import com.sugata.mycredibleinfo.R;
import com.sugata.mycredibleinfo.UserDetails.ProfessionalDetailsActivity;
import com.sugata.mycredibleinfo.remote.APIUtils;
import com.sugata.mycredibleinfo.remote.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.sugata.mycredibleinfo.LoginActivity.MY_PREF;

public class ProfFrag extends Fragment {

    private TextView organizationTextView, designationTextView, startDateTextView, endDateTextView;
    private Button updateDetailsButton;

    UserService userService;

    private int userId;

    public ProfFrag() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_professional, container, false);

        userService = APIUtils.getUserService();

        organizationTextView = rootView.findViewById(R.id.organization_text_view);
        designationTextView = rootView.findViewById(R.id.designation_text_view);
        startDateTextView = rootView.findViewById(R.id.start_date_text_view);
        endDateTextView = rootView.findViewById(R.id.end_date_text_view);

        updateDetailsButton = rootView.findViewById(R.id.update_details_button);

        SharedPreferences prefs = this.getActivity().getSharedPreferences(MY_PREF, MODE_PRIVATE);
        userId = prefs.getInt("id", 0);

        getProfessionalDetails(userId);

        updateDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProfessionalDetailsActivity.class);
                intent.putExtra("update", "true");
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void getProfessionalDetails(int userId) {
        Call<ProfessionalDetailsData> call = userService.getProfessionalDetails(userId);
        call.enqueue(new Callback<ProfessionalDetailsData>() {
            @Override
            public void onResponse(Call<ProfessionalDetailsData> call, Response<ProfessionalDetailsData> response) {
                if(response.body() != null) {
                    organizationTextView.setText(response.body().getData().getOrganisation());
                    designationTextView.setText(response.body().getData().getDesignation());
                    startDateTextView.setText(response.body().getData().getStart_date());
                    endDateTextView.setText(response.body().getData().getEnd_date());
                }
            }

            @Override
            public void onFailure(Call<ProfessionalDetailsData> call, Throwable t) {
                Toast.makeText(getContext(), "Get professional details failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

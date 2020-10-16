package com.sugata.mycredibleinfo.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.sugata.mycredibleinfo.EduDetailsClasses.EducationDetailsData;
import com.sugata.mycredibleinfo.R;
import com.sugata.mycredibleinfo.UserDetails.EducationDetailsActivity;
import com.sugata.mycredibleinfo.remote.APIUtils;
import com.sugata.mycredibleinfo.remote.UserService;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.sugata.mycredibleinfo.LoginActivity.MY_PREF;

public class EduFrag extends Fragment {

    final private String imageUrl = "http://139.59.65.145:9090/user/educationdetail/certificate/";

    private TextView organizationTextView, degreeTextView, locationTextView, startYearTextView, endYearTextView;
    private Button updateDetailsButton;
    private ImageView certiPic;

    private int userId;

    UserService userService;

    public EduFrag(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_education, container, false);

        userService = APIUtils.getUserService();

        organizationTextView = rootView.findViewById(R.id.organization_text_view);
        degreeTextView = rootView.findViewById(R.id.degree_text_view);
        locationTextView = rootView.findViewById(R.id.location_text_view);
        startYearTextView = rootView.findViewById(R.id.start_year_text_view);
        endYearTextView = rootView.findViewById(R.id.end_year_text_view);
        certiPic = rootView.findViewById(R.id.certi);

        updateDetailsButton = rootView.findViewById(R.id.update_details_button);

        SharedPreferences prefs = this.getActivity().getSharedPreferences(MY_PREF, MODE_PRIVATE);
        userId = prefs.getInt("id", 0);

        getEducationDetails(userId);
        getCertiPic();

        updateDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EducationDetailsActivity.class);
                intent.putExtra("update", "true");
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void getCertiPic() {
        Uri uri = Uri.parse(imageUrl + userId);
        Picasso.with(getActivity()).load(uri).into(certiPic);
    }

    private void getEducationDetails(int userId) {

        Call<EducationDetailsData> call = userService.getEducationalDetails(userId);
        call.enqueue(new Callback<EducationDetailsData>() {
            @Override
            public void onResponse(Call<EducationDetailsData> call, Response<EducationDetailsData> response) {
                if(response.body() != null) {
                    organizationTextView.setText(response.body().getData().getOrganisation());
                    degreeTextView.setText(response.body().getData().getDegree());
                    locationTextView.setText(response.body().getData().getLocation());
                    startYearTextView.setText(response.body().getData().getStart_year());
                    endYearTextView.setText(response.body().getData().getEnd_year());
                }
            }

            @Override
            public void onFailure(Call<EducationDetailsData> call, Throwable t) {
                Toast.makeText(getContext(), "Get education details filed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

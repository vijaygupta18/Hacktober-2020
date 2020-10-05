package com.sugata.mycredibleinfo.UserDetails;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sugata.mycredibleinfo.HomeActivity;
import com.sugata.mycredibleinfo.ProfDetailsClasses.ProfessionalDetails;
import com.sugata.mycredibleinfo.ProfDetailsClasses.ProfessionalDetailsData;
import com.sugata.mycredibleinfo.R;
import com.sugata.mycredibleinfo.remote.APIUtils;
import com.sugata.mycredibleinfo.remote.UserService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sugata.mycredibleinfo.LoginActivity.MY_PREF;

public class ProfessionalDetailsActivity extends AppCompatActivity {

    Button saveButton;
    EditText organizationEditText, designationEditText;
    Spinner startMonthSpinner, startYearSpinner, endMonthSpinner, endYearSpinner;
    CheckBox currWorkCheckBox;
    LinearLayout endLinearLayout;
    String organization, designation;
    String startMonth, startYear, endMonth, endYear;
    String startDate, endDate;

    UserService userService;

    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_details);

        organizationEditText = findViewById(R.id.organization_edit_text);
        designationEditText = findViewById(R.id.designation_edit_text);
        startMonthSpinner = findViewById(R.id.start_month_spinner);
        startYearSpinner = findViewById(R.id.start_year_spinner);
        endMonthSpinner = findViewById(R.id.end_month_spinner);
        endYearSpinner = findViewById(R.id.end_year_spinner);
        endLinearLayout = findViewById(R.id.end_date_linear_layout);

        currWorkCheckBox = findViewById(R.id.curr_work_check_box);
        currWorkCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currWorkCheckBox.isChecked())
                {
                    endLinearLayout.setVisibility(View.GONE);
                }
                else if(!currWorkCheckBox.isChecked())
                {
                    endLinearLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        userService = APIUtils.getUserService();

        SharedPreferences prefs = getSharedPreferences(MY_PREF, MODE_PRIVATE);
        userId = prefs.getInt("id", 0);

        final String isUpdate = getIntent().getStringExtra("update");

        if (isUpdate == null)
            getSupportActionBar().setTitle("Set Professional Details");
        else {
            getSupportActionBar().setTitle("Edit Professional Details");
            getProfessionalDetails();
        }

        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                organization = organizationEditText.getText().toString().trim();
                designation = designationEditText.getText().toString().trim();
                startMonth = startMonthSpinner.getSelectedItem().toString();
                startYear = startYearSpinner.getSelectedItem().toString();
                if(currWorkCheckBox.isChecked())
                {
                    endMonth = endMonthSpinner.getItemAtPosition(Integer.parseInt(getDate().substring(3, 5)) - 1).toString();
                    endYear = getDate().substring(6);
                }
                else if(!currWorkCheckBox.isChecked())
                {
                    endMonth = endMonthSpinner.getSelectedItem().toString();
                    endYear = endYearSpinner.getSelectedItem().toString();
                }

                startDate = startMonth + ", " + startYear;
                endDate = endMonth + ", " + endYear;

                ProfessionalDetails professionalDetails = new ProfessionalDetails(endDate, organization, designation, startDate);

                if (isUpdate == null)
                    setProfessionalDetails(professionalDetails);
                else {
                    updateProfessionalDetails(professionalDetails);
                }
            }
        });
    }

    public void getProfessionalDetails()
    {
        Call<ProfessionalDetailsData> call = userService.getProfessionalDetails(userId);
        call.enqueue(new Callback<ProfessionalDetailsData>() {
            @Override
            public void onResponse(Call<ProfessionalDetailsData> call, Response<ProfessionalDetailsData> response) {
                if(response.body() != null) {
                    organizationEditText.setText(response.body().getData().getOrganisation());
                    designationEditText.setText(response.body().getData().getDesignation());
                    startMonthSpinner.setSelection(getIndex(startMonthSpinner, response.body().getData().getStart_date().substring(0, 3)));
                    startYearSpinner.setSelection(getIndex(startYearSpinner, response.body().getData().getStart_date().substring(5)));
                    endMonthSpinner.setSelection(getIndex(endMonthSpinner, response.body().getData().getEnd_date().substring(0, 3)));
                    endYearSpinner.setSelection(getIndex(endYearSpinner, response.body().getData().getEnd_date().substring(5)));
                } else {
                    Toast.makeText(ProfessionalDetailsActivity.this, "Professional Details Response Empty", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ProfessionalDetailsData> call, Throwable t) {
                Toast.makeText(ProfessionalDetailsActivity.this, "Response Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setProfessionalDetails(ProfessionalDetails professionalDetails)
    {
        Call<ProfessionalDetailsData> call = userService.setProfessionalDetails(userId, professionalDetails);
        call.enqueue(new Callback<ProfessionalDetailsData>() {
            @Override
            public void onResponse(Call<ProfessionalDetailsData> call, Response<ProfessionalDetailsData> response) {
                ProfessionalDetailsData personalDetailsData = new ProfessionalDetailsData();
                personalDetailsData.setData(response.body().getData());

                Intent intent = new Intent(ProfessionalDetailsActivity.this, EducationDetailsActivity.class);
                intent.putExtra("id", userId);
                finish();
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ProfessionalDetailsData> call, Throwable t) {
                Toast.makeText(ProfessionalDetailsActivity.this, "Set Professional details Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateProfessionalDetails(final ProfessionalDetails professionalDetails)
    {
        Call<ProfessionalDetailsData> call = userService.updateProfessionalDetails(userId, professionalDetails);
        call.enqueue(new Callback<ProfessionalDetailsData>() {
            @Override
            public void onResponse(Call<ProfessionalDetailsData> call, Response<ProfessionalDetailsData> response) {
                Toast.makeText(ProfessionalDetailsActivity.this, "Professional Details Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfessionalDetailsActivity.this, HomeActivity.class);
                intent.putExtra("id", userId);
                finish();
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ProfessionalDetailsData> call, Throwable t) {
                Toast.makeText(ProfessionalDetailsActivity.this, "Update Professional details Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getIndex(Spinner spinner, String myString){
        for (int i = 0; i < spinner.getCount(); i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        return 0;
    }

    private String getDate(){
        DateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy");
        String date = dfDate.format(Calendar.getInstance().getTime());
        return date;
    }
}

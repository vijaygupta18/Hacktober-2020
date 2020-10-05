package com.sugata.mycredibleinfo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.design.widget.TabLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.sugata.mycredibleinfo.Fragments.EduFrag;
import com.sugata.mycredibleinfo.Fragments.PerFrag;
import com.sugata.mycredibleinfo.Fragments.ProfFrag;
import com.sugata.mycredibleinfo.PerDetailsClasses.PersonalDetailsData;
import com.sugata.mycredibleinfo.ProfDetailsClasses.ProfessionalDetailsData;
import com.sugata.mycredibleinfo.ProfileImgClasses.StatusMessage;
import com.sugata.mycredibleinfo.remote.APIUtils;
import com.sugata.mycredibleinfo.remote.UserService;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.sugata.mycredibleinfo.LoginActivity.MY_PREF;

public class HomeActivity extends AppCompatActivity {

    final private String imageUrl = "http://139.59.65.145:9090/user/personaldetail/profilepic/";

    private TextView nameTextView, emailTextView, organizationTextView, locationTextView;

    private ImageView ProfilePic;

    private String name, email, organization, location;

    private ViewPager mViewPager;

    private int userId;

    private UserService userService;

    public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
        public SimpleFragmentPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            switch(position)
            {
                case 0 :
                    return new EduFrag();
                case 1 :
                    return new ProfFrag();
                case 2:
                    return new PerFrag();
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Education";
                case 1:
                    return "Professional";
                case 2:
                    return "Personal";
                default:
                    return null;
            }
        }

        @Override
        public int getCount()
        {
            return 3;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nameTextView = findViewById(R.id.name_text_view);
        emailTextView = findViewById(R.id.email_text_view);
        organizationTextView = findViewById(R.id.organization_text_view);
        locationTextView = findViewById(R.id.location_text_view);

        ProfilePic = findViewById(R.id.user_profile_pic);

        name = email = organization = location = "";

        SharedPreferences prefs = getSharedPreferences(MY_PREF, MODE_PRIVATE);
        email = prefs.getString("email", "-");
        userId = prefs.getInt("id", 0);

        emailTextView.setText(email);

        SimpleFragmentPagerAdapter simpleFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.fragment_container);
        mViewPager.setAdapter(simpleFragmentPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        userService = APIUtils.getUserService();

        getProfilePic();
        getPersonalDetails();
        getProfessionalDetails();
    }

    private void getProfessionalDetails() {
        Call<ProfessionalDetailsData> call = userService.getProfessionalDetails(userId);
        call.enqueue(new Callback<ProfessionalDetailsData>() {
            @Override
            public void onResponse(Call<ProfessionalDetailsData> call, Response<ProfessionalDetailsData> response) {
                if(response.body() != null) {
                    organization = response.body().getData().getOrganisation();
                    organizationTextView.setText(organization);
                } else {
                    Toast.makeText(HomeActivity.this, "Professional Details Response Empty", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ProfessionalDetailsData> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Response Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPersonalDetails() {
        Call<PersonalDetailsData> call = userService.getPersonalDetails(userId);
        call.enqueue(new Callback<PersonalDetailsData>() {
            @Override
            public void onResponse(Call<PersonalDetailsData> call, Response<PersonalDetailsData> response) {
                if(response.body() != null) {
                    name = response.body().getData().getName();
                    location = response.body().getData().getLocation();
                    nameTextView.setText(name);
                    locationTextView.setText(location);
                } else {
                    Toast.makeText(HomeActivity.this, "Personal Details Response Empty", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<PersonalDetailsData> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Response Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProfilePic() {
        Uri uri = Uri.parse(imageUrl + userId);
        Picasso.with(getApplicationContext()).load(uri)
                .transform(new CropCircleTransformation()).into(ProfilePic);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.sign_out_menu:
                SharedPreferences prefs = getSharedPreferences(MY_PREF, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.apply();
                Toast.makeText(this, "Signed out Successfully!", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                return true;
            case R.id.delete_account_menu:
                deleteAccount();
                finish();
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteAccount() {
        deleteProfessionalData();
        deleteEducationData();
        Toast.makeText(this, "Account deleted successfully!", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Please login or signup to proceed!", Toast.LENGTH_SHORT).show();
    }

    private void deleteProfessionalData() {
        Call<StatusMessage> call = userService.deleteProfessionalDetails(userId);
        call.enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {

            }
            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Delete Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteEducationData()
    {
        Call<StatusMessage> call = userService.deleteEducationalDetails(userId);
        call.enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {

            }
            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Delete Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

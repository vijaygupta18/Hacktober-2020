package com.sugata.mycredibleinfo.UserDetails;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.sugata.mycredibleinfo.HomeActivity;
import com.sugata.mycredibleinfo.PerDetailsClasses.PersonalDetails;
import com.sugata.mycredibleinfo.PerDetailsClasses.PersonalDetailsData;
import com.sugata.mycredibleinfo.ProfileImgClasses.StatusMessage;
import com.sugata.mycredibleinfo.ProfileImgClasses.Photo;
import com.sugata.mycredibleinfo.R;
import com.sugata.mycredibleinfo.remote.APIUtils;
import com.sugata.mycredibleinfo.remote.UserService;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sugata.mycredibleinfo.LoginActivity.MY_PREF;

public class PersonalDetailsActivity extends AppCompatActivity {

    final private String imageUri = "content://media/internal/images/media";
    final private String imageUrl = "http://139.59.65.145:9090/user/personaldetail/profilepic/";

    private String name,email,mobile,location,links,skills;
    private int userId;

    private EditText nameEditText,emailEditText,mobileEditText,locationEditText,linksEditText,skillsEditText;
    private ImageView ProfilePic;
    private Button saveButton;

    UserService userService;

    private Bitmap profilePicBitmap;
    private ByteArrayOutputStream bos;
    private byte[] imageByteArray;
    private String encodedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        nameEditText = findViewById(R.id.full_name_edit_text);
        mobileEditText = findViewById(R.id.mobile_edit_text);
        locationEditText = findViewById(R.id.location_edit_text);
        linksEditText = findViewById(R.id.links_edit_text);
        skillsEditText = findViewById(R.id.skills_edit_text);
        saveButton = findViewById(R.id.save_button);

        ProfilePic = findViewById(R.id.user_profile_pic);
        ProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse(imageUri));
                startActivityForResult(intent, 1);
            }
        });

        userService = APIUtils.getUserService();

        SharedPreferences prefs = getSharedPreferences(MY_PREF, MODE_PRIVATE);
        userId = prefs.getInt("id", 0);

        final String isUpdate = getIntent().getStringExtra("update");

        if(isUpdate == null)
            getSupportActionBar().setTitle("Set Personal Details");
        else {
            getSupportActionBar().setTitle("Edit Personal Details");
            getPersonalDetails();
            getProfileImg();
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            name = nameEditText.getText().toString().trim();
            mobile = mobileEditText.getText().toString().trim();
            location = locationEditText.getText().toString().trim();
            links = linksEditText.getText().toString().trim();
            skills = skillsEditText.getText().toString().trim();

                PersonalDetails personalDetails = new PersonalDetails(skills, mobile, name, links, location, email);

                if (isUpdate == null)
                    setPersonalDetails(personalDetails);
                else {
                    updatePersonalDetails(personalDetails);

                }
                setProfileImg(encodedImage);

        };

    });
    }

    private void setProfileImg(String encodedImage) {

        Photo photo = new Photo(String.valueOf(userId),encodedImage);
        Call<StatusMessage> call = userService.setProfilePic(photo);
        call.enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {
                Toast.makeText(PersonalDetailsActivity.this, "Profile pic set successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                Toast.makeText(PersonalDetailsActivity.this, "Update Personal details Failed: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getProfileImg() {
        Uri uri = Uri.parse(imageUrl + userId);
        Picasso.with(getApplicationContext()).load(uri).into(ProfilePic);
    }

    private void updatePersonalDetails(PersonalDetails personalDetails) {
        Call<PersonalDetailsData> call = userService.updatePersonalDetails(userId, personalDetails);
        call.enqueue(new Callback<PersonalDetailsData>() {
            @Override
            public void onResponse(Call<PersonalDetailsData> call, Response<PersonalDetailsData> response) {
                Toast.makeText(PersonalDetailsActivity.this, "Personal Details Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PersonalDetailsActivity.this, HomeActivity.class);
                intent.putExtra("id", userId);
                finish();
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<PersonalDetailsData> call, Throwable t) {
                Toast.makeText(PersonalDetailsActivity.this, "Update Personal details Failed: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setPersonalDetails(PersonalDetails personalDetails) {
        Call<PersonalDetailsData> call = userService.setPersonalDetails(userId, personalDetails);
        call.enqueue(new Callback<PersonalDetailsData>() {
            @Override
            public void onResponse(Call<PersonalDetailsData> call, Response<PersonalDetailsData> response) {
                PersonalDetailsData personalDetailsData = new PersonalDetailsData();
                personalDetailsData.setData(response.body().getData());

                Intent intent = new Intent(PersonalDetailsActivity.this, ProfessionalDetailsActivity.class);
                intent.putExtra("id", userId);
                finish();
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<PersonalDetailsData> call, Throwable t) {
                Toast.makeText(PersonalDetailsActivity.this, "Set Personal details Failed: ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPersonalDetails() {
        Call<PersonalDetailsData> call = userService.getPersonalDetails(userId);
        call.enqueue(new Callback<PersonalDetailsData>() {
            @Override
            public void onResponse(Call<PersonalDetailsData> call, Response<PersonalDetailsData> response) {
                if(response.body() != null) {
                    nameEditText.setText(response.body().getData().getName());
                    mobileEditText.setText(response.body().getData().getMobile_no());
                    locationEditText.setText(response.body().getData().getLocation());
                    linksEditText.setText(response.body().getData().getLinks());
                    skillsEditText.setText(response.body().getData().getSkills());
                } else {
                    Toast.makeText(PersonalDetailsActivity.this, "Personal Details Empty", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<PersonalDetailsData> call, Throwable t) {
                Toast.makeText(PersonalDetailsActivity.this, "Response Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 1)
        {
            Uri uri = data.getData();
            ProfilePic.setImageURI(uri);

            //String picturePath = getRealPathFromURIPath(uri, PersonalDetailsActivity.this);

            profilePicBitmap = ((BitmapDrawable) ProfilePic.getDrawable()).getBitmap();
            //BitmapFactory.decodeFile(picturePath);
            bos = new ByteArrayOutputStream();
            profilePicBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            imageByteArray = bos.toByteArray();
            encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);
        }
    }


}

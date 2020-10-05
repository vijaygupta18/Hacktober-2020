package com.sugata.mycredibleinfo.UserDetails;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.telephony.mbms.FileInfo;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.sugata.mycredibleinfo.EduDetailsClasses.EducationDetails;
import com.sugata.mycredibleinfo.EduDetailsClasses.EducationDetailsData;
import com.sugata.mycredibleinfo.HomeActivity;
import com.sugata.mycredibleinfo.ProfileImgClasses.Photo;
import com.sugata.mycredibleinfo.ProfileImgClasses.StatusMessage;
import com.sugata.mycredibleinfo.R;
import com.sugata.mycredibleinfo.remote.APIUtils;
import com.sugata.mycredibleinfo.remote.UserService;

import java.io.ByteArrayOutputStream;
import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sugata.mycredibleinfo.LoginActivity.MY_PREF;

public class EducationDetailsActivity extends AppCompatActivity {

    final private String imageUri = "content://media/internal/images/media";
    final private String imageUrl = "http://139.59.65.145:9090/user/educationdetail/certificate/";

    private Button saveButton;
    private EditText organizationEditText, degreeEditText, locationEditText;
    private Spinner startYearSpinner, endYearSpinner;
    private ImageView certiPic;

    private String organization, degree, location;
    private String startYear, endYear;

    UserService userService;

    private int userId;

    private String picturePath;

    private Bitmap certiPicBitmap;
    private ByteArrayOutputStream bos;
    private byte[] imageByteArray;
    private String encodedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_details);

        organizationEditText = findViewById(R.id.organization_edit_text);
        degreeEditText = findViewById(R.id.degree_edit_text);
        locationEditText = findViewById(R.id.location_edit_text);
        startYearSpinner = findViewById(R.id.start_year_spinner);
        endYearSpinner = findViewById(R.id.end_year_spinner);

        certiPic = findViewById(R.id.certi);
        certiPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse(imageUri));
                startActivityForResult(intent, 1);
            }
        });

        SharedPreferences prefs = getSharedPreferences(MY_PREF, MODE_PRIVATE);
        userId = prefs.getInt("id", 0);

        userService = APIUtils.getUserService();

        final String isUpdate = getIntent().getStringExtra("update");

        if (isUpdate == null)
            getSupportActionBar().setTitle("Set Education Details");
        else {
            getSupportActionBar().setTitle("Edit Education Details");
            getEducationDetails();
            //getCertiImg();
        }

        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                organization = organizationEditText.getText().toString().trim();
                degree = degreeEditText.getText().toString().trim();
                location = locationEditText.getText().toString().trim();
                startYear = startYearSpinner.getSelectedItem().toString();
                endYear = endYearSpinner.getSelectedItem().toString();

                EducationDetails educationDetails = new EducationDetails(startYear, degree, organization, location, endYear);

                if (isUpdate == null)
                    setEducationDetails(educationDetails);
                else {
                    updateEducationDetails(educationDetails);
                }
                setCertiImg();
            }
        });
    }

    private void setCertiImg() {

        File file = new File(picturePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("photo",file.getName(),requestBody);
        RequestBody userid = RequestBody.create(MultipartBody.FORM, String.valueOf(userId));

        Call<ResponseBody> call = userService.setCertificates(part,userid);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EducationDetailsActivity.this, "Certificate set successfully", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(EducationDetailsActivity.this, "Update Personal details Failed: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        /*Photo photo = new Photo(String.valueOf(userId),encodedImage);
        Call<StatusMessage> call = userService.setCertificates(photo);
        call.enqueue(new Callback<StatusMessage>() {
            @Override
            public void onResponse(Call<StatusMessage> call, Response<StatusMessage> response) {
                Toast.makeText(EducationDetailsActivity.this, "Cerificate set successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<StatusMessage> call, Throwable t) {
                Toast.makeText(EducationDetailsActivity.this, "Update Personal details Failed: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    private void getCertiImg() {
        Uri uri = Uri.parse(imageUrl + userId);
        Picasso.with(getApplicationContext()).load(uri).into(certiPic);
    }

    public void getEducationDetails()
    {
        Call<EducationDetailsData> call = userService.getEducationalDetails(userId);
        call.enqueue(new Callback<EducationDetailsData>() {
            @Override
            public void onResponse(Call<EducationDetailsData> call, Response<EducationDetailsData> response) {
                if(response.body() != null) {
                    organizationEditText.setText(response.body().getData().getOrganisation());
                    degreeEditText.setText(response.body().getData().getDegree());
                    locationEditText.setText(response.body().getData().getLocation());
                    startYearSpinner.setSelection(getIndex(startYearSpinner, response.body().getData().getStart_year()));
                    endYearSpinner.setSelection(getIndex(endYearSpinner, response.body().getData().getEnd_year()));
                } else {
                    Toast.makeText(EducationDetailsActivity.this, "Professional Details Response Empty", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<EducationDetailsData> call, Throwable t) {
                Toast.makeText(EducationDetailsActivity.this, "Response Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setEducationDetails(EducationDetails educationDetails)
    {
        Call<EducationDetailsData> call = userService.setEducationalDetails(userId, educationDetails);
        call.enqueue(new Callback<EducationDetailsData>() {
            @Override
            public void onResponse(Call<EducationDetailsData> call, Response<EducationDetailsData> response) {

                Intent intent = new Intent(EducationDetailsActivity.this, HomeActivity.class);
                intent.putExtra("id", userId);
                finish();
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<EducationDetailsData> call, Throwable t) {
                Toast.makeText(EducationDetailsActivity.this, "Set Education details Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateEducationDetails(final EducationDetails educationDetails)
    {
        Call<EducationDetailsData> call = userService.updateEducationalDetails(userId, educationDetails);
        call.enqueue(new Callback<EducationDetailsData>() {
            @Override
            public void onResponse(Call<EducationDetailsData> call, Response<EducationDetailsData> response) {
                Toast.makeText(EducationDetailsActivity.this, "Education Details Updated", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EducationDetailsActivity.this, HomeActivity.class);
                intent.putExtra("id", userId);
                finish();
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<EducationDetailsData> call, Throwable t) {
                Toast.makeText(EducationDetailsActivity.this, "Update Education details Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        return 0;
    }

    private String getRealPathFromURIPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),uri,projection,null,null,null);
        Cursor cursor = loader.loadInBackground();
        int column_idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_idx);
        cursor.close();
        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 1)
        {
            Uri uri = data.getData();
            certiPic.setImageURI(uri);

            picturePath = getRealPathFromURIPath(uri);

            /*certiPicBitmap = ((BitmapDrawable) certiPic.getDrawable()).getBitmap();
            //BitmapFactory.decodeFile(picturePath);
            bos = new ByteArrayOutputStream();
            certiPicBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            imageByteArray = bos.toByteArray();
            encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);*/
        }
    }
}

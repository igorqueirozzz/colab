package br.com.app.colab.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import br.com.app.colab.R;
import br.com.app.colab.dto.UpdatePhotoDTO;
import br.com.app.colab.helper.PermissionCheck;
import br.com.app.colab.model.CurrentUser;
import br.com.app.colab.services.RetrofitServices;
import br.com.app.colab.services.UserServices;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfigActivity extends AppCompatActivity {

    private CircleImageView circleImageView;
    private final int REQUEST_CODE_GALLERY = 200;
    private StorageReference firebaseStorage = FirebaseStorage.getInstance().getReference();;
    private Bitmap image = null;
    private SpinKitView spinKitView;
    private TextView txtMyProfile;
    private TextView txtTouchToChange;
    private TextView txtPasswordMessage;
    private TextInputLayout txt_newPassword;
    private TextInputLayout txt_newPasswordConfirm;
    private Button saveButton;

    private String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        spinKitView = findViewById(R.id.progressConfig);
        Sprite sprite = new CubeGrid();
        spinKitView.setIndeterminateDrawable(sprite);
        PermissionCheck.check(permission, this, 1);
        circleImageView = findViewById(R.id.userPhoto_config);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
                loading();
            }
        });
        txtMyProfile = findViewById(R.id.txt_myprofile);
        txtTouchToChange = findViewById(R.id.txt_touchToChangePhoto);
        txtPasswordMessage = findViewById(R.id.txt_user_config);
        txt_newPassword = findViewById(R.id.txt_newPassword);
        txt_newPasswordConfirm = findViewById(R.id.txt_newPasswordConfirm);
        saveButton = findViewById(R.id.update_user_button);
        Glide.with(this).load(CurrentUser.getPhoto_profile()).into(circleImageView);

        Log.i("ONCREATE============", CurrentUser.getPhoto_profile());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
            try {
                Uri uri = data.getData();
                image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                updateUser();
            } catch (Exception e){
                loaded();
                e.printStackTrace();
            }
        }

    public void finish(View view) {
        super.finish();
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int permission : grantResults){
            if(permission == PackageManager.PERMISSION_DENIED){
                Toast.makeText(this, "Aceite as permissões para fazer alterações", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    public void updateUser(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        StorageReference imageRef = firebaseStorage.child("images").child("profile").child(String.valueOf(CurrentUser.getEmployerId())).child("photo.png");

        UploadTask uploadTask = imageRef.putBytes(imageBytes);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ConfigActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(ConfigActivity.this, "Sucesso", Toast.LENGTH_SHORT).show();
                imageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        String photoUrl = task.getResult().toString();
                        updateUserPhoto(photoUrl);
                        Glide.with(ConfigActivity.this).load(photoUrl).into(circleImageView);

                    }
                });
            }
        });

    }
    public void updateUserPhoto(String photoUrl){
        UpdatePhotoDTO updatePhotoDTO = new UpdatePhotoDTO();
        updatePhotoDTO.setUserid(CurrentUser.getId());
        updatePhotoDTO.setPhotoUrl(photoUrl);
        UserServices userServices = RetrofitServices.getRetrofitService().create(UserServices.class);
        Call<String> userCall = userServices.updatePhoto(updatePhotoDTO);
        userCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("ERRO 1", "CODE: " + response.body());
                CurrentUser.setPhoto_profile(photoUrl);
                Log.i("ONSET============", CurrentUser.getPhoto_profile());
                Glide.with(ConfigActivity.this).load(photoUrl).into(circleImageView);
                loaded();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("IGOR", "CODE: " + t.getMessage());
                CurrentUser.setPhoto_profile(photoUrl);
                Glide.with(ConfigActivity.this).load(photoUrl).into(circleImageView);
                loaded();
            }
        });
    }

    public void updateUserPassword(){

    }

    public void loading(){
        spinKitView.setVisibility(View.VISIBLE);
        circleImageView.setVisibility(View.INVISIBLE);
        txtMyProfile.setVisibility(View.INVISIBLE);
        txtTouchToChange.setVisibility(View.INVISIBLE);
        txtPasswordMessage.setVisibility(View.INVISIBLE);
        txt_newPassword.setVisibility(View.INVISIBLE);
        txt_newPasswordConfirm.setVisibility(View.INVISIBLE);
        saveButton.setVisibility(View.INVISIBLE);
    }
    public void loaded(){
        spinKitView.setVisibility(View.INVISIBLE);
        circleImageView.setVisibility(View.VISIBLE);
        txtMyProfile.setVisibility(View.VISIBLE);
        txtTouchToChange.setVisibility(View.VISIBLE);
        txtPasswordMessage.setVisibility(View.VISIBLE);
        txt_newPassword.setVisibility(View.VISIBLE);
        txt_newPasswordConfirm.setVisibility(View.VISIBLE);
        saveButton.setVisibility(View.VISIBLE);
    }
}
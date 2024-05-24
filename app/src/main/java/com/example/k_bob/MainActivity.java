// MainActivity.java
package com.example.k_bob;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;


import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 100;
    private static final int REQUEST_CODE_ADD_PROFILE = 101;
    private static final String PREFS_NAME = "profiles_preferences";
    private static final String DARK_MODE_PREF = "dark_mode";
    private static final String ACTIVE_PROFILE = "active_profile";
    private String currentProfileId;
    private String currentProfileName;
    private Set<String> avoidIngredients;
    String secretKey = "TG11RFpzY3dHaWFtbFFZVkdhUHRpWElxaU9PSE9Mb0k=";

    private void loadProfilePreferences() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        currentProfileId = preferences.getString(ACTIVE_PROFILE, "");
        currentProfileName = preferences.getString(currentProfileId + "_name", "No Profile Selected");
        boolean isDarkMode = preferences.getBoolean(DARK_MODE_PREF, false);
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
    private void loadUserPreferences() {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        avoidIngredients = new HashSet<>();

        if (preferences.getBoolean(currentProfileId + "_avoid_beef", false)) {
            avoidIngredients.add("쇠고기");
            avoidIngredients.add("소고기");
            avoidIngredients.add("비프");
        }
        if (preferences.getBoolean(currentProfileId + "_avoid_pork", false)) {
            avoidIngredients.add("돼지고기");
            avoidIngredients.add("돈육");
            avoidIngredients.add("포크");
        }
        if (preferences.getBoolean(currentProfileId + "_avoid_shellfish", false)) {
            avoidIngredients.add("조개류");
            avoidIngredients.add("갑각류");
            avoidIngredients.add("새우");
            avoidIngredients.add("게");
            avoidIngredients.add("바닷가재");
        }
        if (preferences.getBoolean(currentProfileId + "_avoid_fish", false)) {
            avoidIngredients.add("어류");
            avoidIngredients.add("어패류");
            avoidIngredients.add("물고기");
            avoidIngredients.add("생선");
        }
        if (preferences.getBoolean(currentProfileId + "_avoid_peanut", false)) {
            avoidIngredients.add("땅콩");
            avoidIngredients.add("피넛");
            avoidIngredients.add("땅콩버터");
        }
        if (preferences.getBoolean(currentProfileId + "_avoid_chicken", false)) {
            avoidIngredients.add("닭고기");
            avoidIngredients.add("치킨");
            avoidIngredients.add("계육");
        }
        if (preferences.getBoolean(currentProfileId + "_avoid_lamb", false)) {
            avoidIngredients.add("양고기");
            avoidIngredients.add("램");
            avoidIngredients.add("램고기");
        }
        if (preferences.getBoolean(currentProfileId + "_avoid_egg", false)) {
            avoidIngredients.add("계란");
            avoidIngredients.add("달걀");
            avoidIngredients.add("에그");
            avoidIngredients.add("알류");
        }
        if (preferences.getBoolean(currentProfileId + "_avoid_dairy", false)) {
            avoidIngredients.add("우유");
            avoidIngredients.add("유제품");
            avoidIngredients.add("치즈");
            avoidIngredients.add("버터");
            avoidIngredients.add("요구르트");
        }
        if (preferences.getBoolean(currentProfileId + "_avoid_flour", false)) {
            avoidIngredients.add("밀가루");
            avoidIngredients.add("밀");
            avoidIngredients.add("소맥");
        }

        if (preferences.getBoolean(currentProfileId + "_avoid_tree_nuts", false)) {
            avoidIngredients.add("호두");
            avoidIngredients.add("견과류");
            avoidIngredients.add("아몬드");
            avoidIngredients.add("캐슈넛");
            avoidIngredients.add("헤이즐넛");
            avoidIngredients.add("피칸");
        }
        if (preferences.getBoolean(currentProfileId + "_avoid_soy", false)) {
            avoidIngredients.add("대두");
            avoidIngredients.add("콩");
            avoidIngredients.add("소야");
            avoidIngredients.add("두유");
        }
        if (preferences.getBoolean(currentProfileId + "_avoid_sesame", false)) {
            avoidIngredients.add("참깨");
            avoidIngredients.add("깨");
            avoidIngredients.add("세사미");
        }
        if (preferences.getBoolean(currentProfileId + "_avoid_wheat", false)) {
            avoidIngredients.add("밀");
            avoidIngredients.add("소맥");
            avoidIngredients.add("밀가루");
        }
        if (preferences.getBoolean(currentProfileId + "_avoid_corn", false)) {
            avoidIngredients.add("옥수수");
            avoidIngredients.add("콘");
            avoidIngredients.add("옥수수가루");
        }
        if (preferences.getBoolean(currentProfileId + "_avoid_gluten", false)) avoidIngredients.add("글루텐");
        if (preferences.getBoolean(currentProfileId + "_avoid_mustard", false)) {
            avoidIngredients.add("겨자");
            avoidIngredients.add("머스타드");
        }
        if (preferences.getBoolean(currentProfileId + "_avoid_celery", false)) avoidIngredients.add("샐러리");
        if (preferences.getBoolean(currentProfileId + "_avoid_sulfites", false)) {
            avoidIngredients.add("황");
            avoidIngredients.add("아황산염");
            avoidIngredients.add("이산화황");
        }
        if (preferences.getBoolean(currentProfileId + "_avoid_lupin", false)) avoidIngredients.add("루핀");
    }
    private void setupUi() {
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) getSupportActionBar().hide();
        LocaleHelper.loadLocale(this);
        setContentView(R.layout.activity_main);
        // Initialize other UI components here
    }

    private void setupListeners() {
        Button uploadPictureButton = findViewById(R.id.button_upload_picture);
        uploadPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        // Initialize the settings button for profile management (using fragment navigation)
        ImageButton settingsButton = findViewById(R.id.button_settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(
                                R.anim.slide_in_right,  // Enter animation
                                R.anim.slide_out_right,  // Exit animation
                                R.anim.slide_in_right,  // Pop enter animation (optional)
                                R.anim.slide_out_right   // Pop exit animation (optional)
                        )
                        .replace(R.id.fragment_container, new SettingsFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        // Initialize the food suggestion list button
        Button foodSuggestionsButton = findViewById(R.id.button_food_suggestions);
        foodSuggestionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to another activity or XML containing the food suggestions
                Intent intent = new Intent(MainActivity.this, FoodSuggestionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void openProfileManagement() {
        Intent intent = new Intent(this, PersonalSettingsActivity.class);
        intent.putExtra("add","True");
        startActivityForResult(intent, REQUEST_CODE_ADD_PROFILE);  // Define REQUEST_CODE_ADD_PROFILE as a constant
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("%%%%","wowowowow"+REQUEST_CODE_ADD_PROFILE+"||"+resultCode+"||"+RESULT_OK);
        if (requestCode == REQUEST_CODE_ADD_PROFILE && resultCode == RESULT_OK) {
            // Reload profile preferences if a new profile has been added
            Log.d("%%%%","wowowowow");
            loadProfilePreferences();
            setupUi();
            setupListeners();
        }
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            File imageFile = uriToFile(imageUri);
            if (imageFile != null && imageFile.exists()) {
                performOcrRequest(imageFile, new OcrResultCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(MainActivity.this, "Success: " + result, Toast.LENGTH_LONG).show();
                        //Log.d("OCR_SUCCESS", "OCR Result~: " + result);
                        List<String> the_result = determineLegality(result);
                        if (the_result.isEmpty())
                            openEdibleActivity();
                        else
                            openNotEdibleActivity(the_result);
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(MainActivity.this, "Error: " + error, Toast.LENGTH_LONG).show();
                    }
                });

            } else {
                Toast.makeText(this, "Error accessing image file.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private List<String> determineLegality(String result) {
        List<String> inferTexts = OcrResultParser.extractInferText(result);
        StringBuilder the_result = new StringBuilder();
        for (String text : inferTexts) {
            the_result.append(text);
        }
        // Log.d("OcrResultParser", "Processing text: " + the_result.toString());
        List<String> contained_illegal_ingredient = new ArrayList<>();

        for (String ingredient : avoidIngredients) {
            if (the_result.toString().contains(ingredient)) {
                contained_illegal_ingredient.add(ingredient);
            }
        }
        return contained_illegal_ingredient;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadProfilePreferences();
        loadUserPreferences();
        if (currentProfileId.isEmpty()) {
            openProfileManagement();
        }
        setupUi();
        setupListeners();
    }

    private File uriToFile(Uri imageUri) {
        File tempFile = null;
        try {
            // Create a temporary file
            tempFile = File.createTempFile("upload", ".jpg", getExternalCacheDir());
            tempFile.deleteOnExit();
            try (InputStream is = getContentResolver().openInputStream(imageUri);
                 OutputStream os = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "Error writing temp file", e);
        }
        return tempFile;
    }

    // Open the gallery to select a photo
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE);
    }

    // Function to call the OCR API and process the result
    private void performOcrRequest(File imageFile, OcrResultCallback callback) {
        Retrofit retrofit = RetrofitClient.getClient();
        ApiService apiService = retrofit.create(ApiService.class);

        // Create RequestBody for image file
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);

        // Create JSON message
        JSONObject json = new JSONObject();
        try {
            json.put("version", "V2");
            json.put("requestId", UUID.randomUUID().toString());
            json.put("timestamp", System.currentTimeMillis());
            JSONObject image = new JSONObject();
            image.put("format", "jpg");
            image.put("name", "demo");
            JSONArray images = new JSONArray();
            images.put(image);
            json.put("images", images);
        } catch (Exception e) {
            callback.onError(e.getMessage());
            return;
        }

        RequestBody message = RequestBody.create(MediaType.parse("multipart/form-data"), json.toString());

        // Call API
        Call<ResponseBody> call = apiService.performOcrRequest(secretKey, body, message);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String responseString = response.body().string();
                        callback.onSuccess(responseString);
                    } catch (IOException e) {
                        callback.onError(e.getMessage());
                    }
                } else {
                    callback.onError("API call failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
    private void openEdibleActivity() {
        Intent intent = new Intent(this, EdibleActivity.class);
        intent.putExtra("stringA", "The ingredient satisfy your dietary preference!");
        startActivity(intent);
    }
    private void openNotEdibleActivity(List<String> the_result) {
        Intent intent = new Intent(this, EdibleActivity.class);
        intent.putExtra("stringA", "The ingredient does not satisfy your dietary preference!");
        intent.putExtra("stringB", "Because the food contains : ");
        for (String ingredient : the_result) {
            intent.putExtra("stringC", " " + ingredient);
        }
        startActivity(intent);
    }
}
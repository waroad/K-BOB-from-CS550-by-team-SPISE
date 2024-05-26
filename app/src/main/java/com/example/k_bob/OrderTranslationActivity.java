package com.example.k_bob;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderTranslationActivity extends AppCompatActivity {
    private static final String PREFS_NAME = "profiles_preferences";
    private static final String ACTIVE_PROFILE = "active_profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_translation);

        ImageView backIcon = findViewById(R.id.back_icon);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous screen
            }
        });

        String profileId = getIntent().getStringExtra("activeProfileId");

        if (profileId != null) {
            SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            String veganType = getVeganType(preferences, profileId);
            List<String> checkedRestrictions = getCheckedRestrictions(preferences, profileId);
            List<String> uncheckedRestrictions = getUncheckedRestrictions(preferences, profileId);

            // Translate restrictions to Korean
            Map<String, String> koreanTranslations = getKoreanTranslations();
            Map<String, String> koreanPronunciations = getKoreanPronunciations();
            List<String> checkedRestrictionsKorean = new ArrayList<>();
            List<String> uncheckedRestrictionsKorean = new ArrayList<>();
            List<String> checkedRestrictionsPronunciation = new ArrayList<>();
            List<String> uncheckedRestrictionsPronunciation = new ArrayList<>();

            for (String restriction : checkedRestrictions) {
                checkedRestrictionsKorean.add(koreanTranslations.get(restriction));
                checkedRestrictionsPronunciation.add(koreanPronunciations.get(restriction));
            }

            for (String restriction : uncheckedRestrictions) {
                uncheckedRestrictionsKorean.add(koreanTranslations.get(restriction));
                uncheckedRestrictionsPronunciation.add(koreanPronunciations.get(restriction));
            }

            String translationMessage = generateTranslationMessage(veganType, checkedRestrictions, uncheckedRestrictions);
            String translationMessageKorean = generateTranslationMessageKorean(checkedRestrictionsKorean, uncheckedRestrictionsKorean);
            String translationMessagePronunciation = generateTranslationMessagePronunciation(checkedRestrictionsPronunciation, uncheckedRestrictionsPronunciation);

            TextView translationTextView = findViewById(R.id.translation_text);
            translationTextView.setText(translationMessage);

            TextView translationKoreanTextView = findViewById(R.id.translation_text_korean);
            translationKoreanTextView.setText(translationMessageKorean);

            TextView translationPronunciationTextView = findViewById(R.id.translation_text_pronunciation);
            translationPronunciationTextView.setText(translationMessagePronunciation);
        }
    }

    private String getVeganType(SharedPreferences preferences, String profileId) {
        if (preferences.getBoolean(profileId + "_is_vegan", false)) return "Vegan";
        if (preferences.getBoolean(profileId + "_is_lacto", false)) return "Lacto";
        if (preferences.getBoolean(profileId + "_is_ovo", false)) return "Ovo";
        if (preferences.getBoolean(profileId + "_is_lacto-ovo", false)) return "Lacto-ovo";
        if (preferences.getBoolean(profileId + "_is_pollotarian", false)) return "Pollotarian";
        if (preferences.getBoolean(profileId + "_is_pescatarian", false)) return "Pescatarian";
        return "Unknown";
    }

    private List<String> getCheckedRestrictions(SharedPreferences preferences, String profileId) {
        List<String> checkedRestrictions = new ArrayList<>();
        Map<String, Boolean> restrictionMap = getRestrictionMap(preferences, profileId);

        for (Map.Entry<String, Boolean> entry : restrictionMap.entrySet()) {
            if (entry.getValue()) {
                checkedRestrictions.add(entry.getKey());
            }
        }

        return checkedRestrictions;
    }

    private List<String> getUncheckedRestrictions(SharedPreferences preferences, String profileId) {
        List<String> uncheckedRestrictions = new ArrayList<>();
        Map<String, Boolean> restrictionMap = getRestrictionMap(preferences, profileId);

        for (Map.Entry<String, Boolean> entry : restrictionMap.entrySet()) {
            if (!entry.getValue()) {
                uncheckedRestrictions.add(entry.getKey());
            }
        }

        return uncheckedRestrictions;
    }

    private Map<String, Boolean> getRestrictionMap(SharedPreferences preferences, String profileId) {
        Map<String, Boolean> restrictionMap = new HashMap<>();
        restrictionMap.put("avoid_beef", preferences.getBoolean(profileId + "_avoid_beef", false));
        restrictionMap.put("avoid_pork", preferences.getBoolean(profileId + "_avoid_pork", false));
        restrictionMap.put("avoid_shellfish", preferences.getBoolean(profileId + "_avoid_shellfish", false));
        restrictionMap.put("avoid_fish", preferences.getBoolean(profileId + "_avoid_fish", false));
        restrictionMap.put("avoid_peanut", preferences.getBoolean(profileId + "_avoid_peanut", false));
        restrictionMap.put("avoid_chicken", preferences.getBoolean(profileId + "_avoid_chicken", false));
        restrictionMap.put("avoid_lamb", preferences.getBoolean(profileId + "_avoid_lamb", false));
        restrictionMap.put("avoid_egg", preferences.getBoolean(profileId + "_avoid_egg", false));
        restrictionMap.put("avoid_dairy", preferences.getBoolean(profileId + "_avoid_dairy", false));
        restrictionMap.put("avoid_flour", preferences.getBoolean(profileId + "_avoid_flour", false));
        restrictionMap.put("avoid_tree_nuts", preferences.getBoolean(profileId + "_avoid_tree_nuts", false));
        restrictionMap.put("avoid_soy", preferences.getBoolean(profileId + "_avoid_soy", false));
        restrictionMap.put("avoid_sesame", preferences.getBoolean(profileId + "_avoid_sesame", false));
        restrictionMap.put("avoid_wheat", preferences.getBoolean(profileId + "_avoid_wheat", false));
        restrictionMap.put("avoid_corn", preferences.getBoolean(profileId + "_avoid_corn", false));
        restrictionMap.put("avoid_gluten", preferences.getBoolean(profileId + "_avoid_gluten", false));
        restrictionMap.put("avoid_mustard", preferences.getBoolean(profileId + "_avoid_mustard", false));
        restrictionMap.put("avoid_celery", preferences.getBoolean(profileId + "_avoid_celery", false));
        restrictionMap.put("avoid_sulfites", preferences.getBoolean(profileId + "_avoid_sulfites", false));
        restrictionMap.put("avoid_lupin", preferences.getBoolean(profileId + "_avoid_lupin", false));

        return restrictionMap;
    }

    private Map<String, String> getKoreanTranslations() {
        Map<String, String> translations = new HashMap<>();
        translations.put("avoid_beef", "쇠고기");
        translations.put("avoid_pork", "돼지고기");
        translations.put("avoid_shellfish", "조개류");
        translations.put("avoid_fish", "생선");
        translations.put("avoid_peanut", "땅콩");
        translations.put("avoid_chicken", "닭고기");
        translations.put("avoid_lamb", "양고기");
        translations.put("avoid_egg", "계란");
        translations.put("avoid_dairy", "유제품");
        translations.put("avoid_flour", "밀가루");
        translations.put("avoid_tree_nuts", "견과류");
        translations.put("avoid_soy", "콩");
        translations.put("avoid_sesame", "참깨");
        translations.put("avoid_wheat", "밀");
        translations.put("avoid_corn", "옥수수");
        translations.put("avoid_gluten", "글루텐");
        translations.put("avoid_mustard", "겨자");
        translations.put("avoid_celery", "샐러리");
        translations.put("avoid_sulfites", "아황산염");
        translations.put("avoid_lupin", "루핀");
        return translations;
    }

    private Map<String, String> getKoreanPronunciations() {
        Map<String, String> pronunciations = new HashMap<>();
        pronunciations.put("avoid_beef", "sogogi");
        pronunciations.put("avoid_pork", "dwaejigogi");
        pronunciations.put("avoid_shellfish", "jogaeryu");
        pronunciations.put("avoid_fish", "saengseon");
        pronunciations.put("avoid_peanut", "ttangkong");
        pronunciations.put("avoid_chicken", "dalgogi");
        pronunciations.put("avoid_lamb", "yanggogi");
        pronunciations.put("avoid_egg", "gyeran");
        pronunciations.put("avoid_dairy", "yooje");
        pronunciations.put("avoid_flour", "milgaru");
        pronunciations.put("avoid_tree_nuts", "gyeongwalyu");
        pronunciations.put("avoid_soy", "kong");
        pronunciations.put("avoid_sesame", "chamkae");
        pronunciations.put("avoid_wheat", "mil");
        pronunciations.put("avoid_corn", "oksusu");
        pronunciations.put("avoid_gluten", "geulluten");
        pronunciations.put("avoid_mustard", "gyeoja");
        pronunciations.put("avoid_celery", "saelleri");
        pronunciations.put("avoid_sulfites", "ahwangsanyeom");
        pronunciations.put("avoid_lupin", "lupin");
        return pronunciations;
    }

    private String generateTranslationMessage(String veganType, List<String> checkedRestrictions, List<String> uncheckedRestrictions) {
        return "Based on your dietary preferences (" + veganType + "), we recommend you say \"Please eliminate " +
                String.join(", ", checkedRestrictions) + " if they are included in the ingredients. However, " +
                String.join(", ", uncheckedRestrictions) + " are acceptable\"";
    }

    private String generateTranslationMessageKorean(List<String> checkedRestrictionsKorean, List<String> uncheckedRestrictionsKorean) {
        return "In Korean, \"만약 " + String.join(", ", checkedRestrictionsKorean) + "이 재료에 포함되어 있다면 제거해주세요. 단, " +
                String.join(", ", uncheckedRestrictionsKorean) + "은 허용됩니다.\"";
    }

    private String generateTranslationMessagePronunciation(List<String> checkedRestrictionsPronunciation, List<String> uncheckedRestrictionsPronunciation) {
        return "In speaking, \"Manyak " + String.join(", ", checkedRestrictionsPronunciation) +
                " Jaeryoe Pohamdoeeo Itda-myeon, Geugeotdeureul Jegeohaseyo. " +
                String.join(", ", uncheckedRestrictionsPronunciation) + " Heoyongdoemnida\"";
    }
}

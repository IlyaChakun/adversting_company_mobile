package by.chekun.presentation.activities.settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import by.chekun.R;


public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        //SharedPreferences languagePreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        //String language = languagePreferences.getString("Language", "2");

        //     ListPreference languagePreferences = (ListPreference) findPreference("language_list");
        //CharSequence[] entries = {"English", "Spanish"};
        //CharSequence[] entryValues = {"1", "2"};
        //languagePreferences.setEntries(entries);
        //languagePreferences.setDefaultValue("1");
        //languagePreferences.setEntryValues(entryValues);
/*
        switch (languagePreferences) {
            case entryValues(1):
                Locale locale = new Locale("en");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                Toast.makeText(this, "Locale in English !", Toast.LENGTH_LONG).show();
                break;

            case R.id.es:
                Locale locale2 = new Locale("es");
                Locale.setDefault(locale2);
                Configuration config2 = new Configuration();
                config2.locale = locale2;
                getBaseContext().getResources().updateConfiguration(config2, getBaseContext().getResources().getDisplayMetrics());
                Toast.makeText(this, "Locale in Spain!", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void setLocale(String language) {

        myLocale = new Locale(language);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, Settings.class);
        startActivity(refresh);
    }
    */
    }
}
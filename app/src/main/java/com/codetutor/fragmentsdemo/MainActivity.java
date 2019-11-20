package com.codetutor.fragmentsdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity implements FragmentActionListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        addCountriesFragment();
    }

    private void addCountriesFragment(){
        fragmentTransaction=fragmentManager.beginTransaction();

        CountriesFragment countryListFragment=new CountriesFragment();
        countryListFragment.setFragmentActionListener(this);

        fragmentTransaction.add(R.id.fragmentContainer,countryListFragment);
        fragmentTransaction.commit();
    }

    private void addCountryDescriptionFragment(String countryName){
        fragmentTransaction=fragmentManager.beginTransaction();

        CountryDescriptionFragment countryDescriptionFragment=new CountryDescriptionFragment();

        Bundle bundle=new Bundle();
        bundle.putString(FragmentActionListener.KEY_SELECTED_COUNTRY,countryName);
        countryDescriptionFragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragmentContainer,countryDescriptionFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onCountrySelected(String country) {
        addCountryDescriptionFragment(country);
    }
}

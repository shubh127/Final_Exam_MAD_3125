package com.example.finalexam;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        PlaceAdapter.OnPlaceSelected {
    private final List<CountryInfo> countries = new ArrayList<>();
    private final List<String> countriesName = new ArrayList<>();
    private List<Place> places = new ArrayList<>();
    private Spinner spCountries;
    private TextView tvCapital;
    private ImageView ivFlag;
    private RecyclerView rvPlaces;
    private Group grp;
    private Button btnAdd;
    private TextView tvTotalCost;
    private EditText etVisitorCount;
    private double totalCost = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.select_destination));
        }

        populateData();
        initViews();
        configViews();
        applyListeners();
    }

    private void populateData() {
        List<Place> canadaPlaces = new ArrayList<>();
        canadaPlaces.add(new Place("Niagara Falls", 100, R.drawable.nf));
        canadaPlaces.add(new Place("CN Tower", 30, R.drawable.cn));
        canadaPlaces.add(new Place("The Butchart Gardens", 30,
                R.drawable.bg));
        canadaPlaces.add(new Place("Notre-Dame Basilica", 50,
                R.drawable.ndb));
        countries.add(new CountryInfo("Canada", "Ottawa", canadaPlaces,
                R.drawable.canada_flag));

        List<Place> usaPlaces = new ArrayList<>();
        usaPlaces.add(new Place("The Statue of Liberty", 90, R.drawable.sl));
        usaPlaces.add(new Place("The White House", 60, R.drawable.wh));
        usaPlaces.add(new Place("Times Square", 75,
                R.drawable.ts));
        countries.add(new CountryInfo("USA", "Washington", usaPlaces,
                R.drawable.usa_flag));

        List<Place> englandPlaces = new ArrayList<>();
        englandPlaces.add(new Place("Big Ben", 30, R.drawable.bb));
        englandPlaces.add(new Place("Westminster Abbey", 25, R.drawable.wa));
        englandPlaces.add(new Place("Hyde Park", 15,
                R.drawable.hp));
        countries.add(new CountryInfo("England", "London", englandPlaces,
                R.drawable.england_flag));

        for (CountryInfo country : countries) {
            countriesName.add(country.getName());
        }
    }

    private void initViews() {
        spCountries = findViewById(R.id.sp_countries);
        tvCapital = findViewById(R.id.tv_capital);
        ivFlag = findViewById(R.id.iv_flag);
        rvPlaces = findViewById(R.id.rv_places);
        grp = findViewById(R.id.grp);
        btnAdd = findViewById(R.id.btn_add);
        tvTotalCost = findViewById(R.id.tv_total_cost);
        etVisitorCount = findViewById(R.id.et_visitor_count);
    }

    private void configViews() {
        ArrayAdapter<String> aa = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, countriesName);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCountries.setAdapter(aa);
        setCountryData(0);
        rvPlaces.setLayoutManager(new LinearLayoutManager(this));
        tvTotalCost.setText(getString(R.string.default_cost));
    }

    private void applyListeners() {
        spCountries.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        for (Place place : places) {
            place.setSelected(false);
        }
        grp.setVisibility(View.GONE);
        setCountryData(i);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //do nothing
    }


    private void setCountryData(int index) {
        tvCapital.setText(String.format(getString(R.string.capital),
                countries.get(index).getCapitalName()));
        ivFlag.setImageDrawable(ContextCompat.getDrawable(this,
                countries.get(index).getFlagImageId()));
        places = countries.get(index).getPlaces();
        rvPlaces.setAdapter(new PlaceAdapter(places, this));
    }

    @Override
    public void onPlaceSelected(Place place) {
        for (Place p : places) {
            p.setSelected(p.getName().equalsIgnoreCase(place.getName()));
        }
        rvPlaces.setAdapter(new PlaceAdapter(places, this));
        grp.setVisibility(View.VISIBLE);

        btnAdd.setOnClickListener(view -> onAddClick(place));
    }

    @SuppressLint("StringFormatMatches")
    private void onAddClick(Place place) {
        if (TextUtils.isEmpty(etVisitorCount.getText()) ||
                Integer.parseInt(etVisitorCount.getText().toString().trim()) < 1) {
            Toast.makeText(this, getString(R.string.invalid_count), Toast.LENGTH_SHORT)
                    .show();
        } else {
            double costPerPerson = place.getPriceOfVisit();
            if (Integer.parseInt(etVisitorCount.getText().toString().trim()) > 15) {
                costPerPerson = costPerPerson - (costPerPerson * 0.05);
            }
            totalCost = totalCost + costPerPerson * Integer.parseInt(etVisitorCount.getText().toString().trim());
            tvTotalCost.setText(String.format(getString(R.string.total_cost), totalCost));
        }
    }
}
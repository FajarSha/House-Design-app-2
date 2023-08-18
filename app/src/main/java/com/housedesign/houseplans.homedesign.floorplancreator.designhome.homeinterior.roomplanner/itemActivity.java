package com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner.Adapter.HouseRecylceAdapter;
import com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner.Modal.HouseModal;
import com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner.Modal.ResourceData;

import java.util.ArrayList;

public class itemActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    HouseRecylceAdapter adapter;
    ArrayList<HouseModal> arrayList = new ArrayList<>();
    int casevalue = 0;
    TextView mainTitle;
    ResourceData resourceData;
    ImageView backArrowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        initView();

        resourceData=new ResourceData(this);
        casevalue = getIntent().getIntExtra("case", 0);
        setmainText(casevalue);
        arrayList=resourceData.getResource(casevalue);
     /*   if (casevalue == 1) {

            mainTitle.setText(getString(R.string.twod));
        } else if (casevalue == 2) {
            setSecRecylceData();
            mainTitle.setText(getString(R.string.threed));
        } else if (casevalue == 3) {
            setthirdRecylceData();
        }*/

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new HouseRecylceAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);
        backArrowBtn.setOnClickListener(view -> {
            finish();
        });
    }

    private void initView() {
        recyclerView = findViewById(R.id.recylceview);
        mainTitle = findViewById(R.id.title);
        backArrowBtn = findViewById(R.id.sort_btn);
    }

    public ArrayList<HouseModal> setmainText(int value) {
        if (value == 1) {
          mainTitle.setText(getString(R.string.twod));
        } else if (value == 2) {
            mainTitle.setText(getString(R.string.threed));
        } else if (value == 3) {
            mainTitle.setText(getString(R.string.front));
        } else if (value == 4) {
            mainTitle.setText(getString(R.string.bathroom));

        }else if (value == 5) {
            mainTitle.setText(getString(R.string.kitchen));
        }else if (value == 6) {
            mainTitle.setText(getString(R.string.bathroom));
        }else if (value == 7) {
            mainTitle.setText(getString(R.string.lounge));
        }
        return arrayList;

    }
    /*private void setRecylceData() {
        arrayList.add(new HouseModal(getString(R.string.twod), null, R.drawable.item_img));
        arrayList.add(new HouseModal(getString(R.string.twod), null, R.drawable.item_img));
        arrayList.add(new HouseModal(getString(R.string.twod), null, R.drawable.item_img));
        arrayList.add(new HouseModal(getString(R.string.twod), null, R.drawable.item_img));
        arrayList.add(new HouseModal(getString(R.string.twod), null, R.drawable.item_img));
        arrayList.add(new HouseModal(getString(R.string.twod), null, R.drawable.item_img));
        arrayList.add(new HouseModal(getString(R.string.twod), null, R.drawable.item_img));
    }

    private void setSecRecylceData() {
        arrayList.add(new HouseModal(getString(R.string.threed), getString(R.string.front), R.drawable.threed_img));
        arrayList.add(new HouseModal(getString(R.string.threed), getString(R.string.bedroom), R.drawable.threed_img));
        arrayList.add(new HouseModal(getString(R.string.threed), getString(R.string.kitchen), R.drawable.threed_img));
        arrayList.add(new HouseModal(getString(R.string.threed), getString(R.string.bathroom), R.drawable.threed_img));
        arrayList.add(new HouseModal(getString(R.string.threed), getString(R.string.lounge), R.drawable.threed_img));

    }*/


}
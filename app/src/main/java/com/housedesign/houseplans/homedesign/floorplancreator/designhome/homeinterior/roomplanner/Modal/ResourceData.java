package com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner.Modal;

import android.content.Context;

import com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner.R;

import java.util.ArrayList;

public class ResourceData {
    Context context;

    public ResourceData(Context context) {
        this.context = context;
    }

    ArrayList<HouseModal> arrayList;

    public ArrayList<HouseModal> getResource(int value) {
        if (value == 1) {
            twoDDatatValue();
        } else if (value == 2) {
            threeDDatatValue();
        } else if (value == 3) {
            frontDatatValue();
        } else if (value == 4) {
            bedroomDatatValue();
          
        }else if (value == 5) {
            kitchenDatatValue();
        }else if (value == 6) {
            bathroomDatatValue();
        }else if (value == 7) {
            loungeDatatValue();
        }
        return arrayList;

    }

    private void loungeDatatValue() {
        arrayList=new ArrayList<>();
        arrayList.add(new HouseModal(context.getString(R.string.lounge), null, R.drawable.temp_3d_l1));
        arrayList.add(new HouseModal(context.getString(R.string.lounge), null, R.drawable.temp_3d_l2));
        arrayList.add(new HouseModal(context.getString(R.string.lounge), null, R.drawable.temp_3d_l3));
        arrayList.add(new HouseModal(context.getString(R.string.lounge), null, R.drawable.temp_3d_l4));
        arrayList.add(new HouseModal(context.getString(R.string.lounge), null, R.drawable.temp_3d_l5));
        arrayList.add(new HouseModal(context.getString(R.string.lounge), null, R.drawable.temp_3d_l6));
        arrayList.add(new HouseModal(context.getString(R.string.lounge), null, R.drawable.temp_3d_l7));
        arrayList.add(new HouseModal(context.getString(R.string.lounge), null, R.drawable.temp_3d_l8));
        arrayList.add(new HouseModal(context.getString(R.string.lounge), null, R.drawable.temp_3d_l9));
        arrayList.add(new HouseModal(context.getString(R.string.lounge), null, R.drawable.temp_3d_l10));
    }

    private void bathroomDatatValue() {
        arrayList=new ArrayList<>();
        arrayList.add(new HouseModal(context.getString(R.string.bathroom), null, R.drawable.temp_3d_bb1));
        arrayList.add(new HouseModal(context.getString(R.string.bathroom), null, R.drawable.temp_3d_bb2));
        arrayList.add(new HouseModal(context.getString(R.string.bathroom), null, R.drawable.temp_3d_bb3));
        arrayList.add(new HouseModal(context.getString(R.string.bathroom), null, R.drawable.temp_3d_bb4));
        arrayList.add(new HouseModal(context.getString(R.string.bathroom), null, R.drawable.temp_3d_bb5));
        arrayList.add(new HouseModal(context.getString(R.string.bathroom), null, R.drawable.temp_3d_bb6));
        arrayList.add(new HouseModal(context.getString(R.string.bathroom), null, R.drawable.temp_3d_bb7));
        arrayList.add(new HouseModal(context.getString(R.string.bathroom), null, R.drawable.temp_3d_bb8));
        arrayList.add(new HouseModal(context.getString(R.string.bathroom), null, R.drawable.temp_3d_bb9));
        arrayList.add(new HouseModal(context.getString(R.string.bathroom), null, R.drawable.temp_3d_bb10));
    }

    private void kitchenDatatValue() {
        arrayList=new ArrayList<>();
        arrayList.add(new HouseModal(context.getString(R.string.kitchen), null, R.drawable.temp_3d_k1));
        arrayList.add(new HouseModal(context.getString(R.string.kitchen), null, R.drawable.temp_3d_k2));
        arrayList.add(new HouseModal(context.getString(R.string.kitchen), null, R.drawable.temp_3d_k3));
        arrayList.add(new HouseModal(context.getString(R.string.kitchen), null, R.drawable.temp_3d_k4));
        arrayList.add(new HouseModal(context.getString(R.string.kitchen), null, R.drawable.temp_3d_k5));
        arrayList.add(new HouseModal(context.getString(R.string.kitchen), null, R.drawable.temp_3d_k6));
        arrayList.add(new HouseModal(context.getString(R.string.kitchen), null, R.drawable.temp_3d_k7));
        arrayList.add(new HouseModal(context.getString(R.string.kitchen), null, R.drawable.temp_3d_k8));
        arrayList.add(new HouseModal(context.getString(R.string.kitchen), null, R.drawable.temp_3d_k9));
        arrayList.add(new HouseModal(context.getString(R.string.kitchen), null, R.drawable.temp_3d_k10));
    }

    private void bedroomDatatValue() {
        arrayList=new ArrayList<>();
        arrayList.add(new HouseModal(context.getString(R.string.bedroom), null, R.drawable.temp_3d_b1));
        arrayList.add(new HouseModal(context.getString(R.string.bedroom), null, R.drawable.temp_3d_b2));
        arrayList.add(new HouseModal(context.getString(R.string.bedroom), null, R.drawable.temp_3d_b3));
        arrayList.add(new HouseModal(context.getString(R.string.bedroom), null, R.drawable.temp_3d_b4));
        arrayList.add(new HouseModal(context.getString(R.string.bedroom), null, R.drawable.temp_3d_b5));
        arrayList.add(new HouseModal(context.getString(R.string.bedroom), null, R.drawable.temp_3d_b6));
        arrayList.add(new HouseModal(context.getString(R.string.bedroom), null, R.drawable.temp_3d_b7));
        arrayList.add(new HouseModal(context.getString(R.string.bedroom), null, R.drawable.temp_3d_b8));
        arrayList.add(new HouseModal(context.getString(R.string.bedroom), null, R.drawable.temp_3d_b9));
        arrayList.add(new HouseModal(context.getString(R.string.bedroom), null, R.drawable.temp_3d_b10));
    }

    private void frontDatatValue() {
        arrayList=new ArrayList<>();
        arrayList.add(new HouseModal(context.getString(R.string.front), null, R.drawable.temp_3d_f1));
        arrayList.add(new HouseModal(context.getString(R.string.front), null, R.drawable.temp_3d_f2));
        arrayList.add(new HouseModal(context.getString(R.string.front), null, R.drawable.temp_3d_f3));
        arrayList.add(new HouseModal(context.getString(R.string.front),null, R.drawable.temp_3d_f4));
        arrayList.add(new HouseModal(context.getString(R.string.front), null, R.drawable.temp_3d_f5));
        arrayList.add(new HouseModal(context.getString(R.string.front), null, R.drawable.temp_3d_f6));
        arrayList.add(new HouseModal(context.getString(R.string.front), null, R.drawable.temp_3d_f7));
        arrayList.add(new HouseModal(context.getString(R.string.front), null, R.drawable.temp_3d_f8));
        arrayList.add(new HouseModal(context.getString(R.string.front), null, R.drawable.temp_3d_f9));
        arrayList.add(new HouseModal(context.getString(R.string.front), null, R.drawable.temp_3d_f10));
    }

    private void threeDDatatValue() {
        arrayList=new ArrayList<>();
        arrayList.add(new HouseModal(context.getString(R.string.threed), context.getString(R.string.front), R.drawable.temp_3d_f1));
        arrayList.add(new HouseModal(context.getString(R.string.threed), context.getString(R.string.bedroom), R.drawable.temp_3d_b1));
        arrayList.add(new HouseModal(context.getString(R.string.threed), context.getString(R.string.kitchen), R.drawable.temp_3d_k10));
        arrayList.add(new HouseModal(context.getString(R.string.threed), context.getString(R.string.bathroom), R.drawable.temp_3d_bb10));
        arrayList.add(new HouseModal(context.getString(R.string.threed), context.getString(R.string.lounge), R.drawable.temp_3d_l4));
    }

    private void twoDDatatValue() {
        arrayList=new ArrayList<>();
        arrayList.add(new HouseModal(context.getString(R.string.twod), null, R.drawable.temp_2d_1));
        arrayList.add(new HouseModal(context.getString(R.string.twod), null, R.drawable.temp_2d_2));
        arrayList.add(new HouseModal(context.getString(R.string.twod), null, R.drawable.temp_2d_3));
        arrayList.add(new HouseModal(context.getString(R.string.twod), null, R.drawable.temp_2d_4));
        arrayList.add(new HouseModal(context.getString(R.string.twod), null, R.drawable.temp_2d_5));
        arrayList.add(new HouseModal(context.getString(R.string.twod), null, R.drawable.temp_2d_6));
        arrayList.add(new HouseModal(context.getString(R.string.twod), null, R.drawable.temp_2d_7));
        arrayList.add(new HouseModal(context.getString(R.string.twod), null, R.drawable.temp_2d_8));
        arrayList.add(new HouseModal(context.getString(R.string.twod), null, R.drawable.temp_2d_9));
        arrayList.add(new HouseModal(context.getString(R.string.twod), null, R.drawable.temp_2d_10));

    }
}

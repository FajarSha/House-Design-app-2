package com.housedesign.houseplans.homedesign.floorplancreator.designhome.homeinterior.roomplanner;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;

public class DragableActivity extends AppCompatActivity {
    FrameLayout dropLayout;
    SeekBar seekBarSize, seekBarAngle;

    float offsetX, offsetY;

    ImageView PreviousBtn,  selectedImageView;
    ImageView removeBtn;
    ImageView captureBtn, nextBtn, prevBtn;
    int currentPosition = 0;
    ImageView firstImg, secondImg, thirdImg, fourthImg;
    TextView firstTxt, secondTxt, thirdTxt, fourthTxt;
    private float startX;
    private float startY;
    private boolean isDragging = false;
    Integer firstimgValue, seconDImgValue, thirdimgValue, fourthImgValue;
    FrameLayout selectedFrameLayout;
    float defX, defY;
    int selected_IMG = 0;
    LinearLayout shapeSeekLayout, sizeSeekBarLayout;
    SeekBar shapesSeekBarsize;

    int adcountvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragable);
        initView();
        setFirstData();
        adcountvalue=getIntent().getIntExtra("adcount",0);
        dropLayout.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:

                        return true;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        v.setBackgroundColor(getResources().getColor(R.color.white_gary));
                        if (selectedImageView != null) {
                            selectedImageView = null;
                        }
                        return true;
                    case DragEvent.ACTION_DRAG_EXITED:
                        v.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        return true;
                    case DragEvent.ACTION_DROP:
                        if (selectedImageView != null) {
                            // Handle drop action here
                            // ...
                        } else {
                            // If there was no selected image, show the new dragged image
                            Log.d("TAG", "onDrag: " + event.getLocalState());
                            showdraggableImage((ImageView) event.getLocalState(), event.getX(), event.getY());
                        }
                        return true;
                    case DragEvent.ACTION_DRAG_ENDED:
                        v.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        return true;
                }
                return false;
            }
        });
        dropLayout.setOnClickListener(view -> {
            if (selectedImageView != null) {
                selectedImageView = null;
            }
            if(selectedFrameLayout!=null){
                selectedFrameLayout.setBackgroundResource(0);
                selectedFrameLayout=null;
            }
        });

        seekBarSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (selectedImageView != null) {
                    int size = 100 + progress;
                    // Adjust the scaling factor as needed
                    ViewGroup.LayoutParams layoutParams = selectedImageView.getLayoutParams();
                    layoutParams.width = size;
                    layoutParams.height = size;
                    selectedImageView.setLayoutParams(layoutParams);
                    ViewGroup.LayoutParams layoutParams2 = selectedFrameLayout.getLayoutParams();
                    layoutParams2.width = size + 30;
                    layoutParams2.height = size + 30;
                    selectedFrameLayout.setLayoutParams(layoutParams2);

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        shapesSeekBarsize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (selectedImageView != null) {
                    int size = 150 + progress;

                    // Adjust the scaling factor as needed
                    ViewGroup.LayoutParams layoutParams = selectedImageView.getLayoutParams();
                    layoutParams.width = size;
                    layoutParams.height = size;
                    selectedImageView.setLayoutParams(layoutParams);
                    ViewGroup.LayoutParams layoutParams2 = selectedFrameLayout.getLayoutParams();
                    layoutParams2.width = size + 50;
                    layoutParams2.height = size + 50;
                    selectedFrameLayout.setPadding(20, 20, 20, 20);
                    selectedFrameLayout.setLayoutParams(layoutParams2);
                    Log.d(TAG, "onProgressChanged: " + size + "//////" + progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        seekBarAngle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int size = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (selectedImageView != null && selectedFrameLayout != null) {
                    float angle = progress; // Adjust the angle factor as needed
                    selectedFrameLayout.setRotation(angle + 0.50f);
//                    selectedImageView.setRotation(angle);

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(selectedFrameLayout!=null){
                    selectedFrameLayout.setBackgroundResource(R.drawable.border);
                }

            }
        });
        PreviousBtn.setOnClickListener(view -> {
//            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        });
        removeBtn.setOnClickListener(view -> {
            if (selectedFrameLayout != null) {
                dropLayout.removeView(selectedFrameLayout);
                selectedImageView = null;
                selectedFrameLayout = null;

            }

            //   this.finish();
        });
        captureBtn.setOnClickListener(view -> {
            captureAndSaveImage(dropLayout);
            //   this.finish();
        });

        nextBtn.setOnClickListener(view -> {
            prevBtn.setAlpha(1.0f);
            prevBtn.setEnabled(true);
            if (currentPosition == 1) {
                setSecondtData();
                Log.d(TAG, "onCreate: second is called in next");
            } else if (currentPosition == 2) {
                setThirdData();
                nextBtn.setEnabled(false);
                nextBtn.setAlpha(0.3f);
                Log.d(TAG, "onCreate: third is called in next");
            }

        });

        // Handle the "Previous" button click
        prevBtn.setOnClickListener(view -> {
            if (currentPosition == 3) {
                setSecondtData();
                nextBtn.setEnabled(true);
                nextBtn.setAlpha(1.0f);
                Log.d(TAG, "onCreate: second is called in previous");
            } else if (currentPosition == 2) {
                setFirstData();
                prevBtn.setEnabled(false);
                prevBtn.setAlpha(0.3f);
                Log.d(TAG, "onCreate: first is called in previous");
            }
        });

        shapeSeekLayout.setVisibility(View.GONE);
    }

    private void initView() {
//        recyclerView = findViewById(R.id.recylce_view);
        dropLayout = findViewById(R.id.dropLayout);
        seekBarSize = findViewById(R.id.seekBar);
        seekBarAngle = findViewById(R.id.seekBar2);
        //claerBtn = findViewById(R.id.clearBtn);
        PreviousBtn = findViewById(R.id.previous_btn);
        captureBtn = findViewById(R.id.clearBtn);
        removeBtn = findViewById(R.id.removeBtn);
        nextBtn = findViewById(R.id.nextBtn);
        prevBtn = findViewById(R.id.prevbtn);
        firstImg = findViewById(R.id.firstImg);
        secondImg = findViewById(R.id.secImg);
        thirdImg = findViewById(R.id.thirdImg);
        fourthImg = findViewById(R.id.fourthImg);
        firstTxt = findViewById(R.id.firsttxt);
        secondTxt = findViewById(R.id.sectxt);
        thirdTxt = findViewById(R.id.thirdtxt);
        fourthTxt = findViewById(R.id.fourthtxt);
        shapeSeekLayout = findViewById(R.id.Sizelayout);
        sizeSeekBarLayout = findViewById(R.id.seekBarlayout1);
        shapesSeekBarsize = findViewById(R.id.sizeSeekbar2);

    }

    private void setFirstData() {
        firstImg.setBackgroundResource(R.drawable.square_l);
        secondImg.setBackgroundResource(R.drawable.t_shape_l);
        thirdImg.setBackgroundResource(R.drawable.l_shape_l);
        fourthImg.setBackgroundResource(R.drawable.u_shape_l);
        firstTxt.setText(getString(R.string.square));
        secondTxt.setText(getString(R.string.t_shape));
        thirdTxt.setText(getString(R.string.l_shape));
        fourthTxt.setText(getString(R.string.u_shape));
        firstimgValue = R.drawable.square;
        seconDImgValue = R.drawable.t_shape;
        thirdimgValue = R.drawable.l_shape;
        fourthImgValue = R.drawable.u_shape;
        currentPosition = 1;
        setTouchListenersForDragImage(firstImg);
        setTouchListenersForDragImage(secondImg);
        setTouchListenersForDragImage(thirdImg);
        setTouchListenersForDragImage(fourthImg);
    }

    private void setSecondtData() {
        firstImg.setBackgroundResource(R.drawable.beds);
        secondImg.setBackgroundResource(R.drawable.chairs);
        thirdImg.setBackgroundResource(R.drawable.door);
        fourthImg.setBackgroundResource(R.drawable.grass);
        firstTxt.setText(getString(R.string.bed));
        secondTxt.setText(getString(R.string.chair));
        thirdTxt.setText(getString(R.string.door));
        fourthTxt.setText(getString(R.string.grass));
        firstimgValue = R.drawable.beds;
        seconDImgValue = R.drawable.chairs;
        thirdimgValue = R.drawable.door;
        fourthImgValue = R.drawable.grass;
        currentPosition = 2;
        setTouchListenersForDragImage(firstImg);
        setTouchListenersForDragImage(secondImg);
        setTouchListenersForDragImage(thirdImg);
        setTouchListenersForDragImage(fourthImg);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTouchListenersForDragImage(firstImg);
        setTouchListenersForDragImage(secondImg);
        setTouchListenersForDragImage(thirdImg);
        setTouchListenersForDragImage(fourthImg);
        prevBtn.setEnabled(false);
        prevBtn.setAlpha(0.3f);
        seekBarSize.setMax(100);
        seekBarAngle.setMax(360);
        shapesSeekBarsize.setMax(400);
    }

    private void setThirdData() {
        firstImg.setBackgroundResource(R.drawable.treee);
        secondImg.setBackgroundResource(R.drawable.stairs);
        thirdImg.setBackgroundResource(R.drawable.sofa);
        fourthImg.setBackgroundResource(R.drawable.window);
        firstTxt.setText(getString(R.string.tree));
        secondTxt.setText(getString(R.string.stairs));
        thirdTxt.setText(getString(R.string.sofa));
        fourthTxt.setText(getString(R.string.window));
        firstimgValue = R.drawable.treee;
        seconDImgValue = R.drawable.stairs;
        thirdimgValue = R.drawable.sofa;
        fourthImgValue = R.drawable.window;
        currentPosition = 3;
        setTouchListenersForDragImage(firstImg);
        setTouchListenersForDragImage(secondImg);
        setTouchListenersForDragImage(thirdImg);
        setTouchListenersForDragImage(fourthImg);
    }

    private void showdraggableImage(ImageView imageView, float x, float y) {
        // Create the ImageView for the dragged image
        if (firstimgValue == R.drawable.square || seconDImgValue == R.drawable.t_shape ||
                thirdimgValue == R.drawable.l_shape || fourthImgValue == R.drawable.u_shape) {
            setDifferentHeight(imageView, x, y);
        } else {
            ImageView newImageView = new ImageView(DragableActivity.this);
            if (imageView.getTag().equals(getString(R.string.first))) {
                selected_IMG = firstimgValue;
                newImageView.setBackgroundResource(firstimgValue);
            } else if (imageView.getTag().equals(getString(R.string.second))) {
                selected_IMG = seconDImgValue;
                newImageView.setBackgroundResource(seconDImgValue);
            } else if (imageView.getTag().equals(getString(R.string.third))) {
                selected_IMG = thirdimgValue;
                newImageView.setBackgroundResource(thirdimgValue);
            } else if (imageView.getTag().equals(getString(R.string.four))) {
                selected_IMG = fourthImgValue;
                newImageView.setBackgroundResource(fourthImgValue);
            }
            newImageView.setLayoutParams(new FrameLayout.LayoutParams(100, 100));

            FrameLayout frameLayout = new FrameLayout(this);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(120, 120);
            frameLayout.setLayoutParams(layoutParams);
            frameLayout.setPadding(10, 10, 10, 10);
            frameLayout.addView(newImageView);
            if (selectedFrameLayout != null) {
                selectedFrameLayout.setBackgroundResource(0);
            }
            selectedFrameLayout = null;
            selectedImageView = null;
            selectedFrameLayout = frameLayout;
            selectedImageView = newImageView;
            dropLayout.addView(frameLayout);
            //    dropLayout.addView(newImageView);
            // Set the position of the new image based on the touch position
            offsetX = 120 / 2.0f;
            offsetY = 120 / 2.0f;
            defX = x - offsetX;
            defY = y - offsetY;
            frameLayout.setX(x - offsetX);
            frameLayout.setY(y - offsetY);
            // Set touch listeners for resizing and rotating the new image
            setTouchListenersForImage(newImageView, selected_IMG);
            // Clear the selected image
            selectedImageView = null;
        }
        //////STart from here for code tommmorow


    }

    private void setDifferentHeight(ImageView imageView, float x, float y) {
        Log.d(TAG, "setDifferentHeight: is called");
        ImageView newImageView = new ImageView(DragableActivity.this);
        if (imageView.getTag().equals(getString(R.string.first))) {
            selected_IMG = firstimgValue;
            newImageView.setBackgroundResource(firstimgValue);
        } else if (imageView.getTag().equals(getString(R.string.second))) {
            selected_IMG = seconDImgValue;
            newImageView.setBackgroundResource(seconDImgValue);
        } else if (imageView.getTag().equals(getString(R.string.third))) {
            selected_IMG = thirdimgValue;
            newImageView.setBackgroundResource(thirdimgValue);
        } else if (imageView.getTag().equals(getString(R.string.four))) {
            selected_IMG = fourthImgValue;
            newImageView.setBackgroundResource(fourthImgValue);
        }
        newImageView.setLayoutParams(new FrameLayout.LayoutParams(210, 210));

        FrameLayout frameLayout = new FrameLayout(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(230, 230);
        frameLayout.setLayoutParams(layoutParams);
        frameLayout.setPadding(10, 10, 10, 10);
        frameLayout.addView(newImageView);
        if (selectedFrameLayout != null) {
            selectedFrameLayout.setBackgroundResource(0);
        }
        selectedFrameLayout = null;
        selectedImageView = null;
        selectedFrameLayout = frameLayout;
        selectedImageView = newImageView;
        dropLayout.addView(frameLayout);
        //    dropLayout.addView(newImageView);
        // Set the position of the new image based on the touch position
        offsetX = 230 / 2.0f;
        offsetY = 230 / 2.0f;
        defX = x - offsetX;
        defY = y - offsetY;
        frameLayout.setX(x - offsetX);
        frameLayout.setY(y - offsetY);
        // Set touch listeners for resizing and rotating the new image
        setTouchListenersForImage(newImageView, selected_IMG);
        // Clear the selected image
        selectedImageView = null;
    }

    private void setTouchListenersForImage(final View view, int IMG) {
        view.setOnTouchListener(new View.OnTouchListener() {
            float startX, startY;
            float offsetX, offsetY;
            boolean isDragging = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v == view) {
                    float x = event.getRawX();
                    float y = event.getRawY();
//                    if(IMG==R.drawable.square||IMG==R.drawable.t_shape||IMG==R.drawable.u_shape||IMG==R.drawable.l_shape){
//                        seekBarSize.setMax(400);
//                    }
//                    else{
//                        seekBarSize.setMax(100);
//                    }
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            startX = x;
                            startY = y;

                            if (selectedFrameLayout != null) {
                                selectedFrameLayout.setBackgroundResource(0);
                            }

                            if (view instanceof FrameLayout) {
                                view.setBackgroundResource(R.drawable.border);
                            }
                            View view2 = (View) view.getParent();
//                            offsetX = x - view.getX();
//                            offsetY = y - view.getY();

                            offsetX = x - view2.getX();
                            offsetY = y - view2.getY();

                            selectedImageView = null;
                            selectedFrameLayout = null;

                            if (view instanceof ImageView) {
                                selectedImageView = (ImageView) view;
                                selectedFrameLayout = (FrameLayout) selectedImageView.getParent();
                                if (IMG == R.drawable.square || IMG == R.drawable.l_shape || IMG == R.drawable.u_shape || IMG == R.drawable.t_shape) {
                                    shapeSeekLayout.setVisibility(View.VISIBLE);
                                    sizeSeekBarLayout.setVisibility(View.GONE);
                                    shapesSeekBarsize.setProgress(selectedImageView.getLayoutParams().width - 150);
                                } else {
                                    shapeSeekLayout.setVisibility(View.GONE);
                                    sizeSeekBarLayout.setVisibility(View.VISIBLE);
                                    seekBarSize.setProgress(selectedImageView.getLayoutParams().width - 100);
                                }

//                                seekBarAngle.setProgress((int) selectedImageView.getRotation());
                            } else if (view instanceof FrameLayout) {
                                selectedFrameLayout = (FrameLayout) view;
                            }

                            if (selectedFrameLayout != null) {
                                selectedFrameLayout.setBackgroundResource(R.drawable.border);
                            }
                            isDragging = false; // Reset dragging state
                            Log.d(TAG, "onTouch: " + view2.getX() + "///////" + view2.getY() + "//////" + defX + "//////" + defY);
//                            selectedFrameLayout.setX(defX);
//                            selectedFrameLayout.setY(defY);
                            break;

                        case MotionEvent.ACTION_MOVE:
                            if (selectedFrameLayout != null) {
                                float newX = x - offsetX;
                                float newY = y - offsetY;

                                // Ensure the new position is within the bounds of the drop layout
                                newX = Math.max(0, Math.min(newX, dropLayout.getWidth() - selectedFrameLayout.getWidth()));
                                newY = Math.max(0, Math.min(newY, dropLayout.getHeight() - selectedFrameLayout.getHeight()));

                                // Update the position of the selected view within the frame layout
                                selectedFrameLayout.setX(newX);
                                selectedFrameLayout.setY(newY);
                            }
                            break;

                        case MotionEvent.ACTION_UP:
                            isDragging = false; // Reset dragging state
                            // Handle any action upon release, if needed
                            break;
                    }


                    return true;
                }
                return false;
            }
        });

        view.setOnLongClickListener(view1 -> {
            dropLayout.removeView(view);
            return true;
        });
    }


    private void captureAndSaveImage(FrameLayout frameLayout) {
        // Create a bitmap with the same dimensions as the FrameLayout
        Bitmap bitmap = Bitmap.createBitmap(frameLayout.getWidth(), frameLayout.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        frameLayout.draw(canvas);
        String imageFileName = "my_image"; // Provide a suitable file name
        String mimeType = "image/jpeg"; // Change to the desired MIME type if needed
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, imageFileName);
        values.put(MediaStore.Images.Media.MIME_TYPE, mimeType);
        Uri imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        try (OutputStream outputStream = getContentResolver().openOutputStream(imageUri)) {
            if (outputStream != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                Toast.makeText(this, "Image Saved in Gallery", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Log.d("TAG", "captureAndSaveImage: " + e);
            e.printStackTrace();
        }
    }

    public boolean setbackgroundToDropLayout() {
        dropLayout.setBackgroundColor(getResources().getColor(R.color.white_gary));
        return true;
    }

    private void setTouchListenersForDragImage(final ImageView imageView) {
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (view instanceof ImageView) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            startX = event.getX();
                            startY = event.getY();

                            return true; // Consume the touch event

                        case MotionEvent.ACTION_MOVE:
                            float deltaX = Math.abs(event.getX() - startX);
                            float deltaY = Math.abs(event.getY() - startY);

                            // Start the drag event
                            ClipData data = ClipData.newPlainText("", "");
                            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                            view.startDragAndDrop(data, shadowBuilder, view, 0);
                            boolean isBackgroundSet = setbackgroundToDropLayout();
                            return true; // Consume the touch event for dragging
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            startX = startY = 0;

                            return true;
                        default:
                            return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        //  super.onBackPressed();
      /*  Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("adcountreturn",adcountvalue);
        startActivity(intent);*/
        finish();
    }
}
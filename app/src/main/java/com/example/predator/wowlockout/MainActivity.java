package com.example.predator.wowlockout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class MainActivity extends Activity {

    //Creating Array List to store dungeon list
    ArrayList<String> NH=new ArrayList<String>();
    ArrayList<String> HH=new ArrayList<String>();
    ArrayList<String> MH=new ArrayList<String>();

    private TextView Label;

    ListView List;


    //Establishing connection to Firebase and Creating Database Reference
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List=(ListView)findViewById(R.id.listView);

        Label = (TextView)this.findViewById(R.id.textView2);

        mDatabase=FirebaseDatabase.getInstance().getReference();
    }


    //Button Functions
    public void NormalButtonPressed(View v){
        mDatabase.child("NormalD").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot :dataSnapshot.getChildren()){
                    String stext = postSnapshot.getValue().toString();

                    NH.add(stext);
                    Log.d("Locations updated", "location: " + stext);


                }}

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Label.setText("Normal Dungeon");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.dungeons,R.id.Normal,NH);
        List.setAdapter(adapter);
        HH.clear();
        MH.clear();
    }

    public void HeroicButtonPressed(View v)
    {

        mDatabase.child("HeroicD").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot :dataSnapshot.getChildren()){
                    String stext = postSnapshot.getValue().toString();

                    HH.add(stext);
                    Log.d("Locations updated", "location: " + stext);


                }}

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Label.setText("Heroic Dungeon");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.dungeons,R.id.Normal,HH);
        List.setAdapter(adapter);
        NH.clear();
        MH.clear();
    }
    public void MythicButtonPressed(View v)
    {

        mDatabase.child("MythicD").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot :dataSnapshot.getChildren()){
                    String stext = postSnapshot.getValue().toString();

                    MH.add(stext);
                    Log.d("Locations updated", "location: " + stext);


                }}

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Label.setText("Mythic Dungeon");
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.dungeons,R.id.Normal,MH);
        List.setAdapter(adapter);
        NH.clear();
        HH.clear();

    }
}

package my.edu.utar.cheehaoyang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RVActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RVAdapter adapter;
    DAOUser dao;
    String key = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvactivity);


        recyclerView =findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter =new RVAdapter(this);
        recyclerView.setAdapter(adapter);
        dao = new DAOUser();

        loadData();
    }

    private void loadData() {
        dao.get().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Data> dats = new ArrayList<>();

                for(DataSnapshot data : snapshot.getChildren()){
                   Data dat =data.getValue(Data.class);
                   dat.setKey(data.getKey());
                   dats.add(dat);
                   key =data.getKey();

                }
                adapter.setItem(dats);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }





}
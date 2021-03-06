package my.edu.utar.cheehaoyang;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOUser {

    private DatabaseReference databaseReference;
    public DAOUser(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference =db.getReference(Data.class.getSimpleName());
    }

    public Task<Void> add(Data data)
    {

        return databaseReference.push().setValue(data);

    }

    public Task<Void> update(String key, HashMap<String , Object> hashMap)
    {
       return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key)
    {
        return databaseReference.child(key).removeValue();
    }

    public Query get()
    {
        return databaseReference.orderByKey();
    }



}

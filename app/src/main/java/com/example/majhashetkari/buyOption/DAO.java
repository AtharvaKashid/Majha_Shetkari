package com.example.majhashetkari.buyOption;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAO {
    private DatabaseReference databaseReference;
    public DAO(){
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Model.class.getSimpleName());
    }
    public Task<Void> add(Model m)
    {
        return   databaseReference.push().setValue(m);
    }
}

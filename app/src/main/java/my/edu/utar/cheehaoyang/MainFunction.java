package my.edu.utar.cheehaoyang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainFunction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_function);

       final EditText name =findViewById(R.id.name);
       final EditText email = findViewById(R.id.email);
       final EditText password =findViewById(R.id.password);
       final EditText pin = findViewById(R.id.pin);
        Button btn =findViewById(R.id.btn_submit);

        Button btn_open =findViewById(R.id.btn_open);
        btn_open.setOnClickListener(v->{
            Intent intent =new Intent(MainFunction.this, RVActivity.class);
            startActivity(intent);
        });


        DAOUser dao = new DAOUser();
        Data data_edit = (Data)getIntent().getSerializableExtra("EDIT");
        if(data_edit != null){
            btn.setText("UPDATE");
            name.setText(data_edit.getName());
            email.setText(data_edit.getEmail());
            password.setText(data_edit.getPassword());
            pin.setText(data_edit.getPin());
            btn_open.setVisibility(View.GONE);
        }
        else{
            btn.setText("SUBMIT");
            btn_open.setVisibility(View.VISIBLE);
        }

        btn.setOnClickListener(v->
        {
            Data data = new Data(name.getText().toString(), email.getText().toString(),password.getText().toString(),pin.getText().toString());
            if(data_edit ==null) {


                dao.add(data).addOnSuccessListener(suc -> {
                    Toast.makeText(this, "Record is successfully inserted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er -> {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });

            }else {
                HashMap<String ,Object> hashMap =new HashMap<>();
                hashMap.put("name",name.getText().toString());
                hashMap.put("email",email.getText().toString());
                hashMap.put("password", password.getText().toString());
                hashMap.put("pin", pin.getText().toString());

                dao.update(data_edit.getKey(),hashMap).addOnSuccessListener(suc->{
                    Toast.makeText(this,"Record is updated",Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(er ->{
                    Toast.makeText(this,"" + er.getMessage(),Toast.LENGTH_SHORT).show();
                });


//                dao.remove("-MyT5N4dk7JpmLMaAag8").addOnSuccessListener(suc->{
//                    Toast.makeText(this,"Record is updated",Toast.LENGTH_SHORT).show();
//                }).addOnFailureListener(er ->{
//                    Toast.makeText(this,"" + er.getMessage(),Toast.LENGTH_SHORT).show();
//                });
            }







        });


    }
}
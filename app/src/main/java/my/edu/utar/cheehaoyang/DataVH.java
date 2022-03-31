package my.edu.utar.cheehaoyang;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataVH extends RecyclerView.ViewHolder {

    public TextView txt_name,txt_email,txt_password,txt_pin, txt_option;

    public DataVH(@NonNull View itemView) {
        super(itemView);
        txt_name = itemView.findViewById(R.id.txt_name);
        txt_email =itemView.findViewById(R.id.txt_email);
        txt_password =itemView.findViewById(R.id.txt_password);
        txt_pin =itemView.findViewById(R.id.txt_pin);
        txt_option =itemView.findViewById(R.id.txt_option);

    }
}

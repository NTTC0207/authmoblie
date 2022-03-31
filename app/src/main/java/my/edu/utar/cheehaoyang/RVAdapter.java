package my.edu.utar.cheehaoyang;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    ArrayList<Data> list = new ArrayList<>();
    public RVAdapter(Context ctx){
        this.context = ctx;
    }

    public void setItem(ArrayList<Data> data){
        list.addAll(data);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new DataVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
           DataVH vh =(DataVH) holder;
           Data data = list.get(position);
           vh.txt_name.setText(data.getName());
           vh.txt_email.setText(data.getEmail());
           vh.txt_password.setText(data.getPassword());
           vh.txt_pin.setText(data.getPin());


           vh.txt_option.setOnClickListener(v->{
               PopupMenu popupMenu =new PopupMenu(context,vh.txt_option);
               popupMenu.inflate(R.menu.option_menu);
               popupMenu.setOnMenuItemClickListener(item->{
               switch (item.getItemId())
               {
                   case R.id.menu_edit:
                       Intent intent =new Intent(context,MainFunction.class);
                       intent.putExtra("EDIT",data);
                       context.startActivity(intent);
                       break;
                   case R.id.menu_remove:
                       DAOUser dao = new DAOUser();
                       dao.remove(data.getKey()).addOnSuccessListener(suc->{
                    Toast.makeText(context,"Record is removed",Toast.LENGTH_SHORT).show();
                    notifyItemRemoved(position);
                }).addOnFailureListener(er ->{
                    Toast.makeText(context,"" + er.getMessage(),Toast.LENGTH_SHORT).show();
                });
                       break;
               }
               return  false;
               });
popupMenu.show();
           });
    }

    @Override
    public int getItemCount() {
       return list.size();
    }
}

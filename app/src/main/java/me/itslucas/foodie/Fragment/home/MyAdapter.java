package me.itslucas.foodie.Fragment.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.itslucas.foodie.R;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

private List<String> mDatas;

private LayoutInflater inflater;
public MyAdapter(List<String> datas){
    mDatas = datas;

}    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
       inflater =LayoutInflater.from(parent.getContext());
//View view = inflater.inflate(R.layout.item,parent,false);
        return null;//new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{


        public ViewHolder( View itemView) {
            super(itemView);
        }
    }
}

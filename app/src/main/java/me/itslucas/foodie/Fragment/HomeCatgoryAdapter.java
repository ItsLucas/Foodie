package me.itslucas.foodie.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.itslucas.foodie.beans.Category;
import me.itslucas.foodie.beans.HomeCampaign;
import me.itslucas.foodie.beans.HomeCategory;
import org.jetbrains.annotations.NotNull;
import me.itslucas.foodie.R;

import java.util.List;

public class HomeCatgoryAdapter extends RecyclerView.Adapter<HomeCatgoryAdapter.ViewHolder> {


    private static final int VIEW_TYPE_R = 0;
    private static final int VIEW_TYPE_L = 1;
    private LayoutInflater mInflater;
    private OnCatgoryClickListener mListener;

    private List<HomeCategory> mDatas;
    private Context mContext;
    public HomeCatgoryAdapter(List<HomeCategory> datas,Context context){

        mDatas = datas;
        this.mContext =context;
    }
    public  HomeCatgoryAdapter(List<HomeCategory> datas){
        mDatas = datas;
    }

    public void setOnCatgoryCLickLIstener(OnCatgoryClickListener lIstener){
        this.mListener =lIstener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        mInflater = LayoutInflater.from(parent.getContext());
        if(viewType == VIEW_TYPE_R){

            return  new ViewHolder(mInflater.inflate(R.layout.home_cardview2,parent,false));
        }

        return  new ViewHolder(mInflater.inflate(R.layout.home_cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder viewHolder, int i) {

        HomeCategory category = mDatas.get(i);
        viewHolder.textTitle.setText(category.getName());
        viewHolder.imageViewBig.setImageResource(category.getImgBig());
        viewHolder.imageViewSmallTop.setImageResource(category.getImgSmallTop());
        viewHolder.imageViewSmallBottom.setImageResource(category.getImgSmallBottom());

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position % 2==0){
            return  VIEW_TYPE_R;
        }
        else return VIEW_TYPE_L;
    }


     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textTitle;
        ImageView imageViewBig;
        ImageView imageViewSmallTop;
        ImageView imageViewSmallBottom;
        public ViewHolder(View itemView){
            super(itemView);

            textTitle = (TextView) itemView.findViewById(R.id.text_title);
            imageViewBig = (ImageView) itemView.findViewById(R.id.imgview_big);
            imageViewSmallTop = (ImageView) itemView.findViewById(R.id.imgview_small_top);
            imageViewSmallBottom = (ImageView) itemView.findViewById(R.id.imgview_small_bottom);

            imageViewBig.setOnClickListener(this);
            imageViewSmallTop.setOnClickListener(this);
            imageViewSmallBottom.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            HomeCategory category = mDatas.get(getLayoutPosition());
            if(mListener!=null){

                switch (v.getId()){
                    case R.id.imgview_big:
                        mListener.onClick(v,category.getCtBig());

                        //TODO: middle
                        Toast.makeText(v.getContext(), category.getCtBig().getName(),Toast.LENGTH_LONG).show();

                        break;
                    case  R.id.imgview_small_top:
                        mListener.onClick(v,category.getCtStop());
                        Toast.makeText(v.getContext(), category.getCtStop().getName(),Toast.LENGTH_LONG).show();
                        break;
                    case R.id.imgview_small_bottom:
                        mListener.onClick(v,category.getCtSbom());
                        Toast.makeText(v.getContext(), category.getCtSbom().getName(),Toast.LENGTH_LONG).show();
                        break;
                }

            }

        }
    }

   public interface OnCatgoryClickListener{
        void onClick(View view, Category category);
    }

}

package com.ezdata.washtakephoto.adapter;

import android.content.Context;


import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ezdata.commonlib.core.App;
import com.ezdata.washtakephoto.R;
import com.ezdata.washtakephoto.bean.PhotoDetail;
import com.ezdata.washtakephoto.widget.AmountVIew;

import java.util.List;



/**
 * @author xiaocq
 * @date 2017/10/30 14:03
 * @project KsiotApp
 * @introduce
 * @see
 * @since JDK 1.8.0_112
 */
public class ClothListAdapter extends RecyclerView.Adapter<ClothListAdapter.ViewHolder> {

    //private OnItemClickListener mListener;
    private Context mContext;

    private List<PhotoDetail.ClothGroup> dataList;//衣服组合

    //弹出提示框
    //CustomDialog exitDialog;

    public ClothListAdapter(Context context, List<PhotoDetail.ClothGroup> clothes) {
        this.dataList = clothes;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.item_cloth_num, parent, false);
        return new ViewHolder (view);
    }


    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        holder.itemView.setTag(position);//
        PhotoDetail.ClothGroup toy = dataList.get(position);
        Log.d("suhong", "onBindViewHolder: "+dataList.size()+"---"+position);

        holder.clothTypeTv.setText(toy.name);
    }


    @Override
    public int getItemCount () {
        return dataList.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView clothTypeTv;
        AmountVIew numAIv;

        public ViewHolder (final View itemView) {
            super (itemView);
            //ButterKnife.bind (this, itemView);
            clothTypeTv = itemView.findViewById(R.id.clothTypeTv);
            numAIv = itemView.findViewById(R.id.numAIv);
            numAIv.setOnAmountChangeListener(new AmountVIew.OnAmountChangeListener() {
                @Override
                public void onAmountChange(View view, int amount) {
                    //Toast.makeText(mContext, "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
                    App.d_loc("getPosition："+itemView.getTag());
                    mListener.onNumChange(view,(int)itemView.getTag(),amount);//
                }
            });


        }
    }

//    public interface OnItemClickListener{
//        void onItemClick(View view, int position);
//    }

    private OnNumChangeListener mListener;

    public interface  OnNumChangeListener{
        void onNumChange(View v,int position,int num);
    }
    public void setOnNumChangeListener(OnNumChangeListener onNumChangeListener) {
        this.mListener = onNumChangeListener;
    }

}

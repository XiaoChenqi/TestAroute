package com.ezdata.washtakephoto.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.ezdata.washtakephoto.R;
import com.ezdata.washtakephoto.bean.WashTaskDetail;

import java.util.List;


/**
 * @author xiaocq
 * @date 2017/10/30 14:03
 * @project KsiotApp
 * @introduce
 * @see
 * @since JDK 1.8.0_112
 */
public class ClothIdListAdapter extends RecyclerView.Adapter<ClothIdListAdapter.ViewHolder> {



    private List<WashTaskDetail.clothDetail> dataList;
    private List<WashTaskDetail> washTaskDetailsList;
    private Context mContext;
    private String  currentName="";//当前衣服种类，种类改变要变色，看起来方便
    private int  currentColor=0;//当前字体颜色



//    public ClothIdListAdapter(List<WashTaskDetail> washTaskDetailsList,Context context) {
//
//        this.washTaskDetailsList = washTaskDetailsList;
//        this.mContext = context;
//    }
    public ClothIdListAdapter(Context context, List<WashTaskDetail.clothDetail> dataList) {

        this.dataList = dataList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.item_cloth_id, parent, false);


        return new ViewHolder (view);
    }


    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        holder.itemView.setTag(position);//
        String clothCode = dataList.get(position).code;

        if(!currentName.equals(clothCode.substring(0,2))){//变色
            if(currentColor==mContext.getResources().getColor(R.color.colorBlack)){//判断当前颜色
                currentColor = mContext.getResources().getColor(R.color.colorWhite);
            }else{
                currentColor = mContext.getResources().getColor(R.color.colorBlack);
            }
        }
        holder.clothIdTv.setText(clothCode);
        holder.clothIdTv.setTextColor(currentColor);
        currentName = clothCode.substring(0,2);

    }


    @Override
    public int getItemCount () {
        return dataList.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView clothIdTv;


        public ViewHolder (final View itemView) {
            super (itemView);
            //ButterKnife.bind (this, itemView);
            clothIdTv = itemView.findViewById(R.id.clothIdTv);
            clothIdTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v, (int) itemView.getTag());
                }
            });
        }
    }

    private onItemClickListener mListener;

    public interface onItemClickListener{
        void onItemClick(View v,int Position);
    }
    public void setOnItemClickListener(onItemClickListener listener) {
        this.mListener = listener;
    }

}

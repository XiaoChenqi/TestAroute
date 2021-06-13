package com.ezdata.washtakephoto.adapter;

import android.content.Context;



import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ezdata.washtakephoto.R;
import com.ezdata.washtakephoto.bean.WashTask;


import java.util.ArrayList;
import java.util.List;


/**
 * @author xiaocq
 * @date 2017/10/30 14:03
 * @project KsiotApp
 * @introduce
 * @see
 * @since JDK 1.8.0_112
 */
public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    //private OnItemClickListener mListener;

    private final String Status_UnStart = "UnStart";
    private final String Status_Start   = "Start";
    private final String Status_Finish  = "Finish";
    private final String Status_PASS    = "Pass";

    private List<WashTask> dataList;
    private Context mContext;

    private List<StringBuilder> imgs=new ArrayList<>();

    //弹出提示框
    //CustomDialog exitDialog;

    public TaskListAdapter(Context context, List<WashTask> dataList) {

        this.dataList = dataList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.item_task, parent, false);


        return new ViewHolder (view);
    }


    @Override
    public void onBindViewHolder (ViewHolder holder, int position) {
        holder.itemView.setTag(position);//
        WashTask task = dataList.get(position);
        Log.d("suhong", "onBindViewHolder: "+dataList.size()+"---"+position);

        holder.clothTitleTv.setText(task.classify_names);
        holder.clothNumTv.setText(task.number_desc+"件");
        if(task.status.equals(Status_UnStart)){
            holder.taskStateTv.setText("未开始");
            holder.taskStateTv.setTextColor(mContext.getResources().getColor(R.color.blue));
        }else if(task.status.equals(Status_Start)){
            holder.taskStateTv.setText("进行中");
            holder.taskStateTv.setTextColor(mContext.getResources().getColor(R.color.colorLightOrange));
        }else if(task.status.equals(Status_Finish)){
            holder.taskStateTv.setText("已结束");
            holder.taskStateTv.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
        }else if(task.status.equals(Status_PASS)){
            holder.taskStateTv.setText("已审核");
            holder.taskStateTv.setTextColor(mContext.getResources().getColor(R.color.colorGreen));
        }

        WashTask.cloth detail = task.detail;
        List<String> clothCodes = detail.codes;
        StringBuilder strB = new StringBuilder();
        for(String temp :clothCodes){
            strB.append(temp).append(",");
        }
        holder.clothContentTv.setText(strB.toString());
    }


    @Override
    public int getItemCount () {
        return dataList.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView clothTitleTv;
        TextView clothNumTv;
        TextView taskStateTv;
        TextView clothContentTv;
        //ConstraintLayout clothCl;

        public ViewHolder (final View itemView) {
            super (itemView);
            //ButterKnife.bind (this, itemView);
            clothTitleTv = itemView.findViewById(R.id.clothTitleTv);
            clothNumTv = itemView.findViewById(R.id.clothNumTv);
            taskStateTv = itemView.findViewById(R.id.taskStateTv);
            clothContentTv = itemView.findViewById(R.id.clothContentTv);
//            clothCl = itemView.findViewById(R.id.clothCl);
//            clothCl.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    App.d_loc("getPosition："+itemView.getTag());
//                    mListener.onItemClick(v,(int)itemView.getTag());//
//                }
//            });


        }
    }

//    public interface OnItemClickListener{
//        void onItemClick(View view, int position);
//    }

}

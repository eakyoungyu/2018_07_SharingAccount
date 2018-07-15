package com.y2k2.sharingaccount;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.y2k2.sharingaccount.R;

import java.util.ArrayList;

/**
 * Created by my on 2018-07-12.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {
    private ArrayList<BankStatementInfo> mItems =new ArrayList<>();
    public RecyclerAdapter() {//기존 노티 추가시 초기화

    }


    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,parent,false);
        return new ItemViewHolder(view);
    }


    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        //여기도 인터페이스

        BankStatementInfo bstate=mItems.get(position);
        String io=bstate.getInOut();

        //여기는 나중에 UI로 표시
        if(io==bstate.IN) io.replace("IN", "입금");
        else if(io==bstate.OUT) io.replace("OUT", "출금");
        else io=bstate.getInOut();
        holder.mtextPackage.setText(io+ " "+bstate.getAmount());

        holder.mtextTitle.setText(bstate.getAccount());
        holder.mtextText.setText(bstate.getDate()+" "+bstate.getTime());
    }

    // 데이터 셋의 크기를 리턴해줍니다.
    @Override
    public int getItemCount() {
        if(mItems.isEmpty())    return 0;
        return mItems.size();
    }

    // 커스텀 뷰홀더
// item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView mtextPackage;
        TextView mtextTitle;
        TextView mtextText;
        public ItemViewHolder(View itemView) {
            super(itemView);
            mtextPackage = (TextView) itemView.findViewById(R.id.textPackageName);
            mtextTitle=(TextView)itemView.findViewById(R.id.textTitle);
            mtextText=(TextView)itemView.findViewById(R.id.textText);

        }
    }

/*
    public void add(NotificationInfo data){
        mItems.add(data);
        notifyDataSetChanged();
        //notifyItemInserted(getItemCount());
    }
    */
    public void add(BankStatementInfo data){
        mItems.add(data);
        notifyDataSetChanged();
        //notifyItemInserted(getItemCount());
    }
}

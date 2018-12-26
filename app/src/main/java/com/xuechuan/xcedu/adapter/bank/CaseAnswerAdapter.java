package com.xuechuan.xcedu.adapter.bank;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.xuechuan.xcedu.R;
import com.xuechuan.xcedu.vo.CaseCardVo;
import com.xuechuan.xcedu.vo.SqliteVo.QuestionSqliteVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: xcedu
 * @Package com.xuechuan.xcedu.adapter.bank
 * @Description: 案例分析答题卡
 * @author: L-BackPacker
 * @date: 2018.12.26 下午 5:19
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class CaseAnswerAdapter extends RecyclerView.Adapter<CaseAnswerAdapter.CaseViewHolder> {
    private Context mContext;
    private List<?> mListDatas;
    private LayoutInflater mInflater;
    private ArrayList<HashMap<Integer, Boolean>> mHashMaps;

    public CaseAnswerAdapter(Context mContext, List<?> mListDatas) {
        this.mContext = mContext;
        this.mListDatas = mListDatas;
        mInflater = LayoutInflater.from(mContext);
        initData(mListDatas);
    }

    private void initData(List<?> mListDatas) {
        mHashMaps = new ArrayList<>();
        for (int i = 0; i < mListDatas.size(); i++) {
            HashMap<Integer, Boolean> map = new HashMap<>();
            CaseCardVo vo = (CaseCardVo) mListDatas.get(i);
            if (vo.getList() == null || vo.getList().isEmpty()) {
                map.put(i, false);
            } else {
                map.put(i, true);
            }
            mHashMaps.add(map);
        }

    }


    @NonNull
    @Override
    public CaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_case_answer, null);
        CaseViewHolder holder = new CaseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CaseViewHolder holder, int position) {
        CaseCardVo vo = (CaseCardVo) mListDatas.get(position);
        holder.mTvCaseCardContent.setVisibility(View.VISIBLE);
        StringBuffer buffer = new StringBuffer();
        buffer.append("第");
        buffer.append(String.valueOf(position + 1));
        buffer.append("题");
        holder.mTvCaseCardContent.setText(buffer.toString());
        ArrayList<QuestionSqliteVo> list = vo.getList();
        if (list == null || list.isEmpty()) return;
        GmGridViewAdapter adapter = new GmGridViewAdapter(mContext, list);
        holder.mGvPopContent.setAdapter(adapter);
        HashMap<Integer, Boolean> map = mHashMaps.get(position);
        Boolean aBoolean = map.get(position);
        holder.mGvPopContent.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return mListDatas.size();
    }

    public class CaseViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvCaseCardContent;
        public GridView mGvPopContent;

        public CaseViewHolder(View itemView) {
            super(itemView);
            this.mTvCaseCardContent = (TextView) itemView.findViewById(R.id.tv_case_card_content);
            this.mGvPopContent = (GridView) itemView.findViewById(R.id.gv_pop_content);
        }
    }


}

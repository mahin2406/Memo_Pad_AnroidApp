package edu.jsu.mcis.cs408.memo_pad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MemoRecyclerViewAdapter extends RecyclerView.Adapter<MemoRecyclerViewAdapter.ViewHolder> {

    private List<Memo> memoList;

    public MemoRecyclerViewAdapter(List<Memo> memoList) {
        this.memoList = memoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.memo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Memo memo = memoList.get(position);
        holder.textViewMemoItem.setText("#" + memo.getId() + ": " + memo.getMemo());
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMemoItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMemoItem = itemView.findViewById(R.id.textViewMemoItem);
        }
    }
}
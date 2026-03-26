package edu.jsu.mcis.cs408.memo_pad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.jsu.mcis.cs408.memo_pad.model.Memo;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final List<Memo> data;
    private final MainActivity activity;

    public RecyclerViewAdapter(MainActivity activity, List<Memo> data) {
        this.activity = activity;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.memo_item, parent, false);

        view.setOnClickListener(activity.getItemClick());

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Memo memo = data.get(position);
        holder.textViewMemoItem.setText("#" + memo.getId() + ": " + memo.getMemo());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Memo getItem(int position) {
        return data.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewMemoItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMemoItem = itemView.findViewById(R.id.textViewMemoItem);
        }
    }
}
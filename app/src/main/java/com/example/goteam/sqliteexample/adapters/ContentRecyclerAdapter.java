package com.example.goteam.sqliteexample.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.goteam.sqliteexample.R;
import com.example.goteam.sqliteexample.modal.Content;

import java.util.List;

public class ContentRecyclerAdapter extends RecyclerView.Adapter<ContentRecyclerAdapter.ContentViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Content item);
    }

    private List<Content> listContent;
    private final OnItemClickListener listener;

    public ContentRecyclerAdapter(List<Content> listContent,OnItemClickListener listener) {
        this.listContent = listContent;
        this.listener = listener;
    }


    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_content_recycler, parent, false);

        return new ContentViewHolder(itemView);
    }


        @Override
    public void onBindViewHolder(ContentViewHolder holder, int position) {
        holder.bind(listContent.get(position),listener);

    }

    @Override
    public int getItemCount() {
        Log.v(ContentRecyclerAdapter.class.getSimpleName(),""+listContent.size());
        return listContent.size();
    }


    /**
     * ViewHolder class
     */
    public class ContentViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewModuleName;

        public ContentViewHolder(View view) {
            super(view);
            textViewModuleName = (AppCompatTextView) view.findViewById(R.id.textViewModuleName);
        }

        public void bind(final Content item, final OnItemClickListener listener) {
            textViewModuleName.setText(item.getModuleName());

            textViewModuleName.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }


}

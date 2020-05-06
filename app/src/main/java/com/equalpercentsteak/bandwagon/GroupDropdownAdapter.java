package com.equalpercentsteak.bandwagon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroupDropdownAdapter extends RecyclerView.Adapter<GroupDropdownAdapter.GroupViewHolder> {
    private ArrayList<Group> groups;
    private GroupSelectedListener groupSelectedListener;

    public GroupDropdownAdapter(ArrayList<Group> groupsList){
        super();
        this.groups = groupsList;
    }

    public void setGroupSelectedListener(GroupDropdownAdapter.GroupSelectedListener groupSelectedListener){
        this.groupSelectedListener = groupSelectedListener;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return new GroupViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, final int position){
        final Group group = groups.get(position);
        holder.tvGroup.setText(group.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(groupSelectedListener != null){
                    groupSelectedListener.onGroupSelected(position, group);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    static class GroupViewHolder extends RecyclerView.ViewHolder{
        AppCompatTextView tvGroup;

        public GroupViewHolder(View itemView){
            super(itemView);
            tvGroup = itemView.findViewById(R.id.tvGroup);
        }
    }
    interface GroupSelectedListener{
        void onGroupSelected(int position, Group group);
    }
}

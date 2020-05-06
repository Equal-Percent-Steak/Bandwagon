package com.equalpercentsteak.bandwagon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GroupDropdownMenu extends PopupWindow {
    private Context context;
    private RecyclerView rvGroup;
    private GroupDropdownAdapter dropdownAdapter;

    public GroupDropdownMenu(Context context){
        super(context);
        this.context = context;
        setupView();
    }

    public void setGroupSelectedListener(GroupDropdownAdapter.GroupSelectedListener groupSelectedListener){
        dropdownAdapter.setGroupSelectedListener(groupSelectedListener);
    }

    private void setupView() {
        View view = LayoutInflater.from(context).inflate(R.layout.group_popup, null);

        rvGroup = view.findViewById(R.id.rvCategory);
        rvGroup.setHasFixedSize(true);
        rvGroup.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rvGroup.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));

        dropdownAdapter = new GroupDropdownAdapter(User.generateGroupList());
        rvGroup.setAdapter(dropdownAdapter);

        setContentView(view);
    }
}

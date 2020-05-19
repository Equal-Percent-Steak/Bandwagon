package com.equalpercentsteak.bandwagon;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/**
 * This class displays the individual groups in the correct format on the Groups page.
 */
public class GroupDisplayAdapter extends
        RecyclerView.Adapter<GroupDisplayAdapter.ViewHolder> {

    /**
     * The onGroupListener to be used with the onClick method
     */
    private OnGroupListener mOnGroupListener;

    /**
     * The ArrayList of groups in the adapter
     */
    private ArrayList<Group> mGroups;

    /**
     * The ArrayList of group names
     */
    private ArrayList<String> mGroupNames;

//    /**
//     * Constructs a GroupDisplayAdapter with a given ArrayList of groups and an onGroupListener
//     * @param groups the groups to be assigned to the adapter
//     * @param onGroupListener the onGroupListener to be used with the onClick method
//     */
//    public GroupDisplayAdapter(ArrayList<Group> groups, OnGroupListener onGroupListener){
//        mGroups = groups;
//        mOnGroupListener = onGroupListener;
//    }

    /**
     * Constructs a GroupDisplayAdapter with a given ArrayList of group names, an onGroupListener, and an unused string
     * @param groupNames The strings of the group names
     * @param onGroupListener the onGroupListener to be used with the onClick method
     * @param unused Used to create this as a separate constructor
     */
    public GroupDisplayAdapter(ArrayList<String> groupNames, OnGroupListener onGroupListener, String unused){
        mGroupNames = groupNames;
        mOnGroupListener = onGroupListener;
    }

    /**
     * Makes a viewHolder
     * @param parent the parent ViewGroup
     * @param viewType the int viewType of the ViewHolder
     * @return returns a ViewHolder object
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.groupdisplay, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(itemView, mOnGroupListener);
        return viewHolder;
    }

    /**
     * Assigns the correct information to each individual ViewHolder
     * @param viewHolder The viewHolder that the data is being set in
     * @param position the position in the array to set the data in the viewHolder
     */
    @Override
    public void onBindViewHolder(GroupDisplayAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        String group = mGroupNames.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.groupNameTextView;
        textView.setText(group);

    }

    /**
     * Gets the number of items in the adapter
     * @return the number of groups in the adapter
     */
    @Override
    public int getItemCount() {
        return mGroupNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /**
         * The TextView in the holder that holds the group name
         */
        private TextView groupNameTextView;
        /**
         * The OnGroupListener that works with the onClick method for selecting a group
         */
        OnGroupListener onGroupListener;

        /**
         * Constructs a ViewHolder with a given itemView and the onGroupListener
         * @param itemView The itemView for the viewHolder
         * @param onGroupListener The OnGroupListener that works with the onClick method for selecting a group
         */
        private ViewHolder(View itemView, OnGroupListener onGroupListener) {
            super(itemView);
            groupNameTextView = itemView.findViewById(R.id.group_name);
            this.onGroupListener=onGroupListener;

            itemView.setOnClickListener(this);
        }

        /**
         * Sets the onClick method for the individual viewHolder
         * @param v what the onClick method is selecting
         */
        @Override
        public void onClick(View v) {
            onGroupListener.onGroupClick(getAdapterPosition());
        }
    }

    /**
     * The interface for the onClick method
     */
    public interface OnGroupListener{
        void onGroupClick(int position);
    }


}

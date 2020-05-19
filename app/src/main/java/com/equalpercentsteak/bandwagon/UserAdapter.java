package com.equalpercentsteak.bandwagon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * This class displays the usernames on the class members screen.
 */
public class UserAdapter extends
        RecyclerView.Adapter<UserAdapter.ViewHolder> {

    /**
     *
     * @param parent the parent ViewGroup
     * @param viewType the int viewType
     * @return returns the ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.userdisplay, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    /**
     * Updates the contents of the recycler view to display the task Description
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        User user = mUsers.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.UserTextView;
        textView.setText(user.getUsername());
    }

    /**
     * Gets the number of users in the adapter
     * @return the number of users in the adapter
     */
    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * The TextView of each user
         */
        public TextView UserTextView;

        /**
         * Constructs a ViewHolder
         * @param itemView
         */
        public ViewHolder(View itemView) {
            super(itemView);
            UserTextView = (TextView)itemView.findViewById(R.id.user_name);
        }
    }

    /**
     * The list of users in the adapter
     */
    private List<User> mUsers;

    /**
     * Constructs a UserAdapter with a list of users
     * @param users the list of users to be added to the adapter
     */
    public UserAdapter(List<User> users){
        mUsers = users;
    }

}

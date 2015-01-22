package android.fmi.userinspector;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.List;

/**
 * Created by mmironov on 1/22/15.
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private List<ParseUser> users;

    public UsersAdapter(List<ParseUser> users) {
        this.users = users;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_item, viewGroup, false);

        final ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        final ParseUser user = users.get(i);

        viewHolder.usernameTextView.setText(user.getEmail());
        viewHolder.messagesCountTextVIew.setText("0");
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView usernameTextView;
        TextView messagesCountTextVIew;

        public ViewHolder(View view) {
            super(view);

            usernameTextView = (TextView) view.findViewById(R.id.usernameTextView);
            messagesCountTextVIew = (TextView) view.findViewById(R.id.messagesCountTextView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}

package tuannm.com.chatdemo.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import tuannm.com.chatdemo.R;
import tuannm.com.chatdemo.dto.UserDto;
import tuannm.com.chatdemo.model.User;

/**
 * Created by TNM on 2/18/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private List<UserDto> users;
    private LayoutInflater inflater;

    public UserAdapter(Context context, List<UserDto> users) {
        this.context = context;
        this.users = users;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_user, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bitmap bitmap = getBitmapFromByteArray(users.get(position).getBytes());
        holder.imgAvatar.setImageBitmap(bitmap);

        holder.tvUsername.setText(users.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imgAvatar;
        private TextView tvUsername;

        public ViewHolder(View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
            tvUsername = itemView.findViewById(R.id.tv_user);
        }
    }

    private Bitmap getBitmapFromByteArray(byte[] bytes) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bitmap;
    }
}

package com.example.uibestpractice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.Viewholder> {

    private List<Message> mMessageList;

    static class Viewholder extends RecyclerView.ViewHolder {

        LinearLayout leftLayout;

        LinearLayout rightLayout;

        TextView leftMessage;

        TextView rightMessage;

        public Viewholder(View view) {
            super(view);
            leftLayout = view.findViewById(R.id.left_layout);
            rightLayout = view.findViewById(R.id.right_layout);
            leftMessage = view.findViewById(R.id.message_received);
            rightMessage = view.findViewById(R.id.message_sent);

        }

    }

    public MessageAdapter(List<Message> messageList) {
        mMessageList = messageList;
    }

    @NonNull
    @Override
    public MessageAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item,
                parent, false);
        Viewholder holder = new Viewholder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.Viewholder holder, int position) {
        Message message = mMessageList.get(position);
        if(message.getType() == Message.TYPE_RECEIVED) {
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMessage.setText(message.getContent());
        } else if (message.getType() == Message.TYPE_SENT){
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightMessage.setText(message.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }
}

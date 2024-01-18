package com.example.uibestpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Message> messageList = new ArrayList<>();

    private EditText inputText;

    private Button send;

    private RecyclerView messageRecyclerView;

    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMessages();
        send = findViewById(R.id.send);
        inputText = findViewById(R.id.input_text);
        messageRecyclerView = findViewById(R.id.message_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        messageRecyclerView.setLayoutManager(manager);
        adapter = new MessageAdapter(messageList);
        messageRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if(!"".equals(content)) {

                    Message message = new Message(content, Message.TYPE_SENT);
                    messageList.add(message);
                    adapter.notifyItemChanged(messageList.size() - 1);
                    messageRecyclerView.scrollToPosition(messageList.size() - 1);
                    inputText.setText("");

                }
            }
        });
    }

    private void initMessages() {
        Message message1 = new Message("Hello guy.", Message.TYPE_RECEIVED);
        messageList.add(message1);
        Message message2 = new Message("Hello. Who's that?", Message.TYPE_SENT);
        messageList.add(message2);
        Message message3 = new Message("This is Tom. Nice to talking to you",
                Message.TYPE_RECEIVED);
        messageList.add(message3);
    }
}
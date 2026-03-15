package edu.jsu.mcis.cs408.memo_pad;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMemo;
    private Button buttonAddMemo;
    private Button buttonDeleteMemo;
    private RecyclerView recyclerViewMemos;

    private MemoDatabaseHandler dbHandler;
    private MemoRecyclerViewAdapter adapter;
    private List<Memo> memoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextMemo = findViewById(R.id.editTextMemo);
        buttonAddMemo = findViewById(R.id.buttonAddMemo);
        buttonDeleteMemo = findViewById(R.id.buttonDeleteMemo);
        recyclerViewMemos = findViewById(R.id.recyclerViewMemos);

        dbHandler = new MemoDatabaseHandler(this);

        recyclerViewMemos.setLayoutManager(new LinearLayoutManager(this));

        memoList = dbHandler.getAllMemosAsList();
        adapter = new MemoRecyclerViewAdapter(memoList);
        recyclerViewMemos.setAdapter(adapter);

        buttonAddMemo.setOnClickListener(v -> {
            String memoText = editTextMemo.getText().toString().trim();

            if (!memoText.isEmpty()) {
                Memo memo = new Memo();
                memo.setMemo(memoText);

                dbHandler.addMemo(memo);

                editTextMemo.setText("");

                updateRecyclerView();
            }
        });

        buttonDeleteMemo.setOnClickListener(v -> {
            // Leave this empty for now if this is still Lab 4A.
            // Delete functionality can be added in Lab 4B.
        });
    }

    private void updateRecyclerView() {
        memoList = dbHandler.getAllMemosAsList();
        adapter = new MemoRecyclerViewAdapter(memoList);
        recyclerViewMemos.setAdapter(adapter);
    }
}
package edu.jsu.mcis.cs408.memo_pad;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.jsu.mcis.cs408.memo_pad.controller.MemoPadController;
import edu.jsu.mcis.cs408.memo_pad.model.Memo;
import edu.jsu.mcis.cs408.memo_pad.model.MemoPadModel;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMemo;
    private Button buttonAddMemo;
    private Button buttonDeleteMemo;
    private RecyclerView recyclerViewMemos;

    private MemoPadController controller;
    private Integer selectedMemoId = null;

    private final MemoPadItemClickHandler itemClick = new MemoPadItemClickHandler();

    public MemoPadItemClickHandler getItemClick() {
        return itemClick;
    }

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

        controller = new MemoPadController(new MemoPadModel(this));

        recyclerViewMemos.setLayoutManager(new LinearLayoutManager(this));

        updateRecyclerView();

        buttonAddMemo.setOnClickListener(v -> {
            String memoText = editTextMemo.getText().toString().trim();

            if (!memoText.isEmpty()) {
                controller.createMemo(memoText);
                editTextMemo.setText("");
                selectedMemoId = null;
                updateRecyclerView();
            } else {
                Toast.makeText(this, "Enter a memo", Toast.LENGTH_SHORT).show();
            }
        });

        buttonDeleteMemo.setOnClickListener(v -> {
            if (selectedMemoId != null) {
                controller.deleteMemo(selectedMemoId);
                selectedMemoId = null;
                updateRecyclerView();
            } else {
                Toast.makeText(this, "Select a memo first", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateRecyclerView() {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, controller.listMemos());
        recyclerViewMemos.setAdapter(adapter);
    }

    public class MemoPadItemClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int position = recyclerViewMemos.getChildLayoutPosition(v);
            RecyclerViewAdapter adapter = (RecyclerViewAdapter) recyclerViewMemos.getAdapter();

            if (adapter != null && position != RecyclerView.NO_POSITION) {
                Memo memo = adapter.getItem(position);
                selectedMemoId = memo.getId();
                Toast.makeText(v.getContext(),
                        "Selected memo ID: " + selectedMemoId,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
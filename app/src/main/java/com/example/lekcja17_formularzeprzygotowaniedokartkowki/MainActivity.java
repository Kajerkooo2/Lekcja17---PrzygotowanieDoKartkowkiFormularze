package com.example.lekcja17_formularzeprzygotowaniedokartkowki;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTitle, editAuthor, editPrice;
    private Spinner spinnerGenre;
    private Switch switchNew;
    private SeekBar seekBarPromo;
    private TextView textPromoValue;
    private CheckBox checkPaper, checkMobi, checkAudiobook, checkPdf;
    private RadioButton radio18, radio12, radio9, radio0;
    private RadioGroup radioGroupAge, radioGroupAge2;
    private Button buttonSubmit;
    private ListView listViewBooks;

    private List<Book> bookList = new ArrayList<>();
    private BookAdapter bookAdapter;


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


        initializeViews();

        // SeekBar listener
        seekBarPromo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textPromoValue.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Button listener
        buttonSubmit.setOnClickListener(v -> submitForm());

        // Adapter dla ListView
        bookAdapter = new BookAdapter(this, bookList);
        listViewBooks.setAdapter(bookAdapter);
    }
    private void initializeViews() {
        editTitle = findViewById(R.id.etTytul);
        editAuthor = findViewById(R.id.etAutor);
        editPrice = findViewById(R.id.etCena);
        spinnerGenre = findViewById(R.id.spinnerGenre);
        switchNew = findViewById(R.id.switchCzNowa);
        seekBarPromo = findViewById(R.id.seekBarPromocja);
        textPromoValue = findViewById(R.id.tvPromoPercent);
        checkPaper = findViewById(R.id.cbPapier);
        checkMobi = findViewById(R.id.cbMobi);
        checkAudiobook = findViewById(R.id.cbAudiobook);
        checkPdf = findViewById(R.id.cbPdf);
        radioGroupAge = findViewById(R.id.radioGroupAge);
        radioGroupAge2 = findViewById(R.id.radioGroupAge2);
        radio18 = findViewById(R.id.radio18);
        radio12 = findViewById(R.id.radio12);
        radio9 = findViewById(R.id.radio9);
        radio0 = findViewById(R.id.radio0);
        buttonSubmit = findViewById(R.id.btnWyslij);
        listViewBooks = findViewById(R.id.lvBooks);
    }

    private void submitForm(){
        if (editTitle.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Wpisz tytuł", Toast.LENGTH_SHORT).show();
            return;
        }
        if (editAuthor.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Wpisz autora", Toast.LENGTH_SHORT).show();
            return;
        }
        if (editPrice.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Wpisz cenę", Toast.LENGTH_SHORT).show();
            return;
        }


        String title = editTitle.getText().toString();
        String author = editAuthor.getText().toString();
        String genre = spinnerGenre.getSelectedItem().toString();
        boolean isNew = switchNew.isChecked();
        double price = Double.parseDouble(editPrice.getText().toString());
        int promo = seekBarPromo.getProgress();

        StringBuilder available = new StringBuilder();
        if (checkPaper.isChecked()) available.append("Papier ");
        if (checkMobi.isChecked()) available.append("Mobi ");
        if (checkAudiobook.isChecked()) available.append("Audiobook ");
        if (checkPdf.isChecked()) available.append("PDF ");

        String ageCategory = "";
        if (radio18.isChecked()) ageCategory = "18+";
        else if (radio12.isChecked()) ageCategory = "12+";
        else if (radio9.isChecked()) ageCategory = "9+";
        else if (radio0.isChecked()) ageCategory = "0+";

        Book book = new Book(title, author, genre, isNew, price, promo,
                available.toString().trim(), ageCategory);

        bookList.add(0, book);
        bookAdapter.notifyDataSetChanged();

        clearForm();

        Toast.makeText(this, "Dodane", Toast.LENGTH_SHORT).show();
    }

    private void clearForm() {
        editTitle.setText("");
        editAuthor.setText("");
        editPrice.setText("");
        spinnerGenre.setSelection(0);
        switchNew.setChecked(false);
        seekBarPromo.setProgress(0);
        textPromoValue.setText("0%");
        checkPaper.setChecked(false);
        checkMobi.setChecked(false);
        checkAudiobook.setChecked(false);
        checkPdf.setChecked(false);
        radioGroupAge.clearCheck();
        radioGroupAge2.clearCheck();
    }
}
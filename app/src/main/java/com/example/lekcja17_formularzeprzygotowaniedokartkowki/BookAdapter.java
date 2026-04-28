package com.example.lekcja17_formularzeprzygotowaniedokartkowki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);

        String info = String.format(
                "%s\nAutor: %s\nGatunek: %s\nCena: %.2f zł (Promo: %d%%)\nNowa: %s\nDostępna na: %s\nKategoria: %s",
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getPrice(),
                book.getPromo(),
                book.isNew() ? "Tak" : "Nie",
                book.getAvailable(),
                book.getAgeCategory()
        );

        textView.setText(info);

        return convertView;
    }

}

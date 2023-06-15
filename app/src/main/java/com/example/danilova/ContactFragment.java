package com.example.danilova;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ContactFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento desde el archivo XML
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        // Obtener la referencia a la imagen en el diseño
        ImageView imageView = view.findViewById(R.id.socialIcon);
        ImageView imageViewwhat = view.findViewById(R.id.sociawhat);



        // Agregar un clic a la imagen
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir el enlace cuando se hace clic en la imagen
                Uri uri = Uri.parse("https://www.facebook.com/profile.php?id=100064327526051");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }


        });
        imageViewwhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=6333366195");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        return view;
    }
}


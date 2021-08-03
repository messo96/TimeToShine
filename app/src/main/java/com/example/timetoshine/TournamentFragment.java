package com.example.timetoshine;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class TournamentFragment extends Fragment {

    //EDIT HERE TO CHANGE DOC
    private static final String DOC_URL = "https://docs.google.com/spreadsheets/d/e/2PACX-1vTciRTDyHiIj7F6o9nLK5MT1hdloWajXHHOtP5RmnrCXdmn812271fpEfLLhlF6wCkVKB26apjLH_9c/pubhtml";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tournament_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebView webView = view.findViewById(R.id.web_view_tournament);
        webView.setInitialScale(200);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(false);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        String data_html = "<!DOCTYPE html><html> <head> <meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"target-densitydpi=high-dpi\" /> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> <link rel=\"stylesheet\" media=\"screen and (-webkit-device-pixel-ratio:1.5)\" href=\"hdpi.css\" /></head> <body style=\"background:white;margin:0 0 0 0; padding:0 0 0 0;\"> <iframe style=\"background:white;\" width=' "+width+"' height='"+height+"' src=\""+DOC_URL+"\" frameborder=\"0\"></iframe> </body> </html> ";

        webView.loadDataWithBaseURL("https://docs.google.com", data_html, "text/html", "UTF-8", null);


        view.findViewById(R.id.btn_reload_schedule).setOnClickListener(l ->{
            webView.reload();
        });

        TextView textView_regolamento = view.findViewById(R.id.textview_regolamento);
        try {
            textView_regolamento.setText(readFromFile());
        } catch (IOException e) {
            e.printStackTrace();
            textView_regolamento.setText("C'è stato un errore nel recuperare il regolamento! Potete picchiare Gala intanto");
        }
    }

    private String readFromFile() throws IOException {
        String regolamento = "";
        String string = "";
        InputStream is = this.getResources().openRawResource(R.raw.regolamento);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String day = "";
        while ((string = reader.readLine()) != null) {
            regolamento += (string + "\n");
        }
        is.close();

        return regolamento;
    }

}

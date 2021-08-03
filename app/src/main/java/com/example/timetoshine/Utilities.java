package com.example.timetoshine;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Utilities {

    public static void insertFragment(AppCompatActivity activity, Fragment fragment, String tag, int idContainer) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container_view with this fragment,
        transaction.replace(idContainer, fragment, tag);

        //add the transaction to the back stack so the user can navigate back
        if( !(fragment instanceof HomeFragment) && !(fragment instanceof TournamentFragment) && !(fragment instanceof SOTGFragment))
            transaction.addToBackStack(tag);


        // Commit the transaction
        transaction.commit();
    }
}

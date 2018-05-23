package cr.ac.una.escinf.proyectoaerolinea.activities;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cr.ac.una.escinf.proyectoaerolinea.R;

public class BookFlightActivity extends BaseActivity {

    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_flight);

    }
}

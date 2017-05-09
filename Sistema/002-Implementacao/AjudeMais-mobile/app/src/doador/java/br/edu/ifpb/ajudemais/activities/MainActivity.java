package br.edu.ifpb.ajudemais.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.LoginManager;

import br.edu.ifpb.ajudemais.R;
import br.edu.ifpb.ajudemais.TabFragmentMain;
import br.edu.ifpb.ajudemais.domain.Conta;
import br.edu.ifpb.ajudemais.dto.LatLng;
import br.edu.ifpb.ajudemais.storage.SharedPrefManager;
import br.edu.ifpb.ajudemais.utils.ImagePicker;


/**
 * <p>
 * <b>MainActivity</b>
 * </p>
 * <p>
 * Activity para controlar Login.
 * <p>
 * <p>
 * </p>
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 * @author <a href="https://github.com/JoseRafael97">Rafael Feitosa</a>
 */
public class MainActivity extends AbstractActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private FloatingActionButton fab;


    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPrefManager = new SharedPrefManager(this);

        initGoogleAPIClient();
        checkPermissions();

        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
        findViewById(R.id.containerView).setVisibility(View.GONE);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new TabFragmentMain()).commit();

        init();
        setUpAccount();
        setUpToggle();
        setupNavDrawer();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mLastLocation == null) {
                    mLastLocation = getLocation();
                }
                if (mLastLocation != null) {
                    sharedPrefManager.storeLatLng(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()));
                }
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MainSearchActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        new LoadingCampanhasDoacoesTask().execute();
    }


    /**
     * Set as informações do usuário logado no app
     */
    protected void setUpAccount() {
        View hView = mNavigationView.getHeaderView(0);
        profilePhoto = (ImageView) hView.findViewById(R.id.photoProfile);
        tvUserName = (TextView) hView.findViewById(R.id.tvUserNameProfile);
        tvEmail = (TextView) hView.findViewById(R.id.tvEmailProfile);

        conta = (Conta) getIntent().getSerializableExtra("Conta");
        if (conta != null) {
            tvUserName.setText(conta.getUsername() != null ? conta.getUsername() : Profile.getCurrentProfile().getName());
            tvEmail.setText(conta.getEmail() != null ? conta.getEmail() : "Nenhum e-mail informado");
        }
        Bitmap bitmap = capturePhotoUtils.loadImageFromStorage();

        if (bitmap != null) {
            profilePhoto.setImageBitmap(bitmap);
        }

        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chooseImageIntent = ImagePicker.getPickImageIntent(getApplicationContext());
                startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);
            }
        });
    }

    /**
     * @param menuItem
     */
    private void onNavDrawerItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_config_conta:
                break;
            case R.id.nav_notificacoes:
                break;
            case R.id.nav_sair:
                if (AccessToken.getCurrentAccessToken() != null) {
                    LoginManager.getInstance().logOut();
                    goToLoginScreen();
                    break;
                } else {
                    SharedPrefManager.getInstance(this).clearSharedPrefs();
                    System.out.println(capturePhotoUtils.deleteImageProfile());
                    goToLoginScreen();
                    break;
                }
        }
    }

    private void goToLoginScreen() {
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    /**
     * Set Configuração para Navegation Drawer
     */
    protected void setupNavDrawer() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            if (mNavigationView != null && mDrawerLayout != null) {

                mNavigationView.setNavigationItemSelectedListener(
                        new NavigationView.OnNavigationItemSelectedListener() {
                            @Override
                            public boolean onNavigationItemSelected(MenuItem menuItem) {
                                menuItem.setChecked(true);
                                mDrawerLayout.closeDrawers();
                                onNavDrawerItemSelected(menuItem);
                                return true;
                            }
                        });
            }
        }
    }


    /**
     *
     */
    private class LoadingCampanhasDoacoesTask extends AsyncTask<Void, Void, String> {

        /**
         * @param params
         * @return
         */
        @Override
        protected String doInBackground(Void... params) {
            return null;
        }

        /**
         * @param message
         */
        @Override
        protected void onPostExecute(String message) {
            findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            findViewById(R.id.containerView).setVisibility(View.VISIBLE);
            Toast.makeText(getApplication(), "CARREGADO", Toast.LENGTH_LONG).show();
        }
    }
}
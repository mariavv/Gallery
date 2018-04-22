package com.maria.gallery.ui;

import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.maria.gallery.R;

import com.yandex.authsdk.YandexAuthException;
import com.yandex.authsdk.YandexAuthOptions;
import com.yandex.authsdk.YandexAuthSdk;
import com.yandex.authsdk.YandexAuthToken;

import java.util.Arrays;
import java.util.Locale;

import io.reactivex.annotations.NonNull;

import static java.security.AccessController.getContext;

public class AuthActivity extends AppCompatActivity {

    private static final int REQUEST_LOGIN_SDK = 1;

    private EditText editUid, editLoginHint;
    private TextView tokenLabel;
    private TextView jwtLabel;
    private View jwtContainer;
    private YandexAuthSdk sdk;
    private YandexAuthToken yandexAuthToken;
    private String jwt;

    public static final Intent start(Context context) {
        return new Intent(context, AuthActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        final View loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(v -> {
            final String uidStr = editUid.getText().toString();
            final String loginHint = editLoginHint.getText().toString();

            final Intent intent;
            if (!TextUtils.isEmpty(uidStr)) {
                final long uid = Long.parseLong(uidStr);
                intent = sdk.createLoginIntent(this, null/*, uid, loginHint*/);
            } else {
                intent = sdk.createLoginIntent(this, null);
            }
            startActivityForResult(intent, REQUEST_LOGIN_SDK);

        });
        final View jwtButton = findViewById(R.id.jwt);
        //jwtButton.setOnClickListener(v -> getJwt());

        tokenLabel = (TextView) findViewById(R.id.status_label);
        jwtLabel = (TextView) findViewById(R.id.jwt_label);
        jwtContainer = findViewById(R.id.jwt_container);
        editUid = findViewById(R.id.edit_uid);
        editLoginHint = findViewById(R.id.edit_login_hint);

        sdk = new YandexAuthSdk(/*getContext(),*/ new YandexAuthOptions(this, true));

        if (yandexAuthToken != null) {
            onTokenReceived(yandexAuthToken);
        }
        if (jwt != null) {
            onJwtReceived(jwt);
        }
        //getAccounts();

        //setResult(RESULT_OK, new Intent());
        //finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        if (requestCode == REQUEST_LOGIN_SDK) {
            try {
                final YandexAuthToken yandexAuthToken = sdk.extractToken(resultCode, data);
                if (yandexAuthToken != null) {
                    onTokenReceived(yandexAuthToken);

                    //String token = yandexAuthToken.getValue();
                    setResult(RESULT_OK, new Intent());
                    finish();
                }
            } catch (YandexAuthException e) {
                tokenLabel.setText(e.getLocalizedMessage());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void onTokenReceived(@NonNull YandexAuthToken yandexAuthToken) {
        this.yandexAuthToken = yandexAuthToken;
        tokenLabel.setText(yandexAuthToken.toString());
        jwtContainer.setVisibility(View.VISIBLE);
    }

    private void onJwtReceived(@NonNull String jwt) {
        this.jwt = jwt;
        jwtLabel.setText(jwt);
    }

    private void getJwt() {
        /*final DialogFragment dialog = new ProgressDialogFragment();
        dialog.setCancelable(false);
        dialog.show(getFragmentManager(), ProgressDialogFragment.TAG);

        assert yandexAuthToken != null;

        new Thread(() -> {
            try {
                final String jwt = sdk.getJwt(yandexAuthToken);
                this.runOnUiThread(() -> {
                    onJwtReceived(jwt);
                    dismissProgress();
                });
            } catch (YandexAuthException e) {
                this.runOnUiThread(() -> {
                    jwtLabel.setText(Arrays.toString(e.getErrors()));
                    dismissProgress();
                });
            }

        }).start();*/
    }

    /*private void getAccounts() {
        final DialogFragment dialog = new ProgressDialogFragment();
        dialog.setCancelable(false);
        dialog.show(getFragmentManager(), ProgressDialogFragment.TAG);

        new Thread(() -> {
            try {
                final List<YandexAuthAccount> accounts = sdk.getAccounts();
                this.runOnUiThread(() -> {
                    Toast.makeText(
                            getContext(),
                            String.format(Locale.getDefault(), "Available %d accounts", accounts.size()),
                            Toast.LENGTH_SHORT
                    ).show();
                    dismissProgress();
                });
            } catch (final YandexAuthSecurityException | YandexAuthInteractionException e) {
                this.runOnUiThread(() -> {
                    jwtLabel.setText(Arrays.toString(e.getErrors()));
                    dismissProgress();
                });
            }

        }).start();
    }*/

    /*private void dismissProgress() {
        final Fragment dialogFragment = getFragmentManager().findFragmentByTag(ProgressDialogFragment.TAG);
        if (dialogFragment != null) {
            ((DialogFragment) dialogFragment).dismiss();
        }
    }

    public static class ProgressDialogFragment extends DialogFragment {

        private static final String TAG = ProgressDialogFragment.class.getCanonicalName();

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final ProgressDialog dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Waiting");
            dialog.setCancelable(false);
            return dialog;
        }
    }*/
}

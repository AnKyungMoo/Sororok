package com.nexters.sororok.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.nexters.sororok.R;
import com.nexters.sororok.asynctask.LoginAsyncTask;
import com.nexters.sororok.asynctask.NaverTokenTask;
import com.nexters.sororok.model.LoginRequestModel;
import com.nexters.sororok.model.LoginResponseModel;
import com.nexters.sororok.service.LoginService;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

import java.lang.ref.WeakReference;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity{

    // google
    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 9001;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    //naver
    public static OAuthLogin mOAuthLoginModule;

    // kakao
    private SessionCallback kakaoCallback;

    // view
    ImageButton googleButton;
    ImageButton naverButton;
    ImageButton kakaoButton;

    // 변수
    static String id;

    /*TODO: 임시로 메인으로 가는 버튼이니 키 해시 문제가 해결되면 제거하자*/
    Button tempButton;

    /*TODO: 카카오 중복 success 해결방안을 찾을 때까지 임시 방편 boolean*/
    boolean isSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.sororok", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // google
        mAuth = FirebaseAuth.getInstance();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // naver
        mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(
                LoginActivity.this,
                "mmelSUnAaeJAGWhIYNQG",
                "nVlJHMh6vf",
                "Sororok"
        );

        googleButton = findViewById(R.id.google_button);
        naverButton = findViewById(R.id.naver_button);
        kakaoButton = findViewById(R.id.kakao_button);
        tempButton = findViewById(R.id.temp_main_button);

        isSuccess = false;

        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        naverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOAuthLoginModule.startOauthLoginActivity(LoginActivity.this, mOAuthLoginHandler);
            }
        });

        kakaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kakaoLogin();
            }
        });

        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, TestActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //Log.d("StartEmail: ", currentUser.getEmail());
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // naver
    private OAuthLoginHandler mOAuthLoginHandler = new NaverLoginHandler(this);

    // kakao
    private void kakaoLogin() {
        kakaoCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(kakaoCallback);
        Session.getCurrentSession().checkAndImplicitOpen();
        Session.getCurrentSession().open(AuthType.KAKAO_TALK_EXCLUDE_NATIVE_LOGIN, LoginActivity.this);
   }

    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            Log.d("SessionOpen" , "세션 오픈됨");
            // 사용자 정보를 가져옴, 회원가입 미가입시 자동가입 시킴
            kakaoRequestMe();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Log.d("SessionFail" , exception.getMessage());
            }
        }
    }

    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
    protected void kakaoRequestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                int ErrorCode = errorResult.getErrorCode();
                int ClientErrorCode = -777;

                if (ErrorCode == ClientErrorCode) {
                    Toast.makeText(getApplicationContext(), "카카오톡 서버의 네트워크가 불안정합니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("KakaoFailure" , "오류로 카카오로그인 실패");
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.d("SessionClosed" , "오류로 카카오로그인 실패 ");
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                //String profileUrl = userProfile.getProfileImagePath();
                /*TODO: 카카오 중복 success 다른 해결방안을 찾아보자..*/
                if (!isSuccess) {
                    isSuccess = true;
                    String userName = userProfile.getNickname();

                    Log.d("kakaoProfile: ", userProfile.getId() + "");
                    Log.d("kakaoProfile: ", userProfile.getUUID() + "");
                    Log.d("kakaoName: ", userName);

                    Retrofit loginRetrofit = new Retrofit.Builder()
                            .baseUrl(LoginService.BASE_URL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    LoginService loginService = loginRetrofit.create(LoginService.class);

                    Call<LoginResponseModel> loginCall = loginService.login(new LoginRequestModel("1", "899582853"));

                    callRetrofit();

                    if (!id.equals("-1")) {
                        Intent intent = new Intent(LoginActivity.this, TestActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Intent intent = new Intent(LoginActivity.this, LoginInfoActivity.class);

                        intent.putExtra("loginType", "kakao");
                        intent.putExtra("kakaoName", userName);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onNotSignedUp() {
                // 자동가입이 아닐경우 동의창
                Log.d("SignedUp", "");
            }
        });
    }

    // handler를 그냥 사용하면 메모리 누수가 있을 수 있으니 static으로 만들어서 사용
    private static class NaverLoginHandler extends OAuthLoginHandler {
        private final WeakReference<LoginActivity> mActivity;

        private NaverLoginHandler(LoginActivity activity) {
            mActivity = new WeakReference<LoginActivity>(activity);
        }


        @Override
        public void run(boolean success) {
            LoginActivity activity = mActivity.get();

            if (success) {
                String accessToken = mOAuthLoginModule.getAccessToken(activity);
                String refreshToken = mOAuthLoginModule.getRefreshToken(activity);
                long expiresAt = mOAuthLoginModule.getExpiresAt(activity);
                String tokenType = mOAuthLoginModule.getTokenType(activity);

                NaverTokenTask tokenTask = new NaverTokenTask();

                tokenTask.execute(accessToken);

                try {
                    String token = tokenTask.get();

                    Log.d("naverJSON: ", token);

                    callRetrofit();

                    if (!id.equals("-1")) {
                        Intent intent = new Intent(activity, TestActivity.class);
                        activity.startActivity(intent);
                        activity.finish();
                    } else {
                        Intent intent = new Intent(activity, LoginInfoActivity.class);

                        intent.putExtra("loginType", "naver");
                        intent.putExtra("naverToken", token);

                        activity.startActivity(intent);
                        activity.finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                String errorCode = mOAuthLoginModule.getLastErrorCode(activity).getCode();
                String errorDesc = mOAuthLoginModule.getLastErrorDesc(activity);
                Toast.makeText(activity, "errorCode:" + errorCode
                        + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // kakao
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data))
            return;

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            assert user != null;

                            Log.d("googleUID", user.getUid());

                            callRetrofit();

                            if (!id.equals("-1")) {
                                Intent intent = new Intent(LoginActivity.this, TestActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent = new Intent(LoginActivity.this, LoginInfoActivity.class);
                                intent.putExtra("loginType", "google");
                                intent.putExtra("googleName", user.getDisplayName());
                                intent.putExtra("googleEmail", user.getEmail());
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            Log.d("NullEmail: ", "");
                        }

                        // ...
                    }
                });
    }

    // 레트로핏을 이용하여서 서버에서 기존 로그인 정보 획득
    private static void callRetrofit() {

        /* 비동기로 처리하면 id를 가져오는거보다 다른 작업이 먼저 처리되는 경우가 발생되므로
         * 동기로 처리, 네트워크 통신 문제때문에 asyncTask에서 작업
         */
        LoginAsyncTask loginTask = new LoginAsyncTask();

        loginTask.execute(new LoginRequestModel("1","899582853"));

        try {
            LoginResponseModel loginResponseModel = loginTask.get();
            id = loginResponseModel.getId();
            Log.d("checkId", id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /* 비동기로 처리할 때
        loginCall.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                Log.d("start!!!", "start");
                Log.d("response", response.code() + "");

                LoginResponseModel loginResponseModel = response.body();

                Log.d("loginBody: ", loginResponseModel.getId());
                id = loginResponseModel.getId();
                Log.d("loginEmail: ", loginResponseModel.getEmail());
                Log.d("phone: ", loginResponseModel.getPhone());
                Log.d("name: ", loginResponseModel.getName());
                Log.d("end!!!", "end");
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Log.d("Splash Retrofit: ", "Fail");
            }
        });
        */
    }
}

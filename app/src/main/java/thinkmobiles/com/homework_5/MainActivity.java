package thinkmobiles.com.homework_5;

import android.app.Activity;
import android.os.Bundle;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button button_call;
    Button button_send;
    EditText edit_text_name;
    EditText edit_text_topic;
    EditText edit_text_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_call = (Button) findViewById(R.id.button_call);
        button_send = (Button) findViewById(R.id.button_send);
        edit_text_name = (EditText) findViewById(R.id.edit_text_name);
        edit_text_topic = (EditText) findViewById(R.id.edit_text_topic);
        edit_text_message = (EditText) findViewById(R.id.edit_text_message);
    }

    public void onClick(View v) {
        String mail_name = edit_text_name.getText().toString();
        String mail_subject = edit_text_topic.getText().toString();
        String mail_text = edit_text_message.getText().toString();

        switch (v.getId()) {
            case R.id.button_send:
                if (mail_name.equals("")&&mail_subject.equals("")&&mail_text.equals("")){
                    Toast.makeText(MainActivity.this, Constants.CORRECT_EMAIL, Toast.LENGTH_SHORT).show();
                    if (isCorrectEmail(mail_name)) {
                        Intent sendIntent = new Intent(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_EMAIL, mail_name);
                        sendIntent.putExtra(Intent.EXTRA_SUBJECT, mail_subject);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, mail_text);
                        sendIntent.setType("message/rfc822");
                        startActivity(Intent.createChooser(sendIntent, Constants.CHOOSE_APP));
                } else  {
                    Toast.makeText(MainActivity.this, Constants.INCORRECT_EMAIL, Toast.LENGTH_SHORT).show();
                    edit_text_name.setText("");
                    }
                } else { Toast.makeText(MainActivity.this, Constants.FILL_EMPTY_FIELDS, Toast.LENGTH_SHORT).show();
                    edit_text_name.setText("");
                }
                break;

            case R.id.button_call:

                    Intent callIntent = new Intent();
                    Uri uri = Uri.parse(Constants.TEL_TO_CALL);
                    callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(uri);
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    }
                    startActivity(callIntent);
                    break;
                }
        }
    private static boolean isCorrectEmail(String email) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
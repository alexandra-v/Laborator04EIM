package ro.pub.cs.systems.eim.lab04.contactsmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.Serializable;
import java.util.ArrayList;


public class ContactsManagerActivity extends AppCompatActivity {
    LinearLayout container;
    EditText name, phone, email, address, jobTitle, company, website, im;

    class Listener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.button){
                Button b = (Button)view;
                String buttonText = b.getText().toString();
                if (buttonText.equals("Show Additional Fields")) {
                    container.setVisibility(View.VISIBLE);
                    b.setText("Hide Additional Details");
                }
                if (buttonText.equals("Hide Additional Details")) {
                    container.setVisibility(View.GONE);
                    b.setText("Show Additional Fields");
                }
            }
            else if(view.getId() == R.id.save){
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                if (name != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name.getText().toString());
                }
                if (phone != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE,  phone.getText().toString());
                }
                if (email != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email.getText().toString());
                }
                if (address != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address.getText().toString());
                }
                if (jobTitle != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle.getText().toString());
                }
                if (company != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company.getText().toString());
                }
                ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
                if (website != null) {
                    ContentValues websiteRow = new ContentValues();
                    websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                    websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, String.valueOf(website));
                    contactData.add(websiteRow);
                }
                if (im != null) {
                    ContentValues imRow = new ContentValues();
                    imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                    imRow.put(ContactsContract.CommonDataKinds.Im.DATA, String.valueOf(im));
                    contactData.add(imRow);
                }
                intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
                startActivity(intent);
            }
            else if (view.getId() == R.id.cancel){
                finish();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);
        Button btn = findViewById(R.id.button);
        Button save = findViewById(R.id.save);
        Button cancel = findViewById(R.id.cancel);
        container = findViewById(R.id.container);
        btn.setOnClickListener(new Listener());

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        address = findViewById(R.id.addres);
        phone = findViewById(R.id.phone);
        company = findViewById(R.id.company);
        website = findViewById(R.id.web);
        im = findViewById(R.id.im);
        save.setOnClickListener(new Listener());
        cancel.setOnClickListener(new Listener());
    }
}

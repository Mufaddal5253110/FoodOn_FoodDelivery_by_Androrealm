package com.food_on.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.food_on.app.R;
import com.food_on.app.ReusableCode.ReusableCodeForAll;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.HashMap;

public class Registeration extends AppCompatActivity {

    String[] Maharashtra = {"Mumbai", "Pune", "Aurangabad"};
    String[] Gujarat = {"Ahemdabad", "Rajkot", "Surat"};


    String[] Mumbai = {"Churchgate", "Marine Lines", "Charni Road", "Grant Road", "Mumbai Central", "Mahalakshmi", "Lower Parel", "Prabhadevi",
            "Dadar", "Matunga", "Mahim", "Bandra", "Khar", "Santacruz", "Vile Parle", "Andheri", "Jogeshwari", "Ram Mandir",
            "Goregaon", "Malad", "Kandivai", "Borivali", "Dahisar", "MiraRoad", "Bhayander", "Naigaon", "Vasai Road", "Nalla Sopara", "Virar"};


    String[] Pune = {"Hinjewadi", "Wagholi", " Ambegaon", "Undri", "Katraj"};
    String[] Aurangabad = {"Aarif Colony", "Baiji Pura", "Balaji Nagar", "Angoori Bagh"};

    String[] Ahemdabad={"Mani Nagar","Thaltej","Prahlad Nagar","Gandhinagar"};
    String[] Surat={"Agnovad","Akoti","Amroli","Athwalines"};
    String[] Rajkot={"Kalawad Road","Astron chowk","Kotecha chowk","Trikon bag"};

    TextInputLayout fname, lname, localadd, emaill, pass, cmpass, Mobileno;
    Spinner statespin, City, Suburban;
    Button Signin, Email, Phone;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String statee;
    String cityy;
    String suburban;
    String email;
    String password;
    String firstname;
    String lastname;
    String Localaddress;
    String confirmpass;
    String mobileno;
    String role = "Customer";
    CountryCodePicker Cpp;
    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        try {
            mDialog = new ProgressDialog(Registeration.this);
            mDialog.setMessage("Registering please wait...");
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(false);
            fname = (TextInputLayout) findViewById(R.id.Fname);
            lname = (TextInputLayout) findViewById(R.id.Lname);
            localadd = (TextInputLayout) findViewById(R.id.Localaddress);
            emaill = (TextInputLayout) findViewById(R.id.Emailid);
            pass = (TextInputLayout) findViewById(R.id.Password);
            cmpass = (TextInputLayout) findViewById(R.id.confirmpass);
            Signin = (Button) findViewById(R.id.button);
            statespin = (Spinner) findViewById(R.id.Statee);
            City = (Spinner) findViewById(R.id.Citys);
            Suburban = (Spinner) findViewById(R.id.Suburban);
            Mobileno = (TextInputLayout) findViewById(R.id.Mobilenumber);
            Cpp = (CountryCodePicker) findViewById(R.id.CountryCode);
            Email = (Button) findViewById(R.id.emaill);
            Phone = (Button) findViewById(R.id.phone);


            statespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object value = parent.getItemAtPosition(position);
                    statee = value.toString().trim();
                    if (statee.equals("Maharashtra")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : Maharashtra) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, list);

                        City.setAdapter(arrayAdapter);
                    }
                    if (statee.equals("Gujarat")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : Gujarat) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, list);

                        City.setAdapter(arrayAdapter);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            City.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object value = parent.getItemAtPosition(position);
                    cityy = value.toString().trim();
                    if (cityy.equals("Mumbai")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Mumbai) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }

                    if (cityy.equals("Pune")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Pune) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }

                    if (cityy.equals("Aurangabad")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Aurangabad) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }

                    if (cityy.equals("Ahemdabad")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Ahemdabad) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Surat")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Surat) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                    if (cityy.equals("Rajkot")) {
                        ArrayList<String> listt = new ArrayList<>();
                        for (String text : Rajkot) {
                            listt.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registeration.this, android.R.layout.simple_spinner_item, listt);
                        Suburban.setAdapter(arrayAdapter);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            Suburban.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object value = parent.getItemAtPosition(position);
                    suburban = value.toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            databaseReference = firebaseDatabase.getInstance().getReference("Customer");
            FAuth = FirebaseAuth.getInstance();

            Signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    email = emaill.getEditText().getText().toString().trim();
                    password = pass.getEditText().getText().toString().trim();
                    firstname = fname.getEditText().getText().toString().trim();
                    lastname = lname.getEditText().getText().toString().trim();
                    Localaddress = localadd.getEditText().getText().toString().trim();
                    confirmpass = cmpass.getEditText().getText().toString().trim();
                    mobileno = Mobileno.getEditText().getText().toString().trim();

                    if (isValid()) {

                        mDialog.show();

                        FAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    databaseReference = FirebaseDatabase.getInstance().getReference("User").child(useridd);
                                    final HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put("Role", role);
                                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            HashMap<String, String> hashMappp = new HashMap<>();
                                            hashMappp.put("City", cityy);
                                            hashMappp.put("ConfirmPassword", confirmpass);
                                            hashMappp.put("EmailID", email);
                                            hashMappp.put("FirstName", firstname);
                                            hashMappp.put("LastName", lastname);
                                            hashMappp.put("Mobileno", mobileno);
                                            hashMappp.put("Password", password);
                                            hashMappp.put("LocalAddress", Localaddress);
                                            hashMappp.put("State", statee);
                                            hashMappp.put("Suburban", suburban);
                                            firebaseDatabase.getInstance().getReference("Customer")
                                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(hashMappp).addOnCompleteListener(new OnCompleteListener<Void>() {

                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {

                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                mDialog.dismiss();
                                                                AlertDialog.Builder builder = new AlertDialog.Builder(Registeration.this);
                                                                builder.setMessage("Registered Successfully,Please Verify your Email");
                                                                builder.setCancelable(false);
                                                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {

                                                                        dialog.dismiss();
                                                                        String phonenumber = Cpp.getSelectedCountryCodeWithPlus() + mobileno;
                                                                        Intent b = new Intent(Registeration.this, VerifyPhone.class);
                                                                        b.putExtra("phonenumber", phonenumber);
                                                                        startActivity(b);

                                                                    }
                                                                });
                                                                AlertDialog alert = builder.create();
                                                                alert.show();

                                                            } else {
                                                                mDialog.dismiss();
                                                                ReusableCodeForAll.ShowAlert(Registeration.this,"Error",task.getException().getMessage());

                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    });

                                } else {
                                    mDialog.dismiss();
                                    ReusableCodeForAll.ShowAlert(Registeration.this,"Error",task.getException().getMessage());
                                }
                            }
                        });
                    }


                }
            });
        } catch (Exception e) {
            mDialog.dismiss();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Registeration.this, Login.class);
                startActivity(i);
                finish();
            }
        });

        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent e = new Intent(Registeration.this, LoginPhone.class);
                startActivity(e);
                finish();
            }
        });


    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean isValid() {
        emaill.setErrorEnabled(false);
        emaill.setError("");
        fname.setErrorEnabled(false);
        fname.setError("");
        lname.setErrorEnabled(false);
        lname.setError("");
        pass.setErrorEnabled(false);
        pass.setError("");
        cmpass.setErrorEnabled(false);
        cmpass.setError("");
        Mobileno.setErrorEnabled(false);
        Mobileno.setError("");

        boolean isValidfirstname = false, isValidlastname = false, isValidaddress = false, isValidemail = false, isvalidpassword = false, isvalidconfirmpassword = false, isvalid = false, isvalidmobileno = false;
        if (TextUtils.isEmpty(firstname)) {
            fname.setErrorEnabled(true);
            fname.setError("FirstName is required");
        } else {
            isValidfirstname = true;
        }
        if (TextUtils.isEmpty(lastname)) {
            lname.setErrorEnabled(true);
            lname.setError("LastName is required");
        } else {
            isValidlastname = true;
        }
        if (TextUtils.isEmpty(email)) {
            emaill.setErrorEnabled(true);
            emaill.setError("Email is required");
        } else {
            if (email.matches(emailpattern)) {
                isValidemail = true;
            } else {
                emaill.setErrorEnabled(true);
                emaill.setError("Enter a valid Email Address");
            }

        }
        if (TextUtils.isEmpty(mobileno)) {
            Mobileno.setErrorEnabled(true);
            Mobileno.setError("Mobile number is required");
        } else {
            if (mobileno.length() < 10) {
                Mobileno.setErrorEnabled(true);
                Mobileno.setError("Invalid mobile number");
            } else {
                isvalidmobileno = true;
            }
        }
        if (TextUtils.isEmpty(password)) {
            pass.setErrorEnabled(true);
            pass.setError("Password is required");
        } else {
            if (password.length() < 6) {
                pass.setErrorEnabled(true);
                pass.setError("Password too weak");
                cmpass.setError("password too weak");
            } else {
                isvalidpassword = true;
            }
        }
        if (TextUtils.isEmpty(confirmpass)) {
            cmpass.setErrorEnabled(true);
            cmpass.setError("Confirm Password is required");
        } else {
            if (!password.equals(confirmpass)) {
                pass.setErrorEnabled(true);
                pass.setError("Password doesn't match");
                cmpass.setError("Password doesn't match");
            } else {
                isvalidconfirmpassword = true;
            }
        }
        if (TextUtils.isEmpty(Localaddress)) {
            localadd.setErrorEnabled(true);
            localadd.setError("Local Address is required");
        } else {
            isValidaddress = true;
        }
        isvalid = (isValidfirstname && isValidlastname && isValidemail && isvalidconfirmpassword && isvalidpassword && isvalidmobileno && isValidaddress) ? true : false;
        return isvalid;
    }
}


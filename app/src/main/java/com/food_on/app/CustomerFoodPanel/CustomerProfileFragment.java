package com.food_on.app.CustomerFoodPanel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.food_on.app.Customer;
import com.food_on.app.MainMenu;
import com.food_on.app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerProfileFragment extends Fragment {


    String[] Maharashtra = {"Mumbai", "Pune", "Aurangabad"};
    String[] Gujarat = {"Ahemdabad", "Rajkot", "Surat"};


    String[] Mumbai = {"Churchgate", "Marine Lines", "Charni Road", "Grant Road", "Mumbai Central", "Mahalakshmi", "Lower Parel", "Prabhadevi",
            "Dadar", "Matunga", "Mahim", "Bandra", "Khar", "Santacruz", "Vile Parle", "Andheri", "Jogeshwari", "Ram Mandir",
            "Goregaon", "Malad", "Kandivai", "Borivali", "Dahisar", "MiraRoad", "Bhayander", "Naigaon", "Vasai Road", "Nalla Sopara", "Virar"};


    String[] Pune = {"Hinjewadi", "Wagholi", " Ambegaon", "Undri", "Katraj"};
    String[] Aurangabad = {"Aarif Colony", "Baiji Pura", "Balaji Nagar", "Angoori Bagh"};
    String[] Ahemdabad = {"Mani Nagar", "Thaltej", "Prahlad Nagar", "Gandhinagar"};
    String[] Surat = {"Agnovad", "Akoti", "Amroli", "Athwalines"};
    String[] Rajkot = {"Kalawad Road", "Astron chowk", "Kotecha chowk", "Trikon bag"};

    EditText firstname, lastname, address;
    Spinner State, City, Suburban;
    TextView mobileno, Email;
    Button Update;
    LinearLayout password, LogOut;
    DatabaseReference databaseReference, data;
    FirebaseDatabase firebaseDatabase;
    String statee, cityy, suburban, email, passwordd, confirmpass;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Profile");
        View v = inflater.inflate(R.layout.fragment_customerprofile, null);

        firstname = (EditText) v.findViewById(R.id.fnamee);
        lastname = (EditText) v.findViewById(R.id.lnamee);
        address = (EditText) v.findViewById(R.id.address);
        Email = (TextView) v.findViewById(R.id.emailID);
        State = (Spinner) v.findViewById(R.id.statee);
        City = (Spinner) v.findViewById(R.id.cityy);
        Suburban = (Spinner) v.findViewById(R.id.sub);
        mobileno = (TextView) v.findViewById(R.id.mobilenumber);
        Update = (Button) v.findViewById(R.id.update);
        password = (LinearLayout) v.findViewById(R.id.passwordlayout);
        LogOut = (LinearLayout) v.findViewById(R.id.logout_layout);

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Customer").child(userid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Customer customer = dataSnapshot.getValue(Customer.class);

                firstname.setText(customer.getFirstName());
                lastname.setText(customer.getLastName());
                address.setText(customer.getLocalAddress());
                mobileno.setText(customer.getMobileno());
                Email.setText(customer.getEmailID());
                State.setSelection(getIndexByString(State, customer.getState()));
                State.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Object value = parent.getItemAtPosition(position);
                        statee = value.toString().trim();
                        if (statee.equals("Maharashtra")) {
                            ArrayList<String> list = new ArrayList<>();
                            for (String text : Maharashtra) {
                                list.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
                            City.setAdapter(arrayAdapter);
                        }
                        if (statee.equals("Gujarat")) {
                            ArrayList<String> list = new ArrayList<>();
                            for (String text : Gujarat) {
                                list.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);

                            City.setAdapter(arrayAdapter);
                        }
                        City.setSelection(getIndexByString(City, customer.getCity()));
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
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }

                        if (cityy.equals("Pune")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Pune) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }

                        if (cityy.equals("Aurangabad")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Aurangabad) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Ahemdabad")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Ahemdabad) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Surat")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Surat) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        if (cityy.equals("Rajkot")) {
                            ArrayList<String> listt = new ArrayList<>();
                            for (String text : Rajkot) {
                                listt.add(text);
                            }
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listt);
                            Suburban.setAdapter(arrayAdapter);
                        }
                        Suburban.setSelection(getIndexByString(Suburban, customer.getSuburban()));
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        updateinformation();
        return v;
    }

    private void updateinformation() {


        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                data = FirebaseDatabase.getInstance().getReference("Customer").child(useridd);
                data.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Customer customer = dataSnapshot.getValue(Customer.class);


                        confirmpass = customer.getConfirmPassword();
                        email = customer.getEmailID();
                        passwordd = customer.getPassword();
                        long mobilenoo = Long.parseLong(customer.getMobileno());

                        String Fname = firstname.getText().toString().trim();
                        String Lname = lastname.getText().toString().trim();
                        String Address = address.getText().toString().trim();

                        HashMap<String, String> hashMappp = new HashMap<>();
                        hashMappp.put("City", cityy);
                        hashMappp.put("ConfirmPassword", confirmpass);
                        hashMappp.put("EmailID", email);
                        hashMappp.put("FirstName", Fname);
                        hashMappp.put("LastName", Lname);
                        hashMappp.put("Mobileno", String.valueOf(mobilenoo));
                        hashMappp.put("Password", passwordd);
                        hashMappp.put("LocalAddress", Address);
                        hashMappp.put("State", statee);
                        hashMappp.put("Suburban", suburban);
                        firebaseDatabase.getInstance().getReference("Customer").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(hashMappp);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), CustomerPassword.class);
                startActivity(intent);
            }
        });

        mobileno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), CustomerPhonenumber.class);
                startActivity(i);
            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure you want to Logout ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getActivity(), MainMenu.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);


                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

    }

    private int getIndexByString(Spinner st, String spist) {
        int index = 0;
        for (int i = 0; i < st.getCount(); i++) {
            if (st.getItemAtPosition(i).toString().equalsIgnoreCase(spist)) {
                index = i;
                break;
            }
        }
        return index;
    }
}

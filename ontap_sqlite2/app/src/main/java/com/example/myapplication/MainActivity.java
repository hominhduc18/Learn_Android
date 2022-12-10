package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvPJ;
    private Button btnADD, btnREMOVE, btnEDIT, btnSEARCH, btnCANCEL;
    private EditText edtPJN, edtDL, edtNAME, edtSEARCH;

    private AdapterPJ adapterPJ;
    private ArrayList<Project> projects;
    private DB db;

    private String idpj = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvPJ = findViewById(R.id.lvPJ);
        btnADD = findViewById(R.id.btnADD);
        btnCANCEL = findViewById(R.id.btnCANCEL);
        btnEDIT = findViewById(R.id.btnEDIT);
        btnREMOVE = findViewById(R.id.btnREMOVE);
        btnSEARCH = findViewById(R.id.btnSEARCH);
        edtDL = findViewById(R.id.edtDL);
        edtNAME = findViewById(R.id.edtNAME);
        edtPJN = findViewById(R.id.edtPJN);
        edtSEARCH = findViewById(R.id.edtSEARCH);

        db = new DB(this);
//        addSample();

        projects = db.getAllPJ();
        adapterPJ = new AdapterPJ(this, R.layout.lv_item, projects);
        lvPJ.setAdapter(adapterPJ);

        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFill()) {
                    Project project = new Project();
                    project.setProjectname(edtPJN.getText().toString());
                    project.setName(edtNAME.getText().toString());
                    project.setDeadline(edtDL.getText().toString());
                    if (db.insertPJ(project) > 0) {
                        Toast.makeText(MainActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                        projects.clear();
                        projects.addAll(db.getAllPJ());
                        adapterPJ.notifyDataSetChanged();
                        btnCANCEL.callOnClick();
                    }
                }
            }
        });

        lvPJ.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Project project = projects.get(position);
                edtPJN.setText(project.getProjectname());
                edtNAME.setText(project.getName());
                edtDL.setText(project.getDeadline());
                idpj = String.valueOf(project.getId());
            }
        });

        btnREMOVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idpj.equals("")) {
                    Toast.makeText(MainActivity.this, "Vui long chon 1 project", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Xoa?")
                            .setNegativeButton("Cancel", null)
                            .setPositiveButton("OK", new AlertDialog.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.deletePJ(idpj);
                                    Toast.makeText(MainActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                                    projects.clear();
                                    projects.addAll(db.getAllPJ());
                                    adapterPJ.notifyDataSetChanged();
                                    btnCANCEL.callOnClick();
                                }
                            }).show();
                }
            }
        });

        btnCANCEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtDL.setText("");
                edtSEARCH.setText("");
                edtNAME.setText("");
                edtPJN.setText("");
                idpj = "";
                btnSEARCH.callOnClick();
            }
        });

        btnEDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkFill()) {
                    if (idpj.equals("")) {
                        Toast.makeText(MainActivity.this, "Vui long chon 1 prj", Toast.LENGTH_SHORT).show();
                    } else {

                        Project project = new Project();
                        project.setProjectname(edtPJN.getText().toString());
                        project.setName(edtNAME.getText().toString());
                        project.setDeadline(edtDL.getText().toString());
                        if (db.updatePJ(idpj, project) > 0) {
                            Toast.makeText(MainActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                            projects.clear();
                            projects.addAll(db.getAllPJ());
                            adapterPJ.notifyDataSetChanged();
                            btnCANCEL.callOnClick();
                        }
                    }
                }
            }
        });

        btnSEARCH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projects.clear();
                projects.addAll(db.search(edtSEARCH.getText().toString()));
                adapterPJ.notifyDataSetChanged();
            }
        });

    }

    public void addSample() {
        db.insertPJ(new Project("Test", "18/09/1991234237", "sdfsdn"));
        db.insertPJ(new Project("CMS", "18123/12309/191231297", "Aádnnguưẻwẻyen"));
        db.insertPJ(new Project("WEB", "18/12309/1123997", "Anádnsdfádáguyen"));
        db.insertPJ(new Project("AI", "18123/09/1123997", "Anđâsngádáuyen"));
        db.insertPJ(new Project("ERB", "18123/12309/1123997", "Anádánguyen"));
    }

    public boolean checkFill() {
        if (edtPJN.getText().toString().equals("")) {
            Toast.makeText(this, "Hãy nhập PJ NAME", Toast.LENGTH_SHORT).show();
        } else if (edtDL.getText().toString().equals("")) {
            Toast.makeText(this, "Hãy nhập DEADLINE", Toast.LENGTH_SHORT).show();
        } else if (edtNAME.getText().toString().equals("")) {
            Toast.makeText(this, "Hãy nhập NAME", Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }
        return false;
    }
}
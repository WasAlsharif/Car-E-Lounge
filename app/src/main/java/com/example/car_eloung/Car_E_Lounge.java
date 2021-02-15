package com.example.car_eloung;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

/*import org.dmg.pmml.MiningFunction;
import org.dmg.pmml.Model;
import org.dmg.pmml.PMML;
import org.dmg.pmml.tree.TreeModel;
import org.jpmml.evaluator.ModelEvaluatorFactory;
import org.jpmml.evaluator.ValueFactoryFactory;
import org.jpmml.evaluator.reporting.ReportingValueFactoryFactory;
import org.jpmml.model.JAXBUtil;
import org.jpmml.model.filters.ImportFilter;
import org.xml.sax.InputSource;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.ModelEvaluatorBuilder;
import org.jpmml.evaluator.reporting.ReportingValueFactoryFactory;
import org.jpmml.evaluator.tree.TreeModelEvaluator;
import org.xml.sax.InputSource;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.CapabilitiesHandler;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.RevisionHandler;
import weka.core.RevisionUtils;
import weka.core.SerializedObject;
import weka.core.Utils;
import weka.core.Capabilities.Capability;
import weka.core.pmml.PMMLUtils;
import weka.core.pmml.jaxbbindings.DecisionTree;
import weka.core.pmml.jaxbbindings.PMML;
import weka.core.pmml.jaxbbindings.TreeModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.transform.Source;*/

public class Car_E_Lounge extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car__e__lounge);

        Button button = (Button)findViewById(R.id.button);
        Button button2 = (Button)findViewById(R.id.button2);

       // DecisionTree decisionTree = new DecisionTree();
      //  PMML pmml = new PMML();




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Car_E_Lounge.this, Questionnaire.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Car_E_Lounge.this, Car.class));
            }
        });



    }

}

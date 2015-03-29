/*
 * Properties files are located in /res/raw folder
 */

package com.example.shoouji_tsyrdean;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import android.app.Activity;
import android.content.res.Resources;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Button;

import com.example.shoouji_tsyrdean.mandarin.*;

public class MainActivity extends Activity {

	private EditText searchEditText;
	private TextView resultsTextView;
	
	private static Properties entries;
	private static Properties fuzzygr;
	
	private static String getMatches(String searchTerm) {
		String entry;
		StringBuilder result = new StringBuilder();
		int NUM_ENTRIES = entries.size();
			
		if (searchTerm.length() < 3) {
			return "Search term must be at least 3 letters long";
		}
		
		for (int i = 0; i < NUM_ENTRIES; i++) {
			entry = getEntry(i);
			if (entry.contains(searchTerm)) {
				result.append(entry);
				result.append("\n");
			}
		}
		return result.toString();
		//return getEntry(4);
	}
	
	private static String getByFuzzy(String fuzzyInput) {
		String entry;
		StringBuilder result = new StringBuilder();
		String[] syllables = fuzzyInput.split(" ");
		int syllablesLength = syllables.length;
		StringBuffer sb = new StringBuffer();
		
		result.append("Results for " + fuzzyInput + "\n");
		
		for (int i = 0; i < syllablesLength; i++) {
			String tonePy = Conversion.gr2py(syllables[i]);
			sb.append(Conversion.py2gr(tonePy.substring(0, tonePy.length()-1) + "1"));
			sb.append("_");
		}
		
		String tonelessGR = sb.toString();
		tonelessGR = tonelessGR.substring(0, tonelessGR.length()-1);
		
		String entryIndicesStr = fuzzygr.getProperty(tonelessGR);
		
		if (entryIndicesStr != null) {
			String[] entryIndices = entryIndicesStr.split(",");
			int numberOfMatches = entryIndices.length;
			
			for (int i = 0; i < numberOfMatches; i++) {
				result.append(getEntry(Integer.parseInt(entryIndices[i])));
				result.append("\n");
			}
		}
		
		return result.toString();
	}
	
    private static String getEntry(int index) {
        return entries.getProperty(String.valueOf(index));
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Resources resources = this.getResources();
		
		try {
			InputStreamReader rawEntriesResource = new InputStreamReader(resources.openRawResource(R.raw.cedict), "UTF-8");
			entries = new Properties();
			entries.load(rawEntriesResource);
			
			InputStreamReader rawFuzzyResource = new InputStreamReader(resources.openRawResource(R.raw.fuzzygr), "UTF-8");
			fuzzygr = new Properties();
			fuzzygr.load(rawFuzzyResource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		searchEditText = (EditText) findViewById(R.id.editText1);
		resultsTextView = (TextView) findViewById(R.id.textView1);
		final Button button = (Button) findViewById(R.id.button1);
		
		resultsTextView.setMovementMethod(new ScrollingMovementMethod());
		
		final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
				
		final InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
		
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// replace TextView with dictionary entries
				
				String searchString = searchEditText.getText().toString();
				
				String radioSelected;
				//radioSelected = ((RadioButton) this.findViewById(rg.getCheckedRadioButtonId())).getText().toString();
				int id = rg.getCheckedRadioButtonId();
				View radioButton = rg.findViewById(id);
				int radioId = rg.indexOfChild(radioButton);
				RadioButton rb = (RadioButton) rg.getChildAt(radioId);
				radioSelected = (String) rb.getText();
				// resultsTextView.setText(radioSelected);
				
				// resultsTextView.setText(getMatches(searchString));
				resultsTextView.scrollTo(0, 0);
				
				if (radioSelected.equals("contains")) {
					resultsTextView.setText(getMatches(searchString));
				} else if (radioSelected.equals("exactEnglish")) {
					resultsTextView.setText(getMatches("/" + searchString + "/"));
				} else if (radioSelected.equals("GR")) {
					resultsTextView.setText(getByFuzzy(searchString));
				}
				
				searchEditText.setText("");
				imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

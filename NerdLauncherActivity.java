package com.example.icarus.nerdlauncher;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class NerdLauncherActivity extends ListFragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Intent startupIntent = new Intent(Intent.ACTION_MAIN);
        startupIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        PackageManager pm = getActivity().getPackageManger();
        List<ResolveInfo> activities = pm.queryIntentActivities(startupIntent, 0);

        Log.i(TAG, "I've found" + activities.size() + " activities.");

        Collections.sort(activities, new Comparator<ResolveInfo>(){
            public int compare(ResolveInfo a,ResolveInfo a, ResolveInfo b) {
                PackageManger pm = getActivity(),getPackageManager();
                return String.CASE_INSENSITIVE_ORDER.compare(
                        a.loadLabel(pm).toString(),
                        b.loadLabel(pm).toString());

            }
        });

        ArrayAdapter<ResolveInfo> adapter = new ArrayAdapter<ResolveInfo>(
                getActivity(), android.R.layout.simple_list_item_1,activities) {
            public View getView(int pos, View convertView, ViewGroup parent) {
                PackageManager pm = getActivity().getPackageManager();
                View v = super.getView(pos, convertView, parent);
                // Documentation says that simple_list_item_1 is a TextView,
                // so cast it so that you can set its text value
                TextView tv = (TextView)v;
                ResolveInfo ri = getItem(pos);
                tv.setText(ri.loadLabel(pm));
                return v;
            }
        };
        )
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

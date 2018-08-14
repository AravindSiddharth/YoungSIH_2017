package com.example.lenovo.young_forever_final;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.AlarmClock;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.lenovo.young_forever_final.EmergencyCall.string;

public class Activity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "Tab2Fragment";

    private ListView mListView;
    private String str1;
    private String string1;
    private String str2;

    public static final  String SHARED="shared";
    public static final String TEXT="text";
    @Nullable

 //   Button b0,b1,b2,b3,b4,b5,b6,b7,b8;
    DownloadManager downloadManager;
    long previoustime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //View view = inflater.inflate(R.layout.activity_main4,false);
        mListView = (ListView) findViewById(R.id.listView);


        ArrayList<Card> list = new ArrayList<>();

        list.add(new Card("drawable://" + R.drawable.pics1, "Games"));
        list.add(new Card("drawable://" + R.drawable.pic2, "TV Shows"));
        list.add(new Card("drawable://" + R.drawable.pic3, "Maps"));
        list.add(new Card("drawable://" + R.drawable.pic_chitchat, "Chit Chat"));
        list.add(new Card("drawable://" + R.drawable.pic5alarm, "Remind Me"));
        list.add(new Card("drawable://" + R.drawable.pic_masterchef, "Masterchef"));
        list.add(new Card("drawable://" + R.drawable.pic_healthtips, "Health Tips"));
        list.add(new Card("drawable://" + R.drawable.pic_reports, "Medical Reports"));
        list.add(new Card("drawable://" + R.drawable.pic_checkup, "Checkup"));
        list.add(new Card("drawable://" + R.drawable.pic_emergencycall, "Emergency Call"));

        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.card_layout_main, list);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(view.getContext(), Games.class);
                    startActivity(intent);
                }

                if (position == 1) {
                    Intent intent = new Intent(view.getContext(), TVshows.class);
                    startActivity(intent);
                }

                if (position == 2) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    PackageManager manager=getPackageManager();
                    intent=manager.getLaunchIntentForPackage("com.google.android.apps.maps");
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    startActivity(intent);
                }

                if (position == 3) {
                    /*Intent intent = new Intent(Intent.ACTION_MAIN);
                    PackageManager manager=getPackageManager();
                    intent=manager.getLaunchIntentForPackage("com.wChitChat_5567406");
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    startActivity(intent);*/
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.wChitChat_5567406");
                    if (launchIntent != null) {
                        startActivity(launchIntent);//null pointer check in case package name was not found
                    }
                    else{
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://files.appsgeyser.com/Chit%20Chat_5567406.apk")));
                        /*Intent intent = new Intent(view.getContext(), ChitChat.class);
                        startActivity(intent);*/
                    }
                }
                if (position == 4) {
                    Intent openClockIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
                    openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getBaseContext().startActivity(openClockIntent);
                }

                if (position == 5) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://masterchef2017.weebly.com")));
                }

                if (position == 6) {
                    Intent intent = new Intent(view.getContext(), HealthTips_Web.class);
                    startActivity(intent);
                }

                if (position == 7) {
                    Intent intent = new Intent(view.getContext(), Records.class);
                    startActivity(intent);
                }

                if (position == 8) {
                    Intent intent = new Intent(view.getContext(), Chekup.class);
                    startActivity(intent);

                }
                if (position == 9) {
                    Intent intent = new Intent(view.getContext(), EmergencyCall.class);
                    startActivity(intent);

                }
            }
        });

    /*    b0=(Button)findViewById(R.id.Button0);
        b1=(Button)findViewById(R.id.Button1);
        b2=(Button)findViewById(R.id.Button2);
        b3=(Button)findViewById(R.id.Button3);
        b4=(Button)findViewById(R.id.Button4);
        b5=(Button)findViewById(R.id.Button5);
        b6=(Button)findViewById(R.id.Button6);
        b7=(Button)findViewById(R.id.Button7);
        b8=(Button)findViewById(R.id.Button8);

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), Games.class);
                startActivity(intent);

                //   startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/rrKuKYF50Vc")));

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(v.getContext(), TVshows.class);
                startActivity(intent);

                // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/rrKuKYF50Vc")));
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_MAIN);
                PackageManager manager=getPackageManager();
                intent=manager.getLaunchIntentForPackage("com.google.android.apps.maps");
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                startActivity(intent);


            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(Intent.ACTION_MAIN);
                PackageManager manager=getPackageManager();
                intent=manager.getLaunchIntentForPackage("com.wChitChat_5567406");
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                startActivity(intent);

                /*
                Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/" + "Chit Chat.apk")), "application/vnd.android.package-archive");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                PackageManager pm =getPackageManager();
                Intent appStartIntent = pm.getLaunchIntentForPackage("com.wChitChat_5567406");
                if (null != appStartIntent)
                {
                    startActivity(appStartIntent);
                }
                else {
                    downloadManager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                    Uri uri=Uri.parse("http://app.appsgeyser.com/widgetdownload.php?widget=Chit%20Chat_5567406");
                    DownloadManager.Request request=new DownloadManager.Request(uri);
                    /*notify download*/
                    /*request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    Long reference=downloadManager.enqueue(request);*/
                    /*Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://app.appsgeyser.com/widgetdownload.php?widget=Chit%20Chat_5567406"));
                    intent.setPackage("com.android.browser");
                    startActivity(intent);

                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
/*
                Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.android.deskclock");
                if(LaunchIntent!=null)
                {
                    startActivity(LaunchIntent);
                }
                else {
                    Intent intent = getPackageManager().getLaunchIntentForPackage("com.sec.android.app.clockpackage");
                    if (intent != null) {
                        startActivity(intent);
                    } else {
                        Intent intent1 = getPackageManager().getLaunchIntentForPackage("com.htc.android.worldclock");
                        startActivity(intent1);
                    }

                }


                Intent openClockIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
                openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBaseContext().startActivity(openClockIntent);

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method0 stub

             /*   Intent intent = new Intent(v.getContext(), cooking.class);
                startActivity(intent);

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://masterchef2017.weebly.com")));

            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

               Intent intent = new Intent(v.getContext(), healthtips.class);
                startActivity(intent);

                /*startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://hippocratic-threads.000webhostapp.com/")));


            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

               Intent intent = new Intent(v.getContext(), Records.class);
                startActivity(intent);



            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(v.getContext(), Chekup.class);
                startActivity(intent);



            }
        });

*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
       loadData();
       update();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)){
            //Do something
            if (getIntent().getExtras()!=null) {
                str1=getIntent().getStringExtra("key");
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + str1));
                saveData();
                startActivity(intent);

            }
            else if (TEXT!=null) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+str2));
                startActivity(intent);

            }


        }
        if ((keyCode == KeyEvent.KEYCODE_BACK)){
            //Do something
           back();
        }
        return true;
    }
    public void back(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to close the application?").setCancelable(false).setTitle("Exit").setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    public void saveData(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString(TEXT,str1);
        editor.apply();
        Toast.makeText(getApplicationContext(),"Data Saved",Toast.LENGTH_SHORT).show();
    }
    public void loadData(){
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED,MODE_PRIVATE);
        string1=sharedPreferences.getString(TEXT,"8939271106");
    }
    public void update(){
        str2=string1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Intent intent = new Intent(this, FirstActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_speak) {
            Intent intent = new Intent(this,TexttoSpeech.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
    int id = item.getItemId();

        if (id == R.id.nav_games) {
            // Handle the camera action

            Intent intent = new Intent(this, Games.class);
            startActivity(intent);
            // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/rrKuKYF50Vc")));

        } else if (id == R.id.nav_tvshows) {

           Intent intent = new Intent(this, TVshows.class);
            startActivity(intent);

            //  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/rrKuKYF50Vc")));

        } else if (id == R.id.nav_maps) {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            PackageManager manager=getPackageManager();
            intent=manager.getLaunchIntentForPackage("com.google.android.apps.maps");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(intent);

        } else if (id == R.id.nav_chat) {

            Intent intent = new Intent(Intent.ACTION_MAIN);
            PackageManager manager=getPackageManager();
            intent=manager.getLaunchIntentForPackage("com.wChitChat_5567406");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            startActivity(intent);

        } else if (id == R.id.nav_reminder) {

           /* Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("com.android.deskclock");
            if(LaunchIntent!=null)
            {
                startActivity(LaunchIntent);
            }
            else {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.sec.android.app.clockpackage");
                if (intent != null) {
                    startActivity(intent);
                } else {
                    Intent intent1 = getPackageManager().getLaunchIntentForPackage("com.htc.android.worldclock");
                    startActivity(intent1);
                }
                */
           Intent openClockIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
            openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getBaseContext().startActivity(openClockIntent);



        }else if (id == R.id.nav_healthtips) {

            Intent intent = new Intent(this, HealthTips_Web.class);
            startActivity(intent);

/*            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://hippocratic-threads.000webhostapp.com/")));*/

        }else if (id == R.id.nav_cooking) {

         /*   Intent intent = new Intent(this, cooking.class);
            startActivity(intent);
*/
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://masterchef2017.weebly.com")));
        }else if (id == R.id.nav_records) {

           Intent intent = new Intent(this, Records.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_chechup) {

            Intent intent = new Intent(this, Chekup.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_emergencycall) {

            Intent intent = new Intent(this, EmergencyCall.class);
            startActivity(intent);

        }


        else if (id == R.id.nav_aboutus) {

            Intent intent = new Intent(this, About.class);
            startActivity(intent);
           // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://hippocratic-threads.000webhostapp.com/about")));
        } else if (id == R.id.nav_contactus) {

            Intent intent = new Intent(this, Contactus.class);
            startActivity(intent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

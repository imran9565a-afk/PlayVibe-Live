package com.playvibe.live;
import android.app.*; import android.os.*; import android.graphics.Color; import android.view.Gravity; import android.widget.*;
public class MainActivity extends Activity {
 LinearLayout root,body; TextView balance; int coins=120;
 int dp(int n){return (int)(n*getResources().getDisplayMetrics().density);}
 TextView t(String s,int z){TextView v=new TextView(this);v.setText(s);v.setTextSize(z);v.setTextColor(Color.WHITE);v.setPadding(dp(8),dp(8),dp(8),dp(8));return v;}
 void toast(String s){Toast.makeText(this,s,0).show();}
 void card(String title,String sub,Runnable action){TextView v=t(title+"\n"+sub+"\nTap to open →",17);v.setBackgroundColor(0xff292b43);LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2);p.setMargins(0,0,0,dp(12));body.addView(v,p);v.setOnClickListener(w->action.run());}
 void page(String name){body.removeAllViews();body.addView(t(name,25));switch(name){
 case "Home":body.addView(t("Meet friends. Go live. Play together.",15));card("🔴 Public Live","Discover live rooms",()->page("Live Rooms"));card("👫 Friends","Join friends and invite people",()->page("Friends"));card("🎮 Play Together","Game room previews",()->page("Games"));card("🎁 Coins & Gifts","Send demo gifts",()->page("Gifts"));card("👤 My Profile","Profile settings",()->page("Profile"));break;
 case "Live Rooms":card("Creator Room","Sample room • Streaming not connected",()->page("Room Preview"));card("Music & Chat","Demo public room",()->page("Room Preview"));break;
 case "Friends":card("Aarav • Online","Friend preview",()->toast("Demo join request sent"));card("Sana • Online","Friend preview",()->toast("Demo join request sent"));break;
 case "Games":card("Ludo Room","Multiplayer not connected",()->toast("Game room prototype only"));card("Tic Tac Toe","Coming in a later build",()->toast("Demo only"));break;
 case "Gifts":card("🌹 Rose • 10 coins","No cash value",()->gift(10));card("💎 Diamond • 50 coins","No cash value",()->gift(50));card("👑 Crown • 100 coins","No cash value",()->gift(100));break;
 case "Profile":card("Guest Creator","Edit display name",()->{EditText e=new EditText(this);new AlertDialog.Builder(this).setTitle("Display name").setView(e).setPositiveButton("Save",(d,w)->toast("Saved locally: "+e.getText())).setNegativeButton("Cancel",null).show();});card("Wallet","Demo balance only • No withdrawal",()->page("Gifts"));break;
 default:card("Prototype notice","Real live video, chat, accounts, multiplayer and payouts need secure backend services.",()->page("Home"));
 }}
 void gift(int n){if(coins<n){toast("Not enough demo coins");return;}coins-=n;balance.setText("🪙 "+coins);toast("Demo gift sent");}
 public void onCreate(Bundle b){super.onCreate(b);getWindow().setStatusBarColor(0xff121323);getWindow().setNavigationBarColor(0xff121323);root=new LinearLayout(this);root.setOrientation(1);root.setPadding(dp(16),dp(16),dp(16),dp(8));root.setBackgroundColor(0xff121323);setContentView(root);LinearLayout head=new LinearLayout(this);head.addView(t("PLAYVIBE LIVE",22));balance=t("🪙 "+coins,16);head.addView(balance);root.addView(head);ScrollView scroll=new ScrollView(this);body=new LinearLayout(this);body.setOrientation(1);scroll.addView(body);root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1));LinearLayout nav=new LinearLayout(this);String[] tabs={"Home","Live Rooms","Friends","Games","Profile"};for(String s:tabs){TextView v=t(s,10);v.setGravity(Gravity.CENTER);nav.addView(v,new LinearLayout.LayoutParams(0,-2,1));v.setOnClickListener(w->page(s));}root.addView(nav);page("Home");}
}
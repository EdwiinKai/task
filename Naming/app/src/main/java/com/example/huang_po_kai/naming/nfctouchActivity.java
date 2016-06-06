package com.example.huang_po_kai.naming;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.*;
import java.sql.*;


public class nfctouchActivity extends AppCompatActivity {
    /* NFC */
    //list of NFC technologies detected:
    private Button btn4_java1;
    private TextView tv1_java;
    private EditText edt1_java;
    public NfcAdapter mAdapter;
    int num=0;
    private final String[][] techList =new String[][] { new String[] {
            NfcA.class.getName(),
            NfcB.class.getName(),
            NfcF.class.getName(),
            NfcV.class.getName(),
            IsoDep.class.getName(),
            MifareClassic.class.getName(),
            MifareUltralight.class.getName(),
            Ndef.class.getName() } };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfctouch);
        tv1_java=(TextView)findViewById(R.id.tag);
        edt1_java=(EditText)findViewById(R.id.edt1);
        btn4_java1=(Button)findViewById(R.id.btn4);
        btn4_java1.setOnClickListener(onclick);
    }
    private Button.OnClickListener onclick = new Button.OnClickListener() {
        public void onClick(View v) {
            tv1_java.setText(edt1_java.getText() + "按了：" + (++num));
           /* StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);*/
            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                Connection Connect = DriverManager.getConnection("jdbc:jtds:sqlserver://140.137.61.38:1433;instance=GHLAIPCCU;DatabaseName=acer", "acer", "zaq12wsx");
                Statement stmt = Connect.createStatement();
                String SQL = "insert into T1 values ('"+tv1_java.getText()+"')";
                stmt.execute(SQL);
                Connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    private void check_NFCDevice(){
        //取得該設備預設的無線感應裝置
        mAdapter =NfcAdapter.getDefaultAdapter(this);
        if(mAdapter==null){
            Toast.makeText(this,"不支援NFC感應功能！",Toast.LENGTH_SHORT).show();
            this.finish();
            return;
        }
    }
    /*  NFC  */

    @Override
    protected void onPause() {
        super.onPause();
        //disabling foreground dispatch:
        NfcAdapter nfcAdapter =NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.disableForegroundDispatch(this);
    }
    //將tag到的ID顯示出來
        @Override
        protected void onNewIntent(Intent intent) {
            super.onNewIntent(intent);
            if(intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
                ((TextView) findViewById(R.id.edt1)).setText(/*"Tag ID(16-14):"
                        + ByteArrayToHexString(intent
                        .getByteArrayExtra(NfcAdapter.EXTRA_ID))
                        +"\n Tag ID (16-8):"
                        +getHex(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID))
                        +"\n Tag ID (dec):"
                        +getDec(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID))
                        +"\n*/ "Tag ID (reversed):"
                        +getReversed(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID)));
            }
        }

    //16進位14碼
    private String ByteArrayToHexString(byte[] inarray) {
        int i, j, in;
        String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
                "B", "C", "D", "E", "F"};
        String out = "";
        for (j = 0; j < inarray.length; ++j) {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }
    //16進位8碼
    private String getHex(byte[]bytes){
        StringBuilder sb = new StringBuilder();
        for (int i = bytes.length - 1; i >= 0; --i) {
            int b = bytes[i] & 0xff;
            if (b < 0x10)
                sb.append('0');
            sb.append(Integer.toHexString(b));
            if (i > 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    //12進位10碼
    private long getDec(byte[] bytes){
        long result = 0;
        long factor = 1;
        for (int i = 0; i < bytes.length; ++i) {
            long value = bytes[i] & 0xffl;
            result += value * factor;
            factor *= 256l;
        }
        return result;
    }
    private long getReversed(byte[]bytes){
        long result = 0;
        long factor = 1;
        for (int i = bytes.length - 1; i >= 0; --i) {
            long value = bytes[i] & 0xffl;
            result += value * factor;
            factor *= 256l;
        }
        return result;
    }
    @Override
    protected void onResume() {
        super.onResume();
        //creating pending intent:
        PendingIntent pendingIntent =PendingIntent.getActivity(this, 0,
                new Intent(this,getClass())
                        .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        //creating intent receiver for NFC events:
        IntentFilter filter =new IntentFilter();
        filter.addAction(NfcAdapter.ACTION_TAG_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_TECH_DISCOVERED);
        // enabling foreground dispatch for getting intent for NFC event:
        NfcAdapter nfcAdapter =NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.enableForegroundDispatch(this, pendingIntent,
                new IntentFilter[] {filter},this.techList);
    }
    }
    /*
    }
    @Override
    protected String doInBackground(Integer...params){
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection Connect = DriverManager.getConnection("jdbc:jtds:sqlserver://140.137.61.38:1433;instance=GHLAIPCCU;DatabaseName=acer","acer","zaq12wsx");
            Statement stmt = Connect.createStatement();
            String SQL = "insert into T1 values ('poon')";
            stmt.execute(SQL);
            Connect.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    */

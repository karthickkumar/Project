package com.ocms.punchlist;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pick_photo.R;

public class UserLogin extends Activity implements OnClickListener{
	
	EditText userName,password;
	Button loginButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Remove title bar
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.login);
		
		/*String url = "http://mmhemobileservices.iscistech.com/UserAuthentication.svc/GetProjectsByLoginProjectID/?id=2";
		
		 try {
			DefaultHttpClient client = new DefaultHttpClient();
			 HttpGet request = new HttpGet(url);
			 HttpResponse response = client.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/* HttpClient httpclient = new DefaultHttpClient();
		    HttpPost httppost = new HttpPost("http://mmhemobileservices.iscistech.com/UserAuthentication.svc/IsValidUser");
		    try {
		        // Add your data
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        nameValuePairs.add(new BasicNameValuePair("username", "admin"));
		        nameValuePairs.add(new BasicNameValuePair("password", "aaa"));
		        //nameValuePairs.add(new BasicNameValuePair("id",  UUID.randomUUID().toString()));
		       // nameValuePairs.add(new BasicNameValuePair("msg",  "test"));
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		        // Execute HTTP Post Request
		        HttpResponse response = httpclient.execute(httppost);

		    } catch (ClientProtocolException e) {
		        // TODO Auto-generated catch block
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		    }*/
		//postData();
		
		WebService.invoke();
		
		boolean loginStatus = WebService.invokeLoginWS("admin","aaa","IsValidUser");
		    finish();
		
		TextView txt = (TextView)findViewById(R.id.logo_name);
		txt.setShadowLayer(30, 0, 0, Color.LTGRAY);
		
		userName = (EditText)findViewById(R.id.username);
		password = (EditText)findViewById(R.id.pwd);
		loginButton = (Button)findViewById(R.id.login_btn);
		loginButton.setOnClickListener(this);
	}

	

	@Override
	public void onClick(View v) {
		
		postData();
		
		startActivity(new Intent(this,ProjectList.class));
		finish();
		overridePendingTransition(0,0);
		//showFileChooser();
	}
	
	public void postData() {
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://mmhemobileservices.iscistech.com/UserAuthentication.svc/IsValidUser");
	    httppost.setHeader("Content-Type", "text/xml; charset=utf-8");
	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("username", "admin"));
	        nameValuePairs.add(new BasicNameValuePair("password", "aaa"));
	        //nameValuePairs.add(new BasicNameValuePair("id", "2"));
	       // nameValuePairs.add(new BasicNameValuePair("msg", "2"));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);

	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	} 
	
	private static final int FILE_SELECT_CODE = 0;

	private void showFileChooser() {
	    Intent intent = new Intent(Intent.ACTION_GET_CONTENT); 
	    intent.setType("*/*"); 
	    intent.addCategory(Intent.CATEGORY_OPENABLE);

	    try {
	        startActivityForResult(
	                Intent.createChooser(intent, "Select a File to Upload"),
	                FILE_SELECT_CODE);
	    } catch (android.content.ActivityNotFoundException ex) {
	        // Potentially direct the user to the Market with a Dialog
	        Toast.makeText(this, "Please install a File Manager.", 
	                Toast.LENGTH_SHORT).show();
	    }
	}

	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    switch (requestCode) {
	        case FILE_SELECT_CODE:
	        if (resultCode == RESULT_OK) {
	            // Get the Uri of the selected file 
	            Uri uri = data.getData();
	            // Get the path
	            try {
					String path = getPath(this, uri);
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
	            // Get the file instance
	            // File file = new File(path);
	            // Initiate the upload
	        }
	        break;
	    }
	    super.onActivityResult(requestCode, resultCode, data);
	}
	
	public static String getPath(Context context, Uri uri) throws URISyntaxException {
	    if ("content".equalsIgnoreCase(uri.getScheme())) {
	        String[] projection = { "_data" };
	        Cursor cursor = null;

	        try {
	            cursor = context.getContentResolver().query(uri, projection, null, null, null);
	            int column_index = cursor.getColumnIndexOrThrow("_data");
	            if (cursor.moveToFirst()) {
	                return cursor.getString(column_index);
	            }
	        } catch (Exception e) {
	            // Eat it
	        }
	    }
	    else if ("file".equalsIgnoreCase(uri.getScheme())) {
	        return uri.getPath();
	    }

	    return null;
	} 
}

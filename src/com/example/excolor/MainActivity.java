package com.example.excolor;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		/*setContentView(R.layout.color_layout);
		//设置窗口背景颜色
		getWindow().setBackgroundDrawableResource(R.color.someBgColor);
		//设置TextView控件
		TextView tv=(TextView) findViewById(R.id.colorText);
		//设置TextCView的文字颜色和背景颜色
		tv.setTextColor(getResources().getColor(R.color.someTextColor));
		tv.setBackgroundColor(getResources().getColor(R.color.someTextBgColor));*/
		
		setContentView(R.layout.test);
		TextView myTv=(TextView) findViewById(R.id.myText);
		try {
			myTv.setText(readXml());
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	String  readXml() throws XmlPullParserException,IOException {
		XmlResourceParser xrp=getResources().getXml(R.xml.corporations);
		StringBuilder sb=new StringBuilder();
		int et=xrp.getEventType();
		while(et!=XmlPullParser.END_DOCUMENT){
			String name=xrp.getName();
			if(et==XmlPullParser.START_TAG)
				if("corporation".equals(name))
					sb.append(xrp.getAttributeValue(0)).append(" ");
				else if("name".equals(name)){
					xrp.next();
					sb.append(xrp.getText()).append(" ");
				}else if("country".equals(name)){
					xrp.next();
					sb.append(xrp.getText()).append("\n");
				}
			xrp.next();
			et=xrp.getEventType();
		}
		return sb.toString();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

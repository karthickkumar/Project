package com.xml.handler;

import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class XMLHandler {

	public static enum viewType {
		LOGIN, PROJECT_LIST, PROJECT_DETAILS
	}

	private String projectName = "a:ProjectName";
	private String projectID = "a:ID";

	private InputStream resInputStream = null;
	private XmlPullParserFactory xmlFactoryObject;
	public volatile boolean parsingComplete = true;
	public viewType viewPage;
	
	public XMLHandler(InputStream stream, viewType view) {
		this.resInputStream = stream;
		this.viewPage = view;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getProjectID() {
		return projectID;
	}

	public void parseProjectList(XmlPullParser myParser) {
		int event;
		String text = null;
		try {
			event = myParser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {
				String name = myParser.getName();
				switch (event) {
				case XmlPullParser.START_TAG:
					break;
				case XmlPullParser.TEXT:
					text = myParser.getText();
					break;

				case XmlPullParser.END_TAG:
					if (name.equals("a:ProjectName")) {
						projectName = text;
					}

					else if (name.equals("a:ID")) {
						projectID = myParser.getAttributeValue(null, "value");
					} else {
					}
					break;
				}
				event = myParser.next();
			}
			parsingComplete = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void parseLogin(XmlPullParser myParser) {
		int event;
		String text = null;
		try {
			event = myParser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {
				String name = myParser.getName();
				switch (event) {
				case XmlPullParser.START_TAG:
					break;
				case XmlPullParser.TEXT:
					text = myParser.getText();
					break;

				case XmlPullParser.END_TAG:
					if (name.equals("a:ProjectName")) {
						projectName = text;
					}

					else if (name.equals("a:ID")) {
						projectID = myParser.getAttributeValue(null, "value");
					} else {
					}
					break;
				}
				event = myParser.next();
			}
			parsingComplete = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fetchXML() {
		try {
			xmlFactoryObject = XmlPullParserFactory.newInstance();
			XmlPullParser myparser = xmlFactoryObject.newPullParser();
			myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			myparser.setInput(resInputStream, null);
			
			if (viewPage == viewType.LOGIN)			
				parseLogin(myparser);
			if (viewPage == viewType.PROJECT_LIST)
				parseProjectList(myparser);
			
			resInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
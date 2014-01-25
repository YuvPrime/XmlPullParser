package com.example.usatodayxmlparser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class UsaTodayXmlParser {
	// name of the XML tags
	static final String ITEM = "item";
	static final String TITLE = "title";
	static final String LINK = "link";
	static final String DESCRIPTION = "description";

	ArrayList<UsaTodayFeed> UsaTodayFeedList = null;
	private UsaTodayFeed currentFeed = null;
	private String currentTag = null;

	public ArrayList<UsaTodayFeed> parse(InputStream is)
			throws XmlPullParserException {
		XmlPullParser parser = XmlPullParserFactory.newInstance()
				.newPullParser();
		parser.setInput(is, "UTF-8");
		try {

			int eventType = parser.getEventType();

					while (eventType != XmlPullParser.END_DOCUMENT) {

				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					UsaTodayFeedList = new ArrayList<UsaTodayFeed>();
					break;
				case XmlPullParser.START_TAG:
					currentTag = parser.getName();
					if (currentTag.equalsIgnoreCase(ITEM)) {
						currentFeed = new UsaTodayFeed();
					} else if (currentFeed != null) {
						if (currentTag.equalsIgnoreCase(TITLE)) {
							currentFeed.setTitle(parser.nextText());
							// currentFeed.setId(parser.nextText());
						} else if (currentTag.equalsIgnoreCase(DESCRIPTION)) {
							currentFeed.setDescription(parser.nextText());
						} else if (currentTag.equalsIgnoreCase(LINK)) {
							currentFeed.setUrl(parser.nextText());
						}
					}
					break;
				case XmlPullParser.END_TAG:
					currentTag = parser.getName();
					if (currentTag.equalsIgnoreCase(ITEM)
							&& currentFeed != null) {
						UsaTodayFeedList.add(currentFeed);
					}
					break;
				}
				eventType = parser.next();
			}

		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return UsaTodayFeedList;

	}
}

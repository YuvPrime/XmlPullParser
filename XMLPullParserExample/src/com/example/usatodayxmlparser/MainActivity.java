/**
 * author: Timothy
 */

package com.example.usatodayxmlparser;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	public List<UsaTodayFeed> feed = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			URL url = new URL(
					"http://rss.lefigaro.fr/lefigaro/laune?format=xml");
			new LoadFeedTaskUrl().execute(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class LoadFeedTaskUrl extends
			AsyncTask<URL, Void, List<UsaTodayFeed>> {

		@Override
		protected List<UsaTodayFeed> doInBackground(URL... args) {

			UsaTodayXmlParser parser = new UsaTodayXmlParser();
			try {
				feed = parser.parse(args[0].openStream());
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return feed;
		}

		@Override
		protected void onPostExecute(List<UsaTodayFeed> feed) {

			ArrayAdapter<UsaTodayFeed> adapter = new ArrayAdapter<UsaTodayFeed>(
					getBaseContext(), android.R.layout.simple_list_item_1, feed);

			setListAdapter(adapter);

		}

		// private class LoadFeedTask extends
		// AsyncTask<InputStream, Void, List<UsaTodayFeed>> {
		//
		// @Override
		// protected List<UsaTodayFeed> doInBackground(InputStream... args) {
		//
		// // CALL XMLPULLPARSER & RETURN A LIST
		// UsaTodayXmlParser parser = new UsaTodayXmlParser();
		// try {
		// feed = parser.parse(args[0]);
		// } catch (XmlPullParserException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// return feed;
		// }
		//
		// @Override
		// protected void onPostExecute(List<UsaTodayFeed> feed) {
		//
		// ArrayAdapter<UsaTodayFeed> adapter = new ArrayAdapter<UsaTodayFeed>(
		// getBaseContext(), android.R.layout.simple_list_item_1,
		// feed);
		//
		// setListAdapter(adapter);
		//
		// }
		//
		// }
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		String content = feed.get(position).getUrl();
		Intent openFeed = new Intent(Intent.ACTION_VIEW);
		openFeed.setData(Uri.parse(content));
		startActivity(openFeed);
	}
}

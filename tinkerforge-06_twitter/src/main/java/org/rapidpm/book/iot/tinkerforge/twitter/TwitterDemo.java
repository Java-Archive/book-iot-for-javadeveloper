package org.rapidpm.book.iot.tinkerforge.twitter;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.api.TweetsResources;

/**
 * Created by Sven Ruppert on 03.01.2015.
 */
public class TwitterDemo {

  public static void main(String[] args) throws TwitterException {
    TwitterFactory twitterFactory = new TwitterFactory();
    final Twitter twitter = twitterFactory.createTwitter();

twitter.updateStatus("und er ist wieder da....");


  }
}

/*
 * Author: Pratik Sanghvi
 * Idea driven from http://www.netinstructions.com/how-to-make-a-simple-web-crawler-in-java/ 
 * */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WebCrawler
{
  
  private static final int MAX_PAGES_TO_SEARCH = 100;
  private Set<String> urlsVisited = new HashSet<String>();
  private List<String> urlsToVisit = new LinkedList<String>();


  /*
   * This method accepts the url and initiates the WebCrawler instance 
   * which using Jsoup finds the word in the html document.
   */
  public void search(String url, String searchWord)
  {
      while(this.urlsVisited.size() < MAX_PAGES_TO_SEARCH)
      {
          String currentUrl;
          WebCrawlerLeg leg = new WebCrawlerLeg();
          if(this.urlsToVisit.isEmpty())
          {
              currentUrl = url;
              this.urlsVisited.add(url);
          }
          else
          {
              currentUrl = this.nextUrl();
          }
          leg.crawl(currentUrl); 
          
          boolean success = leg.searchForWord(searchWord);
          if(success)
          {
              System.out.println(String.format("Word %s found at %s", searchWord, currentUrl));
          }
          this.urlsToVisit.addAll(leg.getLinks());
      }
      System.out.println("Done Searching");
  }

  private String nextUrl()
  {
      String nextUrl;
      do
      {
          nextUrl = this.urlsToVisit.remove(0);
      } 
      while(this.urlsVisited.contains(nextUrl));
      this.urlsVisited.add(nextUrl);
      return nextUrl;
  }
}
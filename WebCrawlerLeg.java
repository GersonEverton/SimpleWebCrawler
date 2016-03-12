/*
 * Author: Pratik Sanghvi
 * Idea driven from http://www.netinstructions.com/how-to-make-a-simple-web-crawler-in-java/ 
 * */

import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawlerLeg
{
    // Using a fake USER_AGENT so the web server thinks the robot is a normal web browser.
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<String> links = new LinkedList<String>();
    private Document htmlDocument;


    /*
      This method makes an HTTP request, checks the response, and then gathers
      up all the links on the page. Perform a searchForWord after the successful crawl.
    */
    public boolean crawl(String url)
    {
        try
        {
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.htmlDocument = htmlDocument;
            
            if(!connection.response().contentType().contains("text/html"))
            {
                return false;
            }
            Elements linksOnPage = htmlDocument.select("a[href]");
            
            for(Element link : linksOnPage)
            {
                this.links.add(link.absUrl("href"));
            }
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }


    /*
      Performs a search on the body of on the HTML document that is retrieved. 
      This method should only be called after a successful crawl.
     */
    public boolean searchForWord(String searchWord)
    {
        // Defensive coding. This method should only be used after a successful crawl.
        if(this.htmlDocument == null)
        {
            System.out.println("ERROR! Call crawl() before performing analysis on the document");
            return false;
        }
        
        String bodyText = this.htmlDocument.body().text();
        return bodyText.toLowerCase().contains(searchWord.toLowerCase());
    }


    public List<String> getLinks()
    {
        return this.links;
    }

}
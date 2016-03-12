/*
 * Author: Pratik Sanghvi
 * Idea driven from http://www.netinstructions.com/how-to-make-a-simple-web-crawler-in-java/ 
 * */

public class WebCrawlerTest
{
    /*This is our test class. It creates a crawler and crawls the given website to
      find a given word.
	*/
    public static void main(String[] args)
    {
        WebCrawler crawler = new WebCrawler();
        crawler.search("http://www.cnn.com", "computer");
    }
}
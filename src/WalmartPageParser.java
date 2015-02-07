import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class WalmartPageParser {
	Document doc;
	
	void parse(String data)
	{
		doc = Jsoup.parse(data);
	}
	String getTotalResults()
	{
		String i = "No results";
		Elements results = doc.getElementsByClass("numResults");
		String parsedResult = results.html();
		if(parsedResult.length() == 0)
			return i;
		return parsedResult;
	}
	List<String> getPageResults()
	{
		List<String> list = new ArrayList<String>();
		List<String> priceList = new ArrayList<String>();
		
		Elements results = doc.select(".item .prodInfo .prodLink.GridItemLink");
		Elements prices = doc.getElementsByClass("item");
		
		for(int k =0;k<prices.size();k++){
			Elements price = prices.get(k).select(".PriceSItalicStrikethruLtgry");
			Element e1 = prices.get(k).select(".bigPriceText2").first();
			Element e2 = prices.get(k).select(".smallPriceText2").first();
			if(price.first() == null)
			{
				if(e1==null || e2 == null)
				{
					priceList.add("Unknown price");
					continue;
				}
				String temp = e1.html()+e2.html();
				priceList.add(temp.replaceAll("\n", ""));
			}
			else if(price.first() != null)
			{
				if(e1==null || e2 == null)
				{
					priceList.add(price.first().html().replaceAll("\n",""));
				}
				else
				{
					String temp = e1.html()+e2.html();
					priceList.add(temp.replaceAll("\n", ""));
				}
			}else{
				priceList.add("Unknown price");
			}
		}
//		System.out.println(priceList.size()+"\n");
		String temp = null;
		for(int j=0;j<results.size();j++){
			list.add(results.get(j).attr("title").replaceAll("\n", "")+"\n\t"+"Price : "+priceList.get(j)+"\n");
		}
		return list;
	}
}


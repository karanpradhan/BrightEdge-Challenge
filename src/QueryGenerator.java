
public class QueryGenerator {
	private String query = "http://www.walmart.com/search/search-ng.do?search_query=";
	private String pageInfo = "&ic=16_";
	void appendKeyWords(String key)
	{
		String[] list = key.split(" ");
		StringBuilder br  = new StringBuilder();
		br.append(list[0]);
		for(int i=1;i<list.length;i++)
		{
			br.append("+");
			br.append(list[i]);
		}
		br.append("&ic=16_0");
		query = query+br.toString();
	}
	void appendKeysAndPages(String key,int pageNumber)
	{
		String relativePage = (pageNumber - 1) *16+"";
		pageInfo = pageInfo + relativePage;
		String[] list = key.split(" ");
		StringBuilder br  = new StringBuilder();
		br.append(list[0]);
		for(int i=1;i<list.length;i++)
		{
			br.append("+");
			br.append(list[i]);
		}
		query = query + br.toString()+pageInfo;
	}
	String generate()
	{
		return query;
	}
}

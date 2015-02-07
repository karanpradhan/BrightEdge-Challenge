import java.util.List;


public class Main {
	public static void main(String arg[])
	{
		if(arg.length == 0 || arg.length > 2)
			System.out.println("Invalid number of arguements");
		
		String keyWords = arg[0].replace("\"","").toLowerCase();
		
		WalmartHTTPClient walmartHTTPClient = new WalmartHTTPClient();
		QueryGenerator queryGenerator = new QueryGenerator();
		WalmartPageParser walmartPageParser = new WalmartPageParser();
		if(arg.length == 1)
		{
			queryGenerator.appendKeyWords(keyWords);
			walmartHTTPClient.setUrl(queryGenerator.generate());
			walmartPageParser.parse(walmartHTTPClient.getData());
			System.out.println(walmartPageParser.getTotalResults());
			
		}
		if(arg.length == 2)
		{
			int pageNumber = 0;
			try{
			pageNumber = Integer.parseInt(arg[1]);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Invalid Page Number");
				return;
				
			}
			queryGenerator.appendKeysAndPages(keyWords, pageNumber);
			walmartHTTPClient.setUrl(queryGenerator.generate());
			walmartPageParser.parse(walmartHTTPClient.getData());
			List<String> pageResults = walmartPageParser.getPageResults();
			if(pageResults.isEmpty())
				System.out.println("No results");
			else{
			String formatedOutput = pageResults.toString()
					.replace("[", "")
					.replace("]","")
					.replaceAll(", ","")
					.trim();
			System.out.println(formatedOutput);
			}
		}
	}
}

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Liem Le
 * Dec 12, 2017-10:40:37 AM
 * Collect data to create table Actuate_Tables in specs DB
 */
public class GetTableFromActuateQuery {
	
	public static HashSet<String> getTableFromQuery(String query, TABLETYPE type) {
		
		HashSet<String> tables = new HashSet<>();
		String datamartRegexString = null;
		
		if(TABLETYPE.DM.equals(type)) {
			datamartRegexString = "([#\\w]+_(REP|rep))";
		} else {
			datamartRegexString = "([#\\w]+_(DBF|dbf))";
		}
		
		Pattern datamartPattern = Pattern.compile(datamartRegexString);

        Matcher matcher = datamartPattern.matcher(query);
        
        while(matcher.find())
        {
        	tables.add(matcher.group(1));
        }

		return tables;		
	}
	
	public static HashSet<String> getAllTable(String query) {
		
		HashSet<String> tables = new HashSet<>();
		String datamartRegexString = "([#\\w]+_(REP|rep|DBF|dbf))";

		Pattern datamartPattern = Pattern.compile(datamartRegexString);

        Matcher matcher = datamartPattern.matcher(query);
        
        while(matcher.find())
        {
        	tables.add(matcher.group(1));
        }

		return tables;		
	}
	
	public static String hashListToString(HashSet<String> hs) {
		String result = "";
		
		for (String string : hs) {
			result += string + ',';
		}
		
		return result.substring(0, result.length()-1);
	}
 }

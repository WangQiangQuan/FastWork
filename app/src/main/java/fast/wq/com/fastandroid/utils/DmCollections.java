package fast.wq.com.fastandroid.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DmCollections {
	
	public static JSONObject map2Json(final Map<String,Object> map){
		try {
			JSONObject json = new JSONObject();
			if(map != null && !map.isEmpty()){
				for(String key : map.keySet()){
					json.put(key, map.get(key));
				}
			}
			return json;
		} catch (Exception e) {
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> jsonArray2List(final JSONArray jsonArray, Class<T> cls) {
		ArrayList<T> listdata = null;
		if (jsonArray != null) {
			listdata = new ArrayList<T>();
			for (int i = 0; i < jsonArray.length(); i++) {
				if(jsonArray.opt(i) != null && jsonArray.opt(i).getClass() == cls){
					listdata.add((T)(jsonArray.opt(i)));
				}
			}
		}
		return listdata;
	}
}

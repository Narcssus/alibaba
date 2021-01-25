import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.narc.alibaba.utils.HttpUtils;

/**
 * @author : Narcssus
 * @date : 2020/11/11 20:34
 */
public class test {
    public static void main(String[] args) {
        try {
            String start_time = "2021-01-01 18:00:01";
            String end_time = "2021-01-01 20:00:01";
            String uid = "1046427852";
            JSONArray array = getPaidOrder(uid,start_time,end_time);
            System.out.println(array.toJSONString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static JSONArray getPaidOrder(String uid, String start_time, String end_time) {
        JSONArray resArray = new JSONArray();
        try{
            String url = "https://api.taokouling.com/tbk/TbkScOrderDetailsGet";
            JSONObject req = new JSONObject();
            req.put("uid", uid);
            req.put("start_time", start_time);
            req.put("end_time", end_time);
            req.put("query_type", "2");
            req.put("page_size", 1);
            String res = HttpUtils.httpGet(url, req);
            JSONObject data = JSON.parseObject(res).getJSONObject("data");
            JSONArray jsonArray = JSON.parseObject(res).getJSONObject("data")
                    .getJSONObject("results").getJSONArray("publisher_order_dto");

            resArray.addAll(jsonArray);
            while (data.getBooleanValue("has_next")) {
                req.put("position_index", data.getString("position_index"));
                req.put("jump_type", "1");
                res = HttpUtils.httpGet(url, req);
                data = JSON.parseObject(res).getJSONObject("data");
                jsonArray = JSON.parseObject(res).getJSONObject("data")
                        .getJSONObject("results").getJSONArray("publisher_order_dto");
                resArray.addAll(jsonArray);
            }
        }catch (Exception e){

        }
        return resArray;
    }

}

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class paramsWaibao{
    public static String[] GAMEID = {"Mario","Link","Brick"};
    //diamond:星乐斗;winning:连胜玩法;crufew:限时赛;ladder:天梯赛
    public static int[] WinCode = {1,2,3,4,5};
    public static LinkedHashMap<String, String> sendParam;
    public static String secretKey = "Wfo3DnWg8m4a3DEG";
    public static String md5SecretKey = "443ba7f86ef6ba8b1310f1882ac21cbc";

    //MD5:前端后台通信加密，nouce要递增
    public static String genMd5Sign(String cmd,String nouce ){
        String md5Org = cmd + "#" + nouce + "#" + md5SecretKey;
        String mySign = MD5.createPassword(md5Org);
        return mySign;
    }
    //SHA1后台加密
    private static String genSign() {
        StringBuffer sb = new StringBuffer();
        int index = 0;
        for (Map.Entry<String, String> nv : sendParam.entrySet()) {
            if (index == 0) {
                sb.append(nv.getKey() + "=" + nv.getValue());
            } else {
                sb.append("&" + nv.getKey() + "=" + nv.getValue());
            }
            index++;
        }
        sb.append("&secret=" + secretKey);
        String sectet = SHA1.encode(sb.toString());
        System.out.println("source源参数"+sb);
        sendParam.put("sign", sectet);
        return sectet;
    }
    //与后台通信的query消息
    public static void genParam(String nouce, String roomId, String base64DataStr){
        sendParam = new LinkedHashMap<String, String>();
        sendParam.put("nonce", nouce);
        String timestamp=System.currentTimeMillis()+"";
        sendParam.put("timestamp", timestamp);
        sendParam.put("roomId", roomId);
        sendParam.put("data", base64DataStr);
        String sign=genSign();
        sendParam.put("sign",sign);
    }
    //双方对战消息
    public static JSONObject RoomDetail(String accountId,String oppAccountId,String roomId) {
        JSONObject jo = new JSONObject();
        jo.put("accountId",accountId);
        jo.put("nickName",accountId);
        jo.put("sex",2);
        jo.put("avatar","https://img.hiwan360.cn/hiwan360/product/images/user_head/10204_1526373832761_9182.jpg");
        jo.put("oppAccountId",oppAccountId);
        jo.put("oppNickName",oppAccountId);
        jo.put("oppSex",1);
        jo.put("oppAvatar","https://img.hiwan360.cn/hiwan360/product/images/user_head/10204_1526373832761_9182.jpg");
        jo.put("gameId","Gobang");
        jo.put("roomId",roomId);
        jo.put("isAddScore",1);
        jo.put("userType",0);
        jo.put("newbie",false);
        return jo;
    }
    //{"accountId":"10204","nickName":"韩聪聪", "sex":2, "avatar":"https://img.hiwan360.cn/hiwan360/product/images/user_head/10204_1526373832761_9182.jpg",
    // "oppAccountId":"10187","oppNickName":"hi123@shs","oppSex":2,"oppAvatar":"https://img.hiwan360.cn/hiwan360/product/images/user_head/10187_1526283319123_3032.jpg",
    // "gameId":"Gobang","roomId":"20180521185044ROOM06609994000398","isAddScore":1,"userType":0,"newbie":false}
    //enter消息：内层用户对战消息是SHA1加密，塞入query参数中；外层前后端通信，走MD5加密
    public static JSONObject EnterMessage(String accountId,String oppAccountId,String roomId) throws UnsupportedEncodingException {
        JSONObject jo = new JSONObject();
        jo.put("cmd", "Enter");
        String dataStr = RoomDetail(accountId,oppAccountId,roomId).toString();
        String base64DataStr = Base64.getEncoder().encodeToString(dataStr.getBytes("UTF-8"));
        //生成query参数：用户对战信息，房间信息等
        genParam(accountId,roomId,base64DataStr);
        String query = "?nonce="+sendParam.get("nonce")+
                "&timestamp="+sendParam.get("timestamp")+
                "&roomId="+sendParam.get("roomId")+
                "&data=" +sendParam.get("data")+
                "&sign="+sendParam.get("sign");
        jo.put("query", query);
        String nouce = System.currentTimeMillis()+"";
        jo.put("nouce", nouce);
        jo.put("sign", genMd5Sign("Enter",nouce));
     return jo;
    }
    //走步消息
    //{"cmd":"GOBANG","op":"READY","nouce":4694001,"sign":"6c46035d501255a04e5060c1552dcf03"}
    public static JSONObject GobangMessage(String op,int x,int y){
        JSONObject jo = new JSONObject();
        if(op.equals("STEP")){
            jo.put("x",x);
            jo.put("y",y);
        }
        //{"cmd":"GOBANG","op":"STEP","x":0,"y":5,"nouce":5776002,"sign":"15d120fd4a69085d4a60a5b5616333c6"}
        jo.put("cmd","Gobang");
        jo.put("op",op);
        String nouce = System.currentTimeMillis()+"";
        jo.put("nouce",nouce);
        jo.put("sign",genMd5Sign("Gobang",nouce));
        return jo;
    }
    //enter消息
    public static String enterMessage(String accountId,String oppAccountId,String roomId) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(EnterMessage(accountId,oppAccountId,roomId).toString().getBytes("UTF-8"));
    }
    //Gobang消息
    public static String gobangMessage(String op,int x,int y) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(GobangMessage(op,x,y).toString().getBytes("UTF-8"));
    }




    public static void main(String[] argv) throws IOException {
        System.out.println(enterMessage("88888888","88888889","8888888888888889"));
        System.out.println(gobangMessage("Ready",-1,-1));
        System.out.println(gobangMessage("STEP",4,7));
        System.out.println(gobangMessage("STEP",5,7));
        System.out.println(gobangMessage("STEP",6,7));
        System.out.println(gobangMessage("STEP",7,7));
        System.out.println(gobangMessage("STEP",8,7));
        System.out.println(enterMessage("88888889","88888888","8888888888888889"));
        System.out.println(gobangMessage("Ready",-1,-1));
        System.out.println(gobangMessage("STEP",4,7));
        System.out.println(gobangMessage("STEP",5,7));
        System.out.println(gobangMessage("STEP",6,7));
        System.out.println(gobangMessage("STEP",7,7));
        System.out.println(gobangMessage("STEP",8,7));


    }
}

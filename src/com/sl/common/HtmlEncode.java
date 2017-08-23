package com.sl.common;
/**
 * HtmlEncode防js注入公共类
 **/
public class HtmlEncode {
	
	/**
	 * 编码的方法
	 * @param string
	 * @return
	 */
	public static String htmlEncode(String string) {
		//判断参数是否为空
		if(null == string || "".equals(string)){
			return null;
		}else{
			String result = string;
			result = result.replaceAll("&", "&amp;");
			result = result.replaceAll("<", "&lt;");
			result = result.replaceAll(">", "&gt;");
			result = result.replaceAll("\"", "&quot;");
			return (result.toString());
		}
	}
	
	/**
	 * 解码的方法
	 * @param string
	 * @return
	 */
	public static String htmlDecode(String string) {
		//判断参数是否为空
		if(null == string || "".equals(string)){
			return null;
		}else{
			String result = string;
			result = result.replaceAll("&amp;", "&");
			result = result.replaceAll("&lt;", "<");
			result = result.replaceAll("&gt;", ">");
			result = result.replaceAll("&quot;", "\"");
			return (result.toString());
		}
	}
	
	/*public static void main(String[] args) {
		System.out.println(HtmlEncode.htmlEncode("<script>alert(\"123\");</script>&nbsp;&nbsp;"));
		System.out.println(HtmlEncode.htmlDecode("&lt;script&gt;alert(&quot;123&quot;);&lt;/script&gt;&amp;nbsp;&amp;nbsp;"));
	}*/
}
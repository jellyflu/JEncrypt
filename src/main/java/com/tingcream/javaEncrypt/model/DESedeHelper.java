package com.tingcream.javaEncrypt.model;

import com.tingcream.javaEncrypt.util.DESedeUtil;

public class DESedeHelper {
	
	/**
	 * 3des 加密
	 * @author jelly
	 * @param key 密钥
	 * @param text  原文
	 * @throws Exception
	 */
	public String encode3Des(String key ,String text) throws Exception {
		key=buildKey(key);
		return DESedeUtil.encode3Des(key, text);
	 
	}

	/**
	 * 3des 解密
	 * @author jelly
	 * @param key  密钥
	 * @param cipherText 密文
	 * @return
	 * @throws Exception
	 */
	public String decode3Des(String key,String cipherText) throws Exception {
		key=buildKey(key);
		return DESedeUtil.decode3Des(key, cipherText);
	}	
	
	/**
	 * 3des加密算法的key长度要求必须为24位字符
	 * @author jelly
	 * @date 2018年12月11日 
	 * @param key
	 * @return
	 */
	public String buildKey(String key ) {
		// 若key长度大于24位，则截取前面24位字符, 若key长度小于24位，则后面以0补充  
		if(key.length()>24) {
			return key.substring(0,24);
		}else {
            StringBuilder sb =new StringBuilder();
			int len=24-key.length();
			for(;len-->0;) {
				sb.append("0");
			}
			return   key+sb.toString();
		}
	}
	

	
	
	
}

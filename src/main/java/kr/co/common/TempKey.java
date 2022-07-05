package kr.co.common;

import java.util.Random;

public class TempKey {
	private boolean lowerCheck;
	private int size;
	
	public String getKey(int size, boolean lowerCheck) {
		this.size = size;
		this.lowerCheck = lowerCheck;
		return init();
	}
	
	private String init() {
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		StringBuffer sc = new StringBuffer("!@#$%^&*-=?~"); //특수문자
		StringBuffer sn = new StringBuffer("0123456789"); //숫자
		StringBuffer sm = new StringBuffer("abcdefghijklmnopqrstuvwxyz"); //영어
		int num = 0;
		sb.insert(0, sc.charAt((int)(Math.random()*sc.length()-1)));
		sb.insert(1, sn.charAt((int)(Math.random()*sn.length()-1)));
		sb.insert(2, sm.charAt((int)(Math.random()*sm.length()-1)));
		do {
			num = r.nextInt(75) + 48;
			//아스키 코드가 48이상이면 숫자, 65이상이면 A~Z, 97이상이면 a~z
			if((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
				sb.append((char)num); //생성된 num을 char단위로 변환하여 StringBuffer에 추가
			} else {
				continue;
			}
		} while(sb.length() < size);
		if(lowerCheck) {
			return sb.toString().toLowerCase();
		}
		return sb.toString();
	}
}

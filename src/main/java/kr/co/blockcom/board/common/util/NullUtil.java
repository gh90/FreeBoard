package kr.co.blockcom.board.common.util;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public final class NullUtil {
	private NullUtil() {
		// don't use!
	}
	
	/**
	 * 입력값이 널인지 확인합니다. 입력받은 값 중 하나라도 널이면 TRUE를 반환합니다.
	 * @param values
	 * @return
	 */
	public static boolean isNullAll(Object ... values){
		if(values == null){
			return true;
		}
		
		for(Object obj : values){
			if(isNull(obj)){
				return true;
			}
		}
		
		return false;
	}

	/**
	 * 입력값이 널인지 확인합니다.
	 * @param value
	 * @return 널이면 TRUE, 아니면 FALSE
	 */
	public static boolean isNull(Object value) {
		if (value == null) {
			return true;
		}
		if (value instanceof Integer && 0 == (Integer) value) {
			return true;
		}
		if (value instanceof Long && 0 == (Long) value) {
			return true;
		}
		if(value instanceof Double && 0 == (Double) value){
			return true;
		}
		if(value instanceof Float && 0 == (Float) value){
			return true;
		}
		if(value instanceof BigDecimal && BigDecimal.ZERO == (BigDecimal) value){
			return true;
		}
		if(value instanceof Map){
			return ((Map<?,?>)value).isEmpty();
		}
		if(value instanceof List){
			return ((List<?>)value).isEmpty();
		}
		if(value instanceof Object[]){
			return ((Object[])value).length == 0;
		}
		if(value.getClass().isArray()){
			return Array.getLength(value) == 0;
		}
		if(value instanceof String){
			String s = ((String)value).trim();
			if(s.length() == 0){
				return true;
			}
			if("undefined".equals(value) || "null".equals(value) || "(null)".equals(value)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNullMberSeq(Object value) {
		if (value == null) {
			return true;
		}
		if(value instanceof Map){
			return ((Map<?,?>)value).isEmpty();
		}
		if(value instanceof List){
			return ((List<?>)value).isEmpty();
		}
		if(value instanceof Object[]){
			return ((Object[])value).length == 0;
		}
		if(value.getClass().isArray()){
			return Array.getLength(value) == 0;
		}
		if(value instanceof String){
			String s = ((String)value).trim();
			if(s.length() == 0){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNullByInt(Object value) {
		if (value == null) {
			return true;
		}
		if(value instanceof Map){
			return ((Map<?,?>)value).isEmpty();
		}
		if(value instanceof List){
			return ((List<?>)value).isEmpty();
		}
		if(value instanceof Object[]){
			return ((Object[])value).length == 0;
		}
		if(value.getClass().isArray()){
			return Array.getLength(value) == 0;
		}
		if(value instanceof String){
			String s = ((String)value).trim();
			if(s.length() == 0){
				return true;
			}
		}
		return false;
	}	
	

	/**
	 * 입력값이 널인지 확인합니다.
	 * 값이 널일 경우 replacement값으로 대체합니다.
	 * @param value
	 * @param replacement
	 * @return
	 */
	public static <T> T replaceIfNull(T value, T replacement) {
		return isNull(value) ? replacement : value;
	}
}

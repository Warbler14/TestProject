package com.example.util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectUtil {

	private static final Logger logger = LoggerFactory.getLogger(ObjectUtil.class);

	/**
     * 빈 객체 체크
     * @param str
     * @return
     */
	public static boolean isNotEmpty( String str ){
		if( str == null || str.isEmpty() || "".equals(str) ){
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 빈 객체 체크
	 *
	 * @param builder
	 * @return
	 */
	public static boolean isNotEmpty(StringBuilder builder) {
		if (builder == null || builder.length() <= 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 빈 객체 체크
	 *
	 * @param buffer
	 * @return
	 */
	public static boolean isNotEmpty(StringBuffer buffer) {
		if (buffer == null || buffer.length() <= 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 빈 객체 체크
	 * @param chMap
	 * @return
	 */
	public static boolean isNotEmpty( Map<String, ?> chMap ){
		if( chMap == null || chMap.isEmpty() ){
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 빈 객체 체크
	 * @param useList
	 * @return
	 */
	public static boolean isNotEmpty( List<?> useList ){
		if( useList == null || useList.isEmpty() ){
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 객체 내용 출력
	 * @param cMap
	 */
	public static void print( ConcurrentHashMap<String, Object> cMap ){

		ConcurrentHashMap<String, Object> cloneCMap = null;
		synchronized( cMap ) {
			cloneCMap = new ConcurrentHashMap<String, Object>(cMap);
		}

		if( isNotEmpty( cloneCMap ) ){

			Iterator<String> itr0 = cloneCMap.keySet().iterator();
			while( itr0.hasNext() ){
				String key = itr0.next();
				logger.debug( key + " ~ " + cloneCMap.get( key ) );
			} //end while

		}
	}

	/**
	 * 객체 내용 출력
	 * @param useList
	 */
	public static void print( ArrayList<Object> useList  ) {

		if( isNotEmpty( useList ) ){

			for (int idx0 = 0, end0 = useList.size() ; idx0 < end0; idx0++) {
				logger.debug(idx0 + " ~ " + useList.toString() );
			}//end for

		}
	}

	public static String getPid() {
		RuntimeMXBean rt = ManagementFactory.getRuntimeMXBean();
		String processID = rt.getName();
		return processID.substring(0, processID.indexOf("@"));
	}

}

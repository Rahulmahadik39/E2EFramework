package webAppTestPackeg;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.util.ArrayUtil;

public class ArrayTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      
		
		Integer[] a= {1,2,3,4};
		Integer[] c= {5,6,7,8};
		Integer[] b=ArrayUtils.addAll(a,c);
		ArrayList<Integer> aList=new ArrayList<>(Arrays.asList(b));
		for(Integer temp:aList) {
		System.out.print(temp);
		System.out.print(" ");
		}
	}

}

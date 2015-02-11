import java.util.Scanner;
import java.util.Random;

public class main {

	public static void main(String[] args) {
		int size = findSize(args);
		int[][] arr = new int[size][size];
		int[][] transpose = new int[size][size];
		int[][] shift = new int[size][size];
		String key1;
		String key2;
		int[][] keyArr1 = new int[size][size];
		int[][] keyArr2 = new int[size][size];
		char[][] finArr = new char[size][size];
		int k=0;
		
		key1=input();
		key2=input();
		
		System.out.println(key1);
		System.out.println(key2);
		
		int key1Size = key1.length();
		int key2Size = key2.length();
		
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(k<args[0].length()){
					arr[i][j] = args[0].charAt(k);
					k++;
				}
				else
					arr[i][j] = '.';
			}
		}
		
		k=0;
		
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(k==key1.length())
					k=0;
				keyArr1[i][j] = key1.charAt(k);
				k++;
			}
		}
		
		k=0;
				
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(k==key2.length())
					k=0;
				keyArr2[i][j] = key2.charAt(k);
				k++;
			}
		}
		
		
		
		//fixCodes(arr,size);
		
		for(int i=0;i<4096;i++){
			
			xOr(arr,keyArr1);
			
			arrayIncrement(keyArr1,size,key1Size);
		
			transArray(arr,transpose);
		
			diagShift(transpose,shift);
		
			xOr(shift,keyArr2);
			
			arrayDecrement(keyArr2,size, key2Size);
		
			copyArr(arr,shift,size);
		
		}
		
		translate(arr,finArr);
		
		//reverseFixCodes(finArr, size);
		
		printArr(finArr,size);
		
	}
	
	public static void printArr(char[][] arr,int size){
		System.out.println("----------------");
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				System.out.print(arr[i][j]);
			}
		}
		
		System.out.println();
		System.out.println("----------------");
	}
	
	public static String generate(){
		String key = "";
		Random rand = new Random();
		for(int i=0; i<1024; i++){
			int n = rand.nextInt(25)+65;
			int p = rand.nextInt(10)+1;
		
			if((n+p)<90)
				key+=(char)(n+p);
			else
				key+=(char)(n);
		}
		
		return key;
		
	}
	
	public static String input(){
		String key;
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter your key");
		key = keyboard.nextLine();
		return key;
	}
	
	public static void arrayIncrement(int[][] a,int size, int keySize){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(a[i][j]<95)
					a[i][j]++;
				else
					a[i][j]=65;
			}
		}
	}
	
	public static void arrayDecrement(int[][] a,int size, int keySize){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(a[i][j]>64)
					a[i][j]--;
				else
					a[i][j]=95;
			}
		}
	}
	
	public static int findSize(String[] a){
		int result = 0;
		
		if(a[0].length() <= 4)
			result=2;
		else if(a[0].length() <= 16)
			result=4;
		else if(a[0].length() <= 64)
			result=8;
		else if(a[0].length() <= 256)
			result=16;
		else if(a[0].length() <= 1024)
			result=32;
		else if(a[0].length() <= 4096)
			result=64;
		return result;
	}
	
	public static void fixCodes(int[][] a, int b){
		for(int i=0;i<b;i++){
			for(int j=0;j<b;j++){
				if(a[i][j] == 46)
					a[i][j]=92;
				else if(a[i][j] == 95)
					a[i][j]=91;
			}
		}
	}
	
	public static void reverseFixCodes(char[][] a, int b){
		for(int i=0;i<b;i++){
			for(int j=0;j<b;j++){
				if(a[i][j] == 92)
					a[i][j]=46;
				else if(a[i][j] == 91)
					a[i][j]=95;
			}
		}
	}
	
	public static void transArray(int[][] orig, int[][] trans){
		for(int i=0;i<orig.length;i++){
			for(int j=0;j<orig.length;j++){
				trans[j][i]=orig[i][j];
			}
		}
	}
	
	public static void diagShift(int[][] arr, int[][] shift){
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr.length;j++){
				shift[(j+(arr.length/2))%arr.length][(i+(arr.length/2))%arr.length]=arr[i][j];
			}
		}
	}
	
	public static void xOr(int[][] a,int[][] b){
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a.length;j++){
				a[i][j]=(a[i][j]^b[i][j]);
			}
		}
	}
	
	public static void translate(int[][] a, char[][] b){
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a.length;j++){
				b[i][j]=(char)a[i][j];
			}
		}
	}
	
	public static void copyArr(int[][] a, int[][] b, int size){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				a[i][j] = b[i][j];
			}
		}
	}

}

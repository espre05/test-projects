
public class JniExample1{
	public native int intMethod(int n);
//	public native boolean booleanMethod(boolean bool);
//	public native String stringMethod(String text);
//	public native int intArrayMethod(int[] intArray);
	
	public static void main(String[] args){
		System.loadLibrary("JniExample1");
		JniExample1 app = new JniExample1();
		int square = app.intMethod(5);
//		boolean bool = app.booleanMethod(true);
//		String text = app.stringMethod("java");
//		int sum = app.intArrayMethod(new int[] {1,2,3,4,5});
		
		System.out.println("intMethod" + square);
	}
}
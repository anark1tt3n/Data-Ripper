import java.io.*;

public class PText {
	private static String[][] data;
	private static String[] array1;

	public static void parseText(File iFile, int flength,String out,int item,String div) throws IOException{
		data = new String[flength][2];
		File oFile;
		if(out.substring(out.length() - 4).equals(".txt")){
			oFile = new File(out);
		}
		else{
			oFile = new File(out + ".txt");
		}
		BufferedReader br = new BufferedReader(new FileReader(iFile));
		if(item==1){
			array1 = new String[1];
			for(int i=0;i<flength;i++){
				try{
				array1 = br.readLine().trim().split(div,2);
				}
				catch(NullPointerException e){
					System.err.println(e + " " + array1[0] + " " + array1[1]);
				}
				data[i][0] = array1[0];
			}
		}
		else{
			int t=1;
			array1 = new String[2];
				for(int i=0;i<flength;i++){
					try{
					array1 = br.readLine().trim().split(div,2);
					}
					catch(NullPointerException e){
						System.err.println(data[i][0]);
						System.err.println(e + " " + array1[0]);
						System.err.println(e + " " + array1[1]);
					}
					data[i][1] = array1[1];
				}
				while(t<item){
					for(int i=0;i<flength;i++){
						array1 = data[i][1].trim().split(div,2);
						data[i][0] = array1[0];
						data[i][1] = array1[1];
					}
					t++;
				}
		}
		br.close();
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(
			       new FileOutputStream(oFile), "UTF-8"));
		for(int i=0;i<flength;i++){
			writer.println(data[i][0]);
		}
		writer.close();
	}
	public static int count(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean endsWithoutNewLine = false;
	        while ((readChars = is.read(c)) != -1) {
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n')
	                    ++count;
	            }
	            endsWithoutNewLine = (c[readChars - 1] != '\n');
	        }
	        if(endsWithoutNewLine) {
	            ++count;
	        } 
	        return count;
	    } finally {
	        is.close();
	    }
	}
}

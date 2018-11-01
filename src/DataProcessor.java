import java.io.File;
import java.util.Scanner;
import java.util.Vector;
public class DataProcessor {

    private static boolean checkСoincidenced(String k, String v, final String title, String line){
        return (k.equals(title) && v.equals(line.substring(0, line.indexOf(';'))));
    }
    private static String cutString(String line){
        return line.substring(line.indexOf(';') + 1, line.length());
    }
    
    private static boolean findSubstrings(Vector<String> k, Vector<String> v, String line){
        int countCoincidences=0;
        for(int i=0; i<k.size();++i) {
            if (checkСoincidenced(k.get(i),v.get(i),"data_time", line)){
                ++countCoincidences;
                line = cutString(line);
                continue;
            }
            line = cutString(line);
            if (checkСoincidenced(k.get(i),v.get(i),"user_id", line)){
                ++countCoincidences;
                line = cutString(line);
                continue;
            }
            line = cutString(line);
            if (checkСoincidenced(k.get(i),v.get(i),"service", line)){
                ++countCoincidences;
                line = cutString(line);
                continue;
            }
            line = cutString(line);
            if(checkСoincidenced(k.get(i),v.get(i),"protocol", line)){
                ++countCoincidences;
                line = cutString(line);
                continue;
            }
            line = cutString(line);
            if(checkСoincidenced(k.get(i),v.get(i),"upload", line)){
                ++countCoincidences;
                line = cutString(line);
                continue;
            }
            line = cutString(line);
            if(checkСoincidenced(k.get(i),v.get(i),"download", line)){
                ++countCoincidences;
                line = cutString(line);
                continue;
            }
            if(line.contains(k.get(i)+"\":\""+v.get(i))) ++countCoincidences;
        }
        return k.size()==countCoincidences;
    }
    public static void main(String[]  args){
        Vector<String> keys = new Vector();
        Vector<String> values = new Vector();
        //String keyDataTime, keyUserId,
        for (int i=1; i< args.length;++i){
            if(args[i].contains("data_time")) {
                keys.add(args[i].substring(0,args[i].indexOf('=')));
                values.add(args[i].substring(args[i].indexOf('=')+1, args[i].length())+" "+args[i+1]);
                ++i;
            }else {
                keys.add(args[i].substring(0, args[i].indexOf('=')));
                values.add(args[i].substring(args[i].indexOf('=') + 1, args[i].length()));
            }
        }
        try {
            File file = new File(args[0]);
            Scanner data = new Scanner(file);
            int count=0;
            while (data.hasNextLine()){
                String line=data.nextLine();
                if(findSubstrings(keys,values,line)) {
                    System.out.println(line);
                    ++count;
                }
            }
            if(count==0) System.out.println("No matches");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

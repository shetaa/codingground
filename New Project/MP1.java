import java.io.File;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;

public class MP1 {
    Random generator;
    String userName;
    String inputFileName;
    String delimiters = " \t,;.?!-:@[](){}_*/";
    String[] stopWordsArray = {"i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", "yours",
            "yourself", "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its",
            "itself", "they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this", "that",
            "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had", "having",
            "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as", "until", "while",
            "of", "at", "by", "for", "with", "about", "against", "between", "into", "through", "during", "before",
            "after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off", "over", "under", "again",
            "further", "then", "once", "here", "there", "when", "where", "why", "how", "all", "any", "both", "each",
            "few", "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own", "same", "so", "than",
            "too", "very", "s", "t", "can", "will", "just", "don", "should", "now"};

    void initialRandomGenerator(String seed) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA");
        messageDigest.update(seed.toLowerCase().trim().getBytes());
        byte[] seedMD5 = messageDigest.digest();

        long longSeed = 0;
        for (int i = 0; i < seedMD5.length; i++) {
            longSeed += ((long) seedMD5[i] & 0xffL) << (8 * i);
        }

        this.generator = new Random(longSeed);
    }

public <T>  int containsCount(final T[] array, final T key) {
        int count=0;
        for(int i=0;i<array.length;i++)
                if(array[i]==key)
                        count++;
        return count;
}

public <T>  boolean contains(final T[] array, final T key) {
    return Arrays.asList(array).contains(key);
}

public boolean contains(final int[] array, final int key) {
        for (final int i : array) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }

    Integer[] getIndexes() throws NoSuchAlgorithmException {
        Integer n = 10000;
        Integer number_of_lines = 50000;
        Integer[] ret = new Integer[n];
        this.initialRandomGenerator(this.userName);
        for (int i = 0; i < n; i++) {
            ret[i] = generator.nextInt(number_of_lines);
//              System.out.print(ret[i]);

        }
        return ret;
    }



    public MP1(String userName, String inputFileName) {
        this.userName = userName;
        this.inputFileName = inputFileName;
    }

    public String[] process() throws Exception {
        String[] ret = new String[20];

                    String everything;
                try(BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();

                Integer indexes[] = getIndexes();
                        int j=0;



                    while (line != null) {
                        int k=containsCount(indexes,j);
                        for(int l=0;l<k;l++)
                        {
                System.out.println(j+ " line " +line+" k "+k);
                                sb.append(line);
                                System.out.println(line);
                                sb.append(System.lineSeparator());
                        }
                        line = br.readLine();j++;
                    }
                    everything = sb.toString().toLowerCase();

                }


             StringTokenizer st = new StringTokenizer(everything,delimiters);
                int i=0;
                    StringBuilder sbn = new StringBuilder();
         HashMap hm = new HashMap();
                String current;
             while (st.hasMoreTokens() ) {
                current=st.nextToken();
                System.out.println("current " +current);

                if(!contains(stopWordsArray,current))
                       {
                System.out.println("current not contains " +current);
                            if (!hm.containsKey(current)) {
                                hm.put(current, 1);
                                System.out.println(current+"1");
                            } else {
                                hm.put(current, (Integer) hm.get(current) + 1);
                                System.out.println(current+hm.get(current));
                            }
                        }
             }

         Map<Integer, String> map = new TreeMap<Integer, String>(hm);

Set set2 = map.entrySet();
         Iterator iterator2 = set2.iterator();
         while(iterator2.hasNext()) {
              Map.Entry me2 = (Map.Entry)iterator2.next();
              System.out.print(me2.getKey() + ": ");
              System.out.println(me2.getValue());
         }



        //TODO

        return ret;
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1){
            System.out.println("MP1 <User ID>");
        }
        else {
            String userName = args[0];
            String inputFileName = "./input.txt";
            MP1 mp = new MP1(userName, inputFileName);
            String[] topItems = mp.process();
            for (String item: topItems){
                System.out.println(item);
            }
        }
    }
}

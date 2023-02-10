import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

/**
 * Determines whether or not words input have subwords
 * @autor 24steinbergb
 * Constructor for a SubWord object.  Start by supplying the
 * @version 02/09/23
 */


public class SubWordFinder implements WordFinder {
    private ArrayList<ArrayList<String>> dictionary;
    private String alpha = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Initiate the dictionary and setting up param
     */
    public SubWordFinder() {
        dictionary = new ArrayList<>();
        for (int i = 0; i < 26; i++)
            dictionary.add(new ArrayList<String>());
        populateDictionary();

    }

    /**
     * Populates the dictionary from the text file contents
     * The dictionary object should contain 26 buckets, each
     * bucket filled with an ArrayList<String>
     * The String objects in the buckets are sorted A-Z because
     * of the nature of the text file words.txt
     */
    public void  populateDictionary() {
        try{
            Scanner in = new Scanner(new File("new_scrabble.txt"));
            while(in.hasNext()){
                String word = in.nextLine();
                //System.out.println("DEBUG: " + word);
                //System.out.println("DEBUG: " + alpha.indexOf(word.substring(0,1)));
                dictionary.get(alpha.indexOf(word.substring(0,1))).add(word);
            }
            in.close();
            for(int i = 0; i < dictionary.size(); i++){
                Collections.sort(dictionary.get(i));
            }
            //for(ArrayList<String> bucket : dictionary)
            //System.out.println(bucket);
        }
        catch(Exception e){
            System.out.println("Error here: " + e);
            e.printStackTrace();
        }
    }



    public int binarySearch(ArrayList<String> arrayLost, int left, int right, String word) {
        if (right >= left) {
            int mid = (right + left) / 2;
            if (arrayLost.get(mid).equals(word))
                return mid;
            return word.compareTo(arrayLost.get(mid)) > 0 ? binarySearch(arrayLost, mid + 1, right, word) :
                    binarySearch(arrayLost, left, mid - 1, word);
        }
        return -1;
    }

    /**
     * Retrieve all SubWord objects from the dictionary.
     * A SubWord is defined as a word that can be split into two
     * words that are also found in the dictionary.  The words
     * MUST be split evenly, e.g. no unused characters.
     * For example, "baseball" is a SubWord because it contains
     * "base" and "ball" (no unused characters)
     * To do this, you must look through every word in the dictionary
     * to see if it is a SubWord object
     *
     * @return An ArrayList containing the SubWord objects
     * pulled from the file words.txt
     */
    public ArrayList<SubWord> getSubWords() {
            ArrayList<SubWord> subword = new ArrayList<>();
            for (ArrayList<String> bucket : dictionary) {
                for (String word : bucket) {
                    String front = "", back = "";
                    for (int i = 2; i < word.length() - 1; i++) {
                        front = word.substring(0, i);
                        back = word.substring(i);
                        if (inDictionary(front) && inDictionary(back)) {
                            subword.add(new SubWord(word, front, back));
                        }
                    }
                }
            }
            return subword;
        }

            /**
             * Look through the entire dictionary object to see if
             * word exists in dictionary
             *
             * @param word The item to be searched for in dictionary
             * @return true if word is in dictionary, false otherwise
             * NOTE: EFFICIENCY O(log N) vs O(N) IS A BIG DEAL HERE!!!
             * You MAY NOT use Collections.binarySearch() here; you must use
             * YOUR OWN DEFINITION of a binary search in order to receive
             * the credit as specified on the grading rubric.
             */

           public boolean inDictionary (String word){
            ArrayList<String> bucket = dictionary.get(alpha.indexOf(word.substring(0, 1)));
            return binarySearch(bucket, 0, bucket.size() - 1, word) >= 0;
        }

    /**
     * Main method for project 03/01/23 SubWordFinder
     * @param args Command line arguments, if needed
     */
    public static void main (String[] args){
                SubWordFinder app = new SubWordFinder();
                ArrayList<SubWord> sub = app.getSubWords();
                System.out.println("* List of SubWord objects in dictionary*");
                for(SubWord temp : sub) {
                    System.out.println(temp);
                }
                System.out.println(sub.size() + " total SubWords");
        }
}
/**
 * SubWord object contains the root word and the two interior
 * words that can be contained within it.
 * Example:	rootWord is "baseball"
 * 			sub words are "base" + "ball"
 * TODO: Override this @author statement
 * @author ???
 * TODO: Override this @version statement
 * @version 01/12/2015
 */

import java.util.*;
public class SubWord implements Comparable<SubWord> {
	private String rootWord;
	private String sub1, sub2;
	
	/**
	 * @autor 24steinbergb@da.org
	 *
	 * Constructor for a SubWord object.  Start by supplying the
	 * root word for the object.
	 * @param root The rootWord value of this object.
	 */
	public SubWord(String root, String firstSub, String secondSub)	{
		rootWord = root;
		sub1 = firstSub;
		sub2 = secondSub;
	}
	
	/**
	 * Utility accessor method
	 * @return The rootWord of this object
	 */
	public String getRoot()	{	return rootWord;	}
	/**
	 * Utility accessor method
	 * @return The list of sub words
	 */
	public String getSubWords()	{	return sub1 + " + " + sub2;	}
	
	/**
	 * Compares two SubWord objects alphabetically A to Z based on rootWord
	 * @return -int if this.rootWord is less than other.rootWord, 0 if both 
	 * are equal, +int if this.rootWord is greater than other.rootWord
	 */
	public int compareTo(SubWord other)	{
		//TODO: override this invalid return statement

		return rootWord.compareTo(other.getRoot());
	}
	
	/**
	 * @return This SubWord in root = sub1 + sub2 format
	 */
	public String toString()	{
		//TODO: define this method to return "rootWord = subWord1 + subWord2"
		return rootWord + "=" + getSubWords();
	}
}

/*
 * TCES360 Conference Management System
 * Warrick Holfeld
 * Jacob Hall
 * Aarron Merril
 * Tyler Powers
 * Seth Kramer
 * David Swanson
 * 11/10/13
 */


package controller;

/**
 * This is just a temporary Object that reflects the fields that are relevant to filling out a review form.  It is only 
 * used to temporary create an Object that holds the fields which can then be packed into a data structure for efficient
 * transfer to and from the Controller.
 * 
 * @author Jacob Hall
 * @version 90 Date: 11/27/13
 */
public class Review {

	private String reviewer_name = "";
	private Conference conference;
	private String username = "";
	private String paper = "";
	private String paper_author = "";
	private String comments_to_subpc = "";
	private int[] answersRadioBtn;
	private String summary_comments = "";
	
	/**
	 * Constructor for the Review Object.
	 * 
	 * @param the_reviewer_name
	 * @param the_conf
	 * @param the_username
	 * @param the_paper
	 * @param the_paper_author
	 * @param the_comments_to_subpc
	 * @param the_answersRadioBtn
	 * @param the_summary_comments
	 */
	public Review(final String the_reviewer_name, final Conference the_conf, 
			final String the_username, final String the_paper, final String the_paper_author, final String the_comments_to_subpc, 
			final int[] the_answersRadioBtn, final String the_summary_comments){
		reviewer_name = the_reviewer_name;
		conference = the_conf;
		username = the_username;
		paper = the_paper;
		paper_author = the_paper_author;
		comments_to_subpc = the_comments_to_subpc;
		answersRadioBtn = the_answersRadioBtn;
		summary_comments = the_summary_comments;
	}
	
	public String getReviewerName(){
		return reviewer_name;
	}
	
	public Conference getConference(){
		return conference;
	}

	public String getUsername(){
		return username;
	}
	
	public String getPaper(){
		return paper;
	}
	
	public String getPaperAuthor(){
		return paper_author;
	}
	
	public String getCommentsToSubPC(){
		return comments_to_subpc;
	}
	
	public int[] getAnswersToRadioBtns(){
		return answersRadioBtn;
	}
	
	public String getSummaryComments(){
		return summary_comments;
	}
}

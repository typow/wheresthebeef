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
 * @version 152 Date: 12/4/13
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
	 * Constructor for the Review Object
	 * 
	 * @param the_reviewer_name The username of the reviewer.
	 * @param the_conf The conference the review is associated with.
	 * @param the_username The username.
	 * @param the_paper The title of the paper.
	 * @param the_paper_author The username of the author.
	 * @param the_comments_to_subpc Comments to the SubPC.
	 * @param the_answersRadioBtn All the ratings the reviewer gave the paper.
	 * @param the_summary_comments Comments from the reviewer.
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
	
	/**
	 * Getter for the Reviewer's username.
	 * 
	 * @return Returns the Reviewer's username.
	 */
	public String getReviewerName(){
		return reviewer_name;
	}
	
	/**
	 * Getter for the Conference the review is associated with.
	 * 
	 * @return Returns the Conference.
	 */
	public Conference getConference(){
		return conference;
	}
	
	/**
	 * Getter for the username.
	 * 
	 * @return Returns the username.
	 */
	public String getUsername(){
		return username;
	}
	
	/**
	 * Getter for the name of the paper.
	 * 
	 * @return Returns the paper title.
	 */
	public String getPaper(){
		return paper;
	}
	
	/**
	 * Getter for the Author's username.
	 * 
	 * @return Returns the Author's username.
	 */
	public String getPaperAuthor(){
		return paper_author;
	}
	
	/**
	 * Getter for SubPC's comments.
	 * 
	 * @return Returns the SubPC's comments.
	 */
	public String getCommentsToSubPC(){
		return comments_to_subpc;
	}
	
	/**
	 * Getter for the Reviewers ratings.
	 * 
	 * @return Returns an array of integer ratings from the reviewer.
	 */
	public int[] getAnswersToRadioBtns(){
		return answersRadioBtn;
	}
	
	/**
	 * Getter for the comments from the reviewer.
	 * 
	 * @return Returns the Reviewer's comments.
	 */
	public String getSummaryComments(){
		return summary_comments;
	}
}

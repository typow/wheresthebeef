package controller;

public class Review {

	private String reviewer_name = "";
	private Conference conference;
	private String username = "";
	private String paper = "";
	private String paper_author = "";
	private String comments_to_subpc = "";
	private int[] answersRadioBtn;
	private String summary_comments = "";
	
	
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

package controller;

public class Conference {

	private String conf_title;
	private String program_chair;
	private String conf_date;
	private String conf_address;
	private String conf_city;
	private String conf_state;
	private String con_zip;
	private String submission_deadline;
	private String review_deadline;
	private String sub_pc_recommend_deadline;
	private String author_notification_deadline;
	private String conference_summary;
	
	public Conference(final String the_conf_title, final String the_program_chair, final String the_conf_date, 
			final String the_conf_address, final String the_conf_city, final String the_conf_state, final String the_con_zip, 
			final String the_submission_deadline, final String the_review_deadline, final String the_sub_pc_recommend_deadline, 
			final String the_author_notification_deadline, final String the_conference_summary){
		conf_title = the_conf_title;
		program_chair = the_program_chair;
		conf_date = the_conf_date;
		conf_address = the_conf_address;
		conf_city = the_conf_city;
		conf_state = the_conf_state;
		con_zip = the_con_zip;
		submission_deadline = the_submission_deadline;
		review_deadline = the_review_deadline;
		sub_pc_recommend_deadline = the_sub_pc_recommend_deadline;
		author_notification_deadline = the_author_notification_deadline;
		conference_summary = the_conference_summary;
	}
	
	//TODO: add getters for this so that the conference object can be passed around and dat retrieved from it.
}

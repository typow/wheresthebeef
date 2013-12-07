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

import java.util.Date;

/**
 * This Conference object is just a temporary container to efficiently pass data to and from the Controller.  It can
 * be instantiated long enough pack multiple Objects in a data structure which is then unpacked and the data can be
 * retrieved.  There isn't a full set of Conference Objects always available to reflect all the conferences existing
 * in the database.
 * 
 * @author Jacob Hall
 * @version 90 Date: 11/15/13
 */
public class Conference {

	private String conf_title;
	private String program_chair;
	private Date conf_date;
	private String conf_address;
	private String conf_city;
	private String conf_state;
	private String con_zip;
	private Date submission_deadline;
	private Date review_deadline;
	private Date sub_pc_recommend_deadline;
	private Date author_notification_deadline;
	private String conference_summary;
	
	/**
	 * Constructor for the Conference Object
	 * 
	 * @param the_conf_title The title of the conference.
	 * @param the_program_chair The username of the PC.
	 * @param the_conf_date The Date of the conference.
	 * @param the_conf_address The address of the conference.
	 * @param the_conf_city The city of the conference.
	 * @param the_conf_state The state the conference is in.
	 * @param the_con_zip The zip code the conference is in.
	 * @param the_submission_deadline The deadline for paper submissions.
	 * @param the_review_deadline The deadline for reviews to be turned in.
	 * @param the_sub_pc_recommend_deadline The deadline for the SubPC's recommendation.
	 * @param the_author_notification_deadline The deadline for notifying the author.
	 * @param the_conference_summary The description of the conference.
	 */
	public Conference(final String the_conf_title, final String the_program_chair, final Date the_conf_date, 
			final String the_conf_address, final String the_conf_city, final String the_conf_state, final String the_con_zip, 
			final Date the_submission_deadline, final Date the_review_deadline, final Date the_sub_pc_recommend_deadline, 
			final Date the_author_notification_deadline, final String the_conference_summary){
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
	
	/**
	 * Getter for the title of the conference.
	 * 
	 * @return Returns the title of the conference.
	 */
	public String getConfTitle(){
		return conf_title;
	}
	
	/**
	 * Getter for the PC's username.
	 * 
	 * @return Returns the PC's username.
	 */
	public String getProgramChair(){
		return program_chair;
	}
	
	/**
	 * Getter for the conference's Date object.
	 * 
	 * @return Returns conference's Date object.
	 */
	public Date getConfDate(){
		return conf_date;
	}
	
	/**
	 * Getter for the conference's address.
	 * 
	 * @return Returns conference's address.
	 */
	public String getConfAddress(){
		return conf_address;
	}
	
	/**
	 * Getter for the conference's city.
	 * 
	 * @return Returns conference's city.
	 */
	public String getConCity(){
		return conf_city;
	}
	
	/**
	 * Getter for the conference's state.
	 * 
	 * @return Returns conference's state.
	 */
	public String getConfState(){
		return conf_state;
	}
	
	/**
	 * Getter for the conference's zip code.
	 * 
	 * @return Returns conference's zip code.
	 */
	public String getConfZip(){
		return con_zip;
	}
	
	/**
	 * Getter for the conference's submission Date object.
	 * 
	 * @return Returns conference's submission Date object.
	 */
	public Date getSubmissionDead(){
		return submission_deadline;
	}
	
	/**
	 * Getter for the conference's review Date object.
	 * 
	 * @return Returns conference's review Date object.
	 */
	public Date getReviewDead(){
		return review_deadline;
	}
	
	/**
	 * Getter for the conference's recommendation Date object.
	 * 
	 * @return Returns conference's recommendation Date object.
	 */
	public Date getSubPCReccomendDead(){
		return sub_pc_recommend_deadline;
	}
	
	/**
	 * Getter for the conference's authors notification Date object.
	 * 
	 * @return Returns conference's authors notification Date object.
	 */
	public Date getAuthorNotificationDead(){
		return author_notification_deadline;
	}
	
	/**
	 * Getter for the conference's summary.
	 * 
	 * @return Returns conference's summary.
	 */
	public String getConfSummary(){
		return conference_summary;
	}
}

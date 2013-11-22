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
 * The Conference object will hold information relevant to a single conference so that the object can be 
 * passed around as a container holding the relevant data.
 * 
 * @author Jacob Hall
 * @version 11/15/13
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
	
	//TODO: add getters for this so that the conference object can be passed around and dat retrieved from it.
	
	public String getConfTitle(){
		return conf_title;
	}
	
	public String getProgramChair(){
		return program_chair;
	}
	public Date getConfDate(){
		return conf_date;
	}
	public String getConfAddress(){
		return conf_address;
	}
	public String getConCity(){
		return conf_city;
	}
	public String getConfState(){
		return conf_state;
	}
	public String getConfZip(){
		return con_zip;
	}
	public Date getSubmissionDead(){
		return submission_deadline;
	}
	public Date getReviewDead(){
		return review_deadline;
	}
	public Date getSubPCReccomendDead(){
		return sub_pc_recommend_deadline;
	}
	public Date getAuthorNotificationDead(){
		return author_notification_deadline;
	}
	public String getConfSummary(){
		return conference_summary;
	}
}

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

import view.GUIEnum.paperStatusAdminViewable;
import view.GUIEnum.paperStatusAuthorViewable;

/**
 * This Paper object was created to help in passing of information from the 
 * controller to the ConferenceGUI in an array.
 * 
 * @author Jacob Hall
 * @author Seth Kramer
 * @version 164 Date: 12/4/13
 */
public class Paper {
	private String conf_title = "";
	private String paper_title = "";
	private String author = "";
	private paperStatusAuthorViewable status_author_viewable;
	private paperStatusAdminViewable status_admin_viewable;
	private String sub_pc = "";
	private String pc = "";
	private String[] reviewers;
	private int id;
	
	/**
	 * Constructor for the Paper object
	 * 
	 * @param the_conf_title The Conference title.
	 * @param the_paper_title The paper title.
	 * @param the_author The author's username.
	 * @param the_status_author_viewable The author viewable status.
	 * @param the_status_admin_viewable The admin viewable status.
	 * @param the_sub_pc The username of the Paper's SubPC.
	 * @param the_pc The username of the Paper's PC.
	 * @param the_reviewers An array of users assigned as reviewers.
	 * @param the_id The ID # of the paper.
	 */
	public Paper(final String the_conf_title, final String the_paper_title, 
			final String the_author, 
			final paperStatusAuthorViewable the_status_author_viewable,
			final paperStatusAdminViewable the_status_admin_viewable,
			final String the_sub_pc, final String the_pc, final String[] the_reviewers,
			final int the_id){	
		conf_title = the_conf_title;
		paper_title = the_paper_title;
		author = the_author;
		status_author_viewable = the_status_author_viewable;
		status_admin_viewable = the_status_admin_viewable;
		sub_pc = the_sub_pc;
		pc = the_pc;
		reviewers = the_reviewers;
		id = the_id;
	}
	
	/**
	 * Getter for the Conference title.
	 * 
	 * @return Returns the Conference title.
	 */
	public String getConfTitle(){
		return conf_title;
	}
	
	/**
	 * Getter for the paper title.
	 * 
	 * @return Returns the paper title.
	 */
	public String getPaperTitle(){
		return paper_title;
	}
	
	/**
	 * Getter for the author's username.
	 * 
	 * @return Returns the author's username.
	 */
	public String getAuthor(){
		return author;
	}
	
	/**
	 * Getter for the author status enum.
	 * 
	 * @return Returns the author status enum.
	 */
	public paperStatusAuthorViewable getStatusAuthorViewable(){
		return status_author_viewable;
	}
	
	/**
	 * Getter for the admin status enum.
	 * 
	 * @return Returns the admin status enum.
	 */
	public paperStatusAdminViewable getStatusAdminViewable(){
		return status_admin_viewable;
	}
	
	/**
	 * Getter for the SubPC's username.
	 * 
	 * @return Returns the SubPC's username.
	 */
	public String getSubPC(){
		return sub_pc;
	}
	
	/**
	 * Getter for the PC's username.
	 * 
	 * @return Returns the PC's username.
	 */
	public String getPC(){
		return pc;
	}
	
	/**
	 * Getter for the array of Reviewers.
	 * 
	 * @return Returns the Paper's Reviewers.
	 */
	public String[] getReviewers(){
		return reviewers;
	}
	
	/**
	 * Getter for the Paper ID#.
	 * 
	 * @return Returns the Paper ID#.
	 */
	public int getID(){
		return id;
	}
}

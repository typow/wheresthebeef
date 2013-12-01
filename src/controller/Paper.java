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
 * @version 148 Date: 12/1/13
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
	 * @param the_conf_title
	 * @param the_paper_title
	 * @param the_author
	 * @param the_status_author_viewable
	 * @param the_status_admin_viewable
	 * @param the_sub_pc
	 * @param the_pc
	 * @param the_reviewers
	 * @param the_id
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
	
	public String getConfTitle(){
		return conf_title;
	}
	
	public String getPaperTitle(){
		return paper_title;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public paperStatusAuthorViewable getStatusAuthorViewable(){
		return status_author_viewable;
	}
	
	public paperStatusAdminViewable getStatusAdminViewable(){
		return status_admin_viewable;
	}
	
	public String getSubPC(){
		return sub_pc;
	}
	
	public String getPC(){
		return pc;
	}
	
	public String[] getReviewers(){
		return reviewers;
	}
	
	public int getID(){
		return id;
	}
}

package controller;

import view.GUIEnum.paperStatusAdminViewable;
import view.GUIEnum.paperStatusAuthorViewable;

public class Paper {
	private String conf_title = "";
	private String paper_title = "";
	private String author = "";
	private paperStatusAuthorViewable status_author_viewable;
	private paperStatusAdminViewable status_admin_viewable;
	private String sub_pc = "";
	private String pc = "";
	private String[] reviewers = new String[4];
	private int id;
	
	public Paper(final String the_conf_title, final String the_paper_title, 
			final String the_author, 
			final paperStatusAuthorViewable the_status_author_viewable,
			final paperStatusAdminViewable the_status_admin_viewable,
			final String the_sub_pc, final String the_pc, final String[] the_reviewers,
			final int the_id){	
	}
}

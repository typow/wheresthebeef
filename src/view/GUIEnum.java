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

package view;

/**
 * The enumeration class that holds the enums for: GUI state, 
 * paper status that's viewable for administrative purposes,
 * paper status that's viewable for author's purposes,
 * and the relationship a user logged in has with a paper.
 * 
 * @author Jacob Hall
 * @author Warrick Holfeld
 * @version 112 Date: 11/28/13
 */
public class GUIEnum {

	/**
	 * The enumeration class to reference what state the GUI is in.
	 * 
	 * <dt><b>Preconditions: </b><dd>
	 * <dt><b>Postconditions: </b><dd>
	 */
	public enum StateOfGUI{
		/**
		 * Make the login screen is visible.
		 */
		LOGIN,
		/**
		 * Make the registration screen visible.
		 */
		REGISTER,
		/**
		 * Make the main user interface visible.
		 */
		HOME,
		/**
		 * Make the new conference user interface visible.
		 */
		NEW_CONFERENCE,
		/**
		 * Make the manage paper user interface visible.
		 */
		MANAGE_PAPER,
		/**
		 * Make the submit paper user interface visible.
		 */
		SUBMIT_PAPER,
		/**
		 * Make the edit submission user interface visible.
		 */
		EDIT_SUBMISSION,
		/**
		 * Make the submit review user interface visible.
		 */
		SUBMIT_REVIEW,
		/**
		 * Make the submit recommendation user interface visible.
		 */
		SUBMIT_RECOMMENDATION,
		/**
		 * Make the assign reviewer user interface visible.
		 */
		ASSIGN_REVIEWER,
		/**
		 * Make the assign SubPC user interface visible.
		 */
		ASSIGN_SUB_PC,
		/**
		 * Make the accept/reject paper user interface visible.
		 */
		ACCEPT_REJECT,
		/**
		 * Make the conference user interface visible.
		 */
		CONFERENCE;
	}
	
	/**
	 * The paper status that's viewable for administrative purposes.
	 * 
	 * <dt><b>Preconditions: </b><dd>
	 * <dt><b>Postconditions: </b><dd>
	 */
	public enum paperStatusAdminViewable{
		/**
		 * the paper has been submitted.
		 */
		SUBMITTED,
		/**
		 * the paper is waiting to be reviewed.
		 */
		UNDER_REVIEW,
		/**
		 * the paper has been reviewed.
		 */
		REVIEWED,
		/**
		 * the paper is overdue for review.
		 */
		OVERDUE_FOR_REVIEW,
		/**
		 * the recommendation for the paper has been submitted and it is recommended for acceptance.
		 */
		RECOMMENDED,
		/**
		 * the paper is overdue for a recommendation.
		 */
		OVERDUE_FOR_RECOMMEND,
		/**
		 * the recommendation for the paper has been submitted and it is not recommended for acceptance.
		 */
		NOT_RECOMMENDED,
		/**
		 * the paper has been accepted for the conference
		 */
		ACCEPTED,
		/**
		 * the paper has not been accepted for the conference
		 */
		REJECTED,
	}
	
	/**
	 * The paper status that's viewable for an author's purposes.
	 * 
	 * <dt><b>Preconditions: </b><dd>
	 * <dt><b>Postconditions: </b><dd>
	 */
	public enum paperStatusAuthorViewable{
		/**
		 * the paper has been submitted.
		 */
		SUBMITTED,
		/**
		 * the paper is in the review process
		 */
		UNDER_REVIEW,
		/**
		 * the paper has been accepted for the conference
		 */
		ACCEPTED,
		/**
		 * the paper has not been accepted for the conference
		 */
		REJECTED,
	}
	
	/**
	 * The relationship a user has with a paper.
	 * 
	 * <dt><b>Preconditions: </b><dd>
	 * <dt><b>Postconditions: </b><dd>
	 */
	public enum paperRelation{
		/**
		 * the user is the Author of the paper
		 */
		AUTHOR,
		/**
		 * the user is a Reviewer of the paper
		 */
		REVIEWER,
		/**
		 * the user is the SUB PC for the paper
		 */
		SUBPC,
		/**
		 * the user is the PC for the paper
		 */
		PC,
	}

}

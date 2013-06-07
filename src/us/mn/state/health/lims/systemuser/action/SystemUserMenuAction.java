/**
* The contents of this file are subject to the Mozilla Public License
* Version 1.1 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/ 
* 
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
* License for the specific language governing rights and limitations under
* the License.
* 
* The Original Code is OpenELIS code.
* 
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*/
package us.mn.state.health.lims.systemuser.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import us.mn.state.health.lims.common.action.BaseMenuAction;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.systemuser.dao.SystemUserDAO;
import us.mn.state.health.lims.systemuser.daoimpl.SystemUserDAOImpl;

/**
 * @author diane benz
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class SystemUserMenuAction extends BaseMenuAction {

	protected List createMenuList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//System.out.println("I am in SystemUserMenuAction createMenuList()");

		List systemUsers = new ArrayList();

		String stringStartingRecNo = (String) request
				.getAttribute("startingRecNo");
		int startingRecNo = Integer.parseInt(stringStartingRecNo);

		SystemUserDAO systemUserDAO = new SystemUserDAOImpl();
		systemUsers = systemUserDAO.getPageOfSystemUsers(startingRecNo);

		HttpSession session = request.getSession();

		//System.out
		//		.println("I am in SystemUserMenuAction setting menuDefinition");
		request.setAttribute("menuDefinition", "SystemUserMenuDefinition");

		// bugzilla 1411 set pagination variables 
		request.setAttribute(MENU_TOTAL_RECORDS, String.valueOf(systemUserDAO
				.getTotalSystemUserCount()));
		request.setAttribute(MENU_FROM_RECORD, String.valueOf(startingRecNo));
		int numOfRecs = 0;
		if (systemUsers != null) {
			if (systemUsers.size() > SystemConfiguration.getInstance()
					.getDefaultPageSize()) {
				numOfRecs = SystemConfiguration.getInstance()
						.getDefaultPageSize();
			} else {
				numOfRecs = systemUsers.size();
			}
			numOfRecs--;
		}
		int endingRecNo = startingRecNo + numOfRecs;
		request.setAttribute(MENU_TO_RECORD, String.valueOf(endingRecNo));
		//end bugzilla 1411
		
		return systemUsers;
	}

	protected String getPageTitleKey() {
		return "systemuser.browse.title";
	}

	protected String getPageSubtitleKey() {
		return "systemuser.browse.title";
	}

	protected int getPageSize() {
		return SystemConfiguration.getInstance().getDefaultPageSize();
	}

	protected String getDeactivateDisabled() {
		return "false";
	}
}

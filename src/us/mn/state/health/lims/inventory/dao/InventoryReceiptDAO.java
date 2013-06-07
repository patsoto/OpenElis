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
package us.mn.state.health.lims.inventory.dao;

import java.util.List;

import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.inventory.valueholder.InventoryReceipt;

public interface InventoryReceiptDAO extends BaseDAO {

	public boolean insertData(InventoryReceipt inventoryReceipt) throws LIMSRuntimeException;

	public void deleteData(List<InventoryReceipt> inventory) throws LIMSRuntimeException;

	public List<InventoryReceipt> getAllInventoryReceipts() throws LIMSRuntimeException;
	
	public void getData(InventoryReceipt inventoryReceipt) throws LIMSRuntimeException;

	public void updateData(InventoryReceipt inventoryReceipt) throws LIMSRuntimeException;

	public InventoryReceipt getInventoryReceiptById(String id) throws LIMSRuntimeException;

	public InventoryReceipt getInventoryReceiptByInventoryItemId(String id) throws LIMSRuntimeException;

}

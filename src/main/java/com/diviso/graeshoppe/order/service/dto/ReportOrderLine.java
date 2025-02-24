 /*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.diviso.graeshoppe.order.service.dto;

import java.util.List;

/**
 * TODO Provide a detailed description here 
 * @author MayaSanjeev
 * mayabytatech, maya.k.k@lxisoft.com
 */
public class ReportOrderLine {

	private Integer quantity;
	
	private String item ;
	
	private Double total;

	List<AuxItem> auxItems;
	List<ComboItem> combos;
	

	public List<AuxItem> getAuxItems() {
		return auxItems;
	}

	public void setAuxItems(List<AuxItem> auxItems) {
		this.auxItems = auxItems;
	}

	public List<ComboItem> getCombos() {
		return combos;
	}

	public void setCombos(List<ComboItem> combos) {
		this.combos = combos;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "ReportOrderLine [quantity=" + quantity + ", item=" + item + ", total=" + total + ", auxItems="
				+ auxItems + ", combos=" + combos + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auxItems == null) ? 0 : auxItems.hashCode());
		result = prime * result + ((combos == null) ? 0 : combos.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReportOrderLine other = (ReportOrderLine) obj;
		if (auxItems == null) {
			if (other.auxItems != null)
				return false;
		} else if (!auxItems.equals(other.auxItems))
			return false;
		if (combos == null) {
			if (other.combos != null)
				return false;
		} else if (!combos.equals(other.combos))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		return true;
	}
	
}

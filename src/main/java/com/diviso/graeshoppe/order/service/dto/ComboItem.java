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

/**
 * TODO Provide a detailed description here 
 * @author MayaSanjeev
 * mayabytatech, maya.k.k@lxisoft.com
 */
public class ComboItem {
private Double quantity;
	
	private String comboItem ;
	

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getcomboItem() {
		return comboItem;
	}

	public void setcomboItem(String comboItem) {
		this.comboItem = comboItem;
	}

	

	@Override
	public String toString() {
		return "ComboItem [quantity=" + quantity + ", comboItem=" + comboItem + ", total=" +  "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comboItem == null) ? 0 : comboItem.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		
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
		ComboItem other = (ComboItem) obj;
		if (comboItem == null) {
			if (other.comboItem != null)
				return false;
		} else if (!comboItem.equals(other.comboItem))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
	
		return true;
	}
	

}

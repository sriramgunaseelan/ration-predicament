package com.ration.predicament.service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.ration.predicament.constants.AppConstants;

public class RationPredicamentService {

	private List<Object> variant = new LinkedList<>();
	private List<Object> uoms = new LinkedList<>();
	private List<Object> alloc = new LinkedList<>();
	private List<Object> topups = new LinkedList<>();
	private List<Object> postAllocCap = new LinkedList<>();

	/*
	 * This method is used to compute allocation for one size
	 */
	public int computePredicament(int totalQuantity, double size, int logic, int topup, int availability) {
		variant.add(size);
		uoms.add(logic);
		topups.add(topup);
		if (availability >= logic && totalQuantity >= logic) {
			double available = totalQuantity - logic;
			double cal = 0;
			if (available != 0) {
				if (totalQuantity > availability) {
					cal = logic + (topup * ((availability - logic) / topup));
				} else {
					cal = logic + (topup * ((totalQuantity - logic) / topup));
				}
				double allocation = (int) (cal);
				double postAllocation = availability - allocation;
				alloc.add(allocation);
				postAllocCap.add(postAllocation);
				double consumedQuantity = allocation * size;
				totalQuantity = totalQuantity - (int) consumedQuantity;
			}
		} else {
			alloc.add(0.0);
			postAllocCap.add(availability);
		}
		return totalQuantity;
	}

	/*
	 * This method return the total allocation and post allocation
	 */
	public Map<String, Object> allocate(int quantity, List<Object> sizes, List<Object> logisticsConstraints,
			List<Object> topups, List<Object> availableCapacity) {
		Map<String, Object> output = new LinkedHashMap<>();
		variant.clear();
		uoms.clear();
		alloc.clear();
		postAllocCap.clear();
		if (sizes != null && sizes != null) {
			for (int i = 0; i < sizes.size(); i++) {
				quantity = computePredicament(quantity, Double.valueOf(sizes.get(i).toString()),
						Integer.valueOf(logisticsConstraints.get(i).toString()),
						Integer.valueOf(topups.get(i).toString()),
						Integer.valueOf(availableCapacity.get(i).toString()));
			}
			output.put(AppConstants.REMAINDER, quantity);
			output.put(AppConstants.VARIANT, variant);
			output.put(AppConstants.UOMS, logisticsConstraints);
			output.put(AppConstants.ALLOCATIONS, alloc);
			output.put(AppConstants.POST_ALLOCATION_CAPACITY, postAllocCap);
		}
		System.out.println(output);
		return output;
	}

}

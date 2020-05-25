package com.complexible.common.csv.valueprovider;

import com.complexible.common.csv.valueprovider.ValueProvider;

public class RowNumberProvider extends ValueProvider {
		protected String provideValue(int rowIndex, String[] row) {
			return String.valueOf(rowIndex);
		}
}
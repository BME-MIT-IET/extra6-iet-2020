package com.complexible.common.csv.valueprovider;

public class RowNumberProvider extends ValueProvider {
		protected String provideValue(int rowIndex, String[] row) {
			return String.valueOf(rowIndex);
		}
}
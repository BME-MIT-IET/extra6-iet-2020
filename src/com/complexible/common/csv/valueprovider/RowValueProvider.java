package com.complexible.common.csv.valueprovider;

import com.complexible.common.csv.valueprovider.ValueProvider;

public class RowValueProvider extends ValueProvider {
		private final int colIndex;

		public RowValueProvider(int colIndex) {
			this.colIndex = colIndex;
		}

		protected String provideValue(int rowIndex, String[] row) {
			return row[colIndex];
		}
	}